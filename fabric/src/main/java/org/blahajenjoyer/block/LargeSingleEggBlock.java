package org.blahajenjoyer.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SnifferEggBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

/**
 * A sniffer-egg-a-like: one large egg per block — most behavior inherited unchanged from
 * vanilla's SnifferEggBlock (isPathfindable, getShape). tick is overridden to hatch our own
 * species, and onPlace is overridden because vanilla's hatch-boost check
 * (SnifferEggBlock.hatchBoost) is a static call to the hardcoded moss tag — not overridable by
 * subclassing — so a configurable block tag is used instead.
 */
@SuppressWarnings("deprecation")
public class LargeSingleEggBlock extends SnifferEggBlock {

    private static final int REGULAR_HATCH_TIME_TICKS = 24000;
    private static final int BOOSTED_HATCH_TIME_TICKS = 12000;
    private static final int RANDOM_HATCH_OFFSET_TICKS = 300;

    private final Supplier<EntityType<?>> entityType;
    private final boolean babyOnHatch;
    private final TagKey<Block> hatchBoostTag;

    public LargeSingleEggBlock(Supplier<EntityType<?>> entityType, boolean babyOnHatch, TagKey<Block> hatchBoostTag, BlockBehaviour.Properties properties) {
        super(properties);
        this.entityType = entityType;
        this.babyOnHatch = babyOnHatch;
        this.hatchBoostTag = hatchBoostTag;
    }

    private boolean isHatchBoosted(LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(hatchBoostTag);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        boolean boosted = isHatchBoosted(level, pos);
        if (!level.isClientSide() && boosted) {
            level.levelEvent(3009, pos, 0);
        }
        int totalTicks = boosted ? BOOSTED_HATCH_TIME_TICKS : REGULAR_HATCH_TIME_TICKS;
        int delay = totalTicks / 3;
        level.gameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Context.of(state));
        level.scheduleTick(pos, this, delay + level.random.nextInt(RANDOM_HATCH_OFFSET_TICKS));
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int hatch = getHatchLevel(state);
        if (hatch < MAX_HATCH_LEVEL) {
            level.playSound(null, pos, SoundEvents.SNIFFER_EGG_CRACK, SoundSource.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
            level.setBlock(pos, state.setValue(HATCH, hatch + 1), 2);
            return;
        }

        level.playSound(null, pos, SoundEvents.SNIFFER_EGG_HATCH, SoundSource.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
        level.destroyBlock(pos, false);

        EntityType<?> type = entityType.get();
        if (type == null) return;
        Entity entity = type.create(level);
        if (!(entity instanceof Mob mob)) return;

        mob.finalizeSpawn(level, level.getCurrentDifficultyAt(pos), MobSpawnType.SPAWN_EGG, null, null);
        if (babyOnHatch && mob instanceof AgeableMob ageable) {
            ageable.setAge(-24000);
        }
        Vec3 center = pos.getCenter();
        mob.moveTo(center.x(), center.y(), center.z(), Mth.wrapDegrees(level.random.nextFloat() * 360.0F), 0.0F);
        level.addFreshEntity(mob);
    }
}
