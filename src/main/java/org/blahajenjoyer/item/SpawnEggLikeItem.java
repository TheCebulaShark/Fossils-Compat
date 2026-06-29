package org.blahajenjoyer.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class SpawnEggLikeItem extends Item {

    private final Supplier<EntityType<?>> entityTypeSupplier;
    private final boolean babyOnHatch;

    public SpawnEggLikeItem(Supplier<EntityType<?>> entityTypeSupplier, boolean babyOnHatch, Properties properties) {
        super(properties);
        this.entityTypeSupplier = entityTypeSupplier;
        this.babyOnHatch = babyOnHatch;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (level.isClientSide) return InteractionResult.SUCCESS;

        ServerLevel serverLevel = (ServerLevel) level;
        BlockPos spawnPos = context.getClickedPos().relative(context.getClickedFace());
        EntityType<?> entityType = entityTypeSupplier.get();
        if (entityType == null) return InteractionResult.FAIL;

        net.minecraft.world.entity.Entity entity = entityType.create(serverLevel);
        if (!(entity instanceof Mob mob)) return InteractionResult.FAIL;

        mob.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(spawnPos),
            MobSpawnType.SPAWN_EGG, null, null);
        if (babyOnHatch && mob instanceof AgeableMob ageable) {
            ageable.setAge(-24000);
        }
        mob.moveTo(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5, 0.0F, 0.0F);
        serverLevel.addFreshEntity(mob);

        ItemStack stack = context.getItemInHand();
        if (context.getPlayer() != null && !context.getPlayer().getAbilities().instabuild) {
            stack.shrink(1);
        }
        return InteractionResult.CONSUME;
    }
}
