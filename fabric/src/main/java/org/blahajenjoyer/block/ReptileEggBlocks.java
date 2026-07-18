package org.blahajenjoyer.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.blahajenjoyer.util.FossilsCompatUtil;

import java.util.function.Supplier;

public class ReptileEggBlocks {

    /** Blocks that hatch a {@link #registerLarge} egg faster when placed below it — "hot" surfaces for a lava-adjacent species like the laviathan, not vanilla's moss. Populated via {@code data/fossils_compat/tags/blocks/lava_egg_hatch_boost.json}. */
    public static final TagKey<Block> LAVA_EGG_HATCH_BOOST = TagKey.create(Registries.BLOCK, new ResourceLocation("fossils_compat", "lava_egg_hatch_boost"));

    /** Required substrate for a frost-themed egg (e.g. the froststalker) — ice/snow surfaces. Populated via {@code data/fossils_compat/tags/blocks/cold_egg_substrate.json}. */
    public static final TagKey<Block> COLD_EGG_SUBSTRATE = TagKey.create(Registries.BLOCK, new ResourceLocation("fossils_compat", "cold_egg_substrate"));

    /** Mobs that never crack a {@link SmallStackingEggBlock} egg by stepping/falling on it, regardless of babyness or mobGriefing — vanilla turtles and bats. Populated via {@code data/fossils_compat/tags/entity_types/small_egg_immune.json}. */
    public static final TagKey<EntityType<?>> SMALL_EGG_IMMUNE = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("fossils_compat", "small_egg_immune"));

    /** No substrate requirement — hatches on any sturdy block. */
    public static Item registerSmall(String id, Supplier<EntityType<?>> entityType, boolean babyOnHatch) {
        return registerSmall(id, entityType, babyOnHatch, null);
    }

    /** @param requiredSubstrate block tag the block below must match to progress hatching, or {@code null} for no requirement */
    public static Item registerSmall(String id, Supplier<EntityType<?>> entityType, boolean babyOnHatch, TagKey<Block> requiredSubstrate) {
        return register(id, new SmallStackingEggBlock(entityType, babyOnHatch, requiredSubstrate, BlockBehaviour.Properties.copy(Blocks.TURTLE_EGG)));
    }

    /** No substrate requirement — hatches on any sturdy block. */
    public static Item registerMedium(String id, Supplier<EntityType<?>> entityType, boolean babyOnHatch) {
        return registerMedium(id, entityType, babyOnHatch, null);
    }

    /** @param requiredSubstrate block tag the block below must match to progress hatching, or {@code null} for no requirement */
    public static Item registerMedium(String id, Supplier<EntityType<?>> entityType, boolean babyOnHatch, TagKey<Block> requiredSubstrate) {
        return register(id, new MediumStackingEggBlock(entityType, babyOnHatch, requiredSubstrate, BlockBehaviour.Properties.copy(Blocks.TURTLE_EGG)));
    }

    public static Item registerLarge(String id, Supplier<EntityType<?>> entityType, boolean babyOnHatch, TagKey<Block> hatchBoostTag) {
        return register(id, new LargeSingleEggBlock(entityType, babyOnHatch, hatchBoostTag, BlockBehaviour.Properties.copy(Blocks.SNIFFER_EGG)));
    }

    private static Item register(String id, Block block) {
        FossilsCompatUtil.registerBlock(id, block);
        return FossilsCompatUtil.registerItem(id, new BlockItem(block, new Item.Properties()));
    }
}
