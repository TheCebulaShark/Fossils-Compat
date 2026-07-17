package org.blahajenjoyer.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
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
 * A placeable, ticking egg block that hatches into an entity after a while, same idea as vanilla's
 * turtle/sniffer eggs. Unlike FAR's own DinosaurEgg, the species isn't stored in any NBT/network field —
 * it's baked into which block occupies the position, since each species gets its own Block instance.
 * That sidesteps the enum-serialization dead end documented for FAR's own dino eggs.
 */
public class IncubatingEggBlock extends Block {

    public static final IntegerProperty HATCH = IntegerProperty.create("hatch", 0, 2);
    private static final VoxelShape SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 7.0, 13.0);

    private final Supplier<EntityType<?>> entityType;
    private final boolean babyOnHatch;

    public IncubatingEggBlock(Supplier<EntityType<?>> entityType, boolean babyOnHatch, BlockBehaviour.Properties properties) {
        super(properties);
        this.entityType = entityType;
        this.babyOnHatch = babyOnHatch;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HATCH);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
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
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int hatch = state.getValue(HATCH);
        if (hatch < 2) {
            if (random.nextInt(3) == 0) {
                level.setBlock(pos, state.setValue(HATCH, hatch + 1), 2);
                level.playSound(null, pos, SoundEvents.TURTLE_EGG_CRACK, SoundSource.BLOCKS,
                    0.7F, 0.9F + random.nextFloat() * 0.2F);
            }
        } else if (random.nextInt(3) == 0) {
            hatch(level, pos);
        }
    }

    private void hatch(ServerLevel level, BlockPos pos) {
        EntityType<?> type = entityType.get();
        if (type == null) return;
        Entity entity = type.create(level);
        if (!(entity instanceof Mob mob)) return;

        mob.finalizeSpawn(level, level.getCurrentDifficultyAt(pos), MobSpawnType.SPAWN_EGG, null, null);
        if (babyOnHatch && mob instanceof AgeableMob ageable) {
            ageable.setAge(-24000);
        }
        mob.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.0F, 0.0F);
        level.addFreshEntity(mob);

        level.removeBlock(pos, false);
        level.playSound(null, pos, SoundEvents.TURTLE_EGG_HATCH, SoundSource.BLOCKS,
            0.7F, 0.9F + level.random.nextFloat() * 0.2F);
    }
}
