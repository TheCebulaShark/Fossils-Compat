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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.function.Supplier;

/**
 * A turtle-egg-a-like: small, stacks up to 4 per block, cracks when trampled/landed on.
 * canBeReplaced/getStateForPlacement stacking, playerDestroy, and getShape are inherited
 * unchanged from vanilla's TurtleEggBlock. randomTick/onPlace/stepOn/fallOn are all overridden,
 * since vanilla's versions either hatch/spawn a literal Turtle or gate on `state.is(Blocks.
 * TURTLE_EGG)` / a hardcoded sand check — neither works for a different Block instance, so
 * those had to be reimplemented with `state.is(this)` and a configurable (or absent) substrate
 * tag instead — e.g. one species might need sand, another might need the same "hot blocks" tag
 * {@link LargeSingleEggBlock} uses, most need nothing at all.
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

    // Vanilla TurtleEggBlock's inherited onPlace fires this landing particle only on real sand
    // (a hardcoded call to the static onSand check) — overridden so it follows our own
    // (possibly absent) substrate requirement instead.
    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (hasRequiredSubstrate(level, pos) && !level.isClientSide) {
            level.levelEvent(2005, pos, 0);
        }
    }

    // Vanilla TurtleEggBlock's inherited stepOn/fallOn DO get called on our block (they're not
    // overridden here), but their private destroyEgg gate checks `state.is(Blocks.TURTLE_EGG)` —
    // literally the vanilla block singleton, never true for our own Block instance — so trampling
    // silently no-ops on any non-vanilla TurtleEggBlock subclass. Overridden here (small tier only,
    // per user request) with the same logic but checking `state.is(this)` instead.
    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.isSteppingCarefully()) {
            destroyEgg(level, state, pos, entity, 100);
        }
        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!(entity instanceof Zombie)) {
            destroyEgg(level, state, pos, entity, 3);
        }
        super.fallOn(level, state, pos, entity, fallDistance);
    }

    private void destroyEgg(Level level, BlockState state, BlockPos pos, Entity entity, int chance) {
        if (canDestroyEgg(level, entity) && !level.isClientSide && level.random.nextInt(chance) == 0 && state.is(this)) {
            decreaseEggs(level, pos, state);
        }
    }

    private void decreaseEggs(Level level, BlockPos pos, BlockState state) {
        level.playSound(null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + level.random.nextFloat() * 0.2F);
        int count = state.getValue(EGGS);
        if (count <= 1) {
            level.destroyBlock(pos, false);
        } else {
            level.setBlock(pos, state.setValue(EGGS, count - 1), 2);
            level.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(state));
            level.levelEvent(2001, pos, getId(state));
        }
    }

    private boolean canDestroyEgg(Level level, Entity entity) {
        if (entity.getType().is(ReptileEggBlocks.SMALL_EGG_IMMUNE)) {
            return false;
        }
        if (!(entity instanceof LivingEntity living)) {
            return false;
        }
        // Babies don't crack eggs — except zombie-family babies (baby zombie/husk/drowned/zombie
        // villager all extend Zombie), which are hostile toward eggs regardless of age.
        if (living.isBaby() && !(entity instanceof Zombie)) {
            return false;
        }
        return entity instanceof Player || level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING);
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
