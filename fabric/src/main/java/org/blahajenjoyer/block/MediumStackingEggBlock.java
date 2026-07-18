package org.blahajenjoyer.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

/**
 * A custom egg size between a turtle egg and a sniffer egg, for reptiles too bulky for the
 * small tier but not sniffer-sized. Stacks up to 2 per block (vanilla turtle-style clustering,
 * capped lower) and hatches via plain random ticks. Optionally requires the block below to
 * match a tag (e.g. cold surfaces for a frost-themed species) — {@code null} means no
 * requirement.
 */
@SuppressWarnings("deprecation")
public class MediumStackingEggBlock extends Block {

    public static final int MAX_HATCH_LEVEL = 2;
    public static final int MIN_EGGS = 1;
    public static final int MAX_EGGS = 2;
    public static final IntegerProperty HATCH = IntegerProperty.create("hatch", 0, MAX_HATCH_LEVEL);
    public static final IntegerProperty EGGS = IntegerProperty.create("eggs", MIN_EGGS, MAX_EGGS);

    private static final VoxelShape ONE_EGG_AABB = Block.box(2.0, 0.0, 2.0, 14.0, 10.0, 14.0);
    private static final VoxelShape TWO_EGGS_AABB = Block.box(1.0, 0.0, 1.0, 15.0, 10.0, 15.0);

    private final Supplier<EntityType<?>> entityType;
    private final boolean babyOnHatch;
    private final TagKey<Block> requiredSubstrate;

    /** @param requiredSubstrate block tag the block below must match to progress hatching, or {@code null} for no requirement */
    public MediumStackingEggBlock(Supplier<EntityType<?>> entityType, boolean babyOnHatch, TagKey<Block> requiredSubstrate, BlockBehaviour.Properties properties) {
        super(properties);
        this.entityType = entityType;
        this.babyOnHatch = babyOnHatch;
        this.requiredSubstrate = requiredSubstrate;
    }

    private boolean hasRequiredSubstrate(BlockGetter level, BlockPos pos) {
        return requiredSubstrate == null || level.getBlockState(pos.below()).is(requiredSubstrate);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HATCH, EGGS);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(EGGS) > 1 ? TWO_EGGS_AABB : ONE_EGG_AABB;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.DOWN && !this.canSurvive(state, level, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return !context.isSecondaryUseActive() && context.getItemInHand().is(this.asItem()) && state.getValue(EGGS) < MAX_EGGS
            || super.canBeReplaced(state, context);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState clicked = context.getLevel().getBlockState(context.getClickedPos());
        if (clicked.is(this)) {
            return clicked.setValue(EGGS, Math.min(MAX_EGGS, clicked.getValue(EGGS) + 1));
        }
        return super.getStateForPlacement(context);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!hasRequiredSubstrate(level, pos)) return;

        int hatch = state.getValue(HATCH);
        if (hatch < MAX_HATCH_LEVEL) {
            if (random.nextInt(3) == 0) {
                level.setBlock(pos, state.setValue(HATCH, hatch + 1), 2);
                level.playSound(null, pos, SoundEvents.TURTLE_EGG_CRACK, SoundSource.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
            }
        } else if (random.nextInt(3) == 0) {
            hatch(level, pos, state);
        }
    }

    private void hatch(ServerLevel level, BlockPos pos, BlockState state) {
        EntityType<?> type = entityType.get();
        if (type == null) return;

        int count = state.getValue(EGGS);
        for (int i = 0; i < count; i++) {
            Entity entity = type.create(level);
            if (!(entity instanceof Mob mob)) continue;
            mob.finalizeSpawn(level, level.getCurrentDifficultyAt(pos), MobSpawnType.SPAWN_EGG, null, null);
            if (babyOnHatch && mob instanceof AgeableMob ageable) {
                ageable.setAge(-24000);
            }
            mob.moveTo(pos.getX() + 0.3 + i * 0.4, pos.getY(), pos.getZ() + 0.3, 0.0F, 0.0F);
            level.addFreshEntity(mob);
        }

        level.removeBlock(pos, false);
        level.playSound(null, pos, SoundEvents.TURTLE_EGG_HATCH, SoundSource.BLOCKS, 0.7F, 0.9F + level.random.nextFloat() * 0.2F);
    }
}
