package org.blahajenjoyer.util;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class FossilsCompatUtil {

    public static final ResourceKey<CreativeModeTab> FOSSIL_MOB_TAB = ResourceKey.create(
        Registries.CREATIVE_MODE_TAB,
        new ResourceLocation("fossil", "fa_mob_item_tab")
    );

    public static Item registerItem(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("fossils_compat", id), item);
    }
}
