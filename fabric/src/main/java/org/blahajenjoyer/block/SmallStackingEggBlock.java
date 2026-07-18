package org.blahajenjoyer.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

/**
 * A turtle-egg-a-like: small, stacks up to 4 per block, cracks when trampled/landed on — all
 * inherited unchanged from vanilla's TurtleEggBlock (stepOn, fallOn, onPlace,
 * canBeReplaced/getStateForPlacement stacking, playerDestroy, getShape). Only randomTick is
 * overridden, to hatch our own species instead of a vanilla Turtle, and to swap the hardcoded
 * sand requirement for an arbitrary (or absent) substrate tag — e.g. one species might need
 * sand, another might need the same "hot blocks" tag {@link LargeSingleEggBlock} uses, most
 * need nothing at all.
 */
@SuppressWarnings("deprecation")
public class SmallStackingEggBlock extends TurtleEggBlock {

    private final Supplier<EntityType<?>> entityType;
    private final boolean babyOnHatch;
    private final TagKey<Block> requiredSubstrate;

    /** @param requiredSubstrate block tag the block below must match to progress hatching, or {@code null} for no requirement */
    public SmallStackingEggBlock(Supplier<EntityType<?>> entityType, boolean babyOnHatch, TagKey<Block> requiredSubstrate, BlockBehaviour.Properties properties) {
        super(properties);
        this.entityType = entityType;
        this.babyOnHatch = babyOnHatch;
        this.requiredSubstrate = requiredSubstrate;
    }

    private boolean hasRequiredSubstrate(BlockGetter level, BlockPos pos) {
        return requiredSubstrate == null || level.getBlockState(pos.below()).is(requiredSubstrate);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!shouldUpdateHatchLevel(level) || !hasRequiredSubstrate(level, pos)) return;

        int hatch = state.getValue(HATCH);
        if (hatch < MAX_HATCH_LEVEL) {
            level.playSound(null, pos, SoundEvents.TURTLE_EGG_CRACK, SoundSource.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
            level.setBlock(pos, state.setValue(HATCH, hatch + 1), 2);
            return;
        }

        level.playSound(null, pos, SoundEvents.TURTLE_EGG_HATCH, SoundSource.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
        level.removeBlock(pos, false);
        int count = state.getValue(EGGS);
        for (int i = 0; i < count; i++) {
            level.levelEvent(2001, pos, getId(state));
            hatchOne(level, pos, i);
        }
    }

    // Copy of TurtleEggBlock's private timing check (same day-time window + 1-in-500 fallback) —
    // not accessible to override piecemeal since the original method is private.
    private boolean shouldUpdateHatchLevel(Level level) {
        float timeOfDay = level.getTimeOfDay(1.0F);
        if (timeOfDay < 0.69F && timeOfDay > 0.65F) return true;
        return level.random.nextInt(500) == 0;
    }

    private void hatchOne(ServerLevel level, BlockPos pos, int index) {
        EntityType<?> type = entityType.get();
        if (type == null) return;
        Entity entity = type.create(level);
        if (!(entity instanceof Mob mob)) return;

        mob.finalizeSpawn(level, level.getCurrentDifficultyAt(pos), MobSpawnType.SPAWN_EGG, null, null);
        if (babyOnHatch && mob instanceof AgeableMob ageable) {
            ageable.setAge(-24000);
        }
        mob.moveTo(pos.getX() + 0.3 + index * 0.2, pos.getY(), pos.getZ() + 0.3, 0.0F, 0.0F);
        level.addFreshEntity(mob);
    }
}
