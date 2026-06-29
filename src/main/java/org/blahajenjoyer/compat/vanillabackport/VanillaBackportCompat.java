package org.blahajenjoyer.compat.vanillabackport;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.blahajenjoyer.compat.vanillabackport.entity.ThrownVariantChickenEgg;
import org.blahajenjoyer.compat.vanillabackport.item.VariantChickenEggItem;

public class VanillaBackportCompat {

    public static EntityType<ThrownVariantChickenEgg> THROWN_VARIANT_CHICKEN_EGG;

    public static Item ARMADILLO_DNA;
    public static Item ARMADILLO_EMBRYO;
    public static Item COLD_CHICKEN_DNA;
    public static Item COLD_CHICKEN_EGG;
    public static Item WARM_CHICKEN_DNA;
    public static Item WARM_CHICKEN_EGG;

    public static void register() {
        THROWN_VARIANT_CHICKEN_EGG = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation("fossils_compat", "thrown_variant_chicken_egg"),
            EntityType.Builder.<ThrownVariantChickenEgg>of(ThrownVariantChickenEgg::new, MobCategory.MISC)
                .sized(0.25F, 0.25F)
                .clientTrackingRange(4)
                .updateInterval(10)
                .build("thrown_variant_chicken_egg")
        );

        ARMADILLO_DNA = Registry.register(BuiltInRegistries.ITEM,
            new ResourceLocation("fossils_compat", "armadillo_dna"),
            new Item(new Item.Properties()));

        ARMADILLO_EMBRYO = Registry.register(BuiltInRegistries.ITEM,
            new ResourceLocation("fossils_compat", "armadillo_embryo"),
            new Item(new Item.Properties()));

        COLD_CHICKEN_DNA = Registry.register(BuiltInRegistries.ITEM,
            new ResourceLocation("fossils_compat", "cold_chicken_dna"),
            new Item(new Item.Properties()));

        COLD_CHICKEN_EGG = Registry.register(BuiltInRegistries.ITEM,
            new ResourceLocation("fossils_compat", "cold_chicken_egg"),
            new VariantChickenEggItem("cold", new Item.Properties().stacksTo(16)));

        WARM_CHICKEN_DNA = Registry.register(BuiltInRegistries.ITEM,
            new ResourceLocation("fossils_compat", "warm_chicken_dna"),
            new Item(new Item.Properties()));

        WARM_CHICKEN_EGG = Registry.register(BuiltInRegistries.ITEM,
            new ResourceLocation("fossils_compat", "warm_chicken_egg"),
            new VariantChickenEggItem("warm", new Item.Properties().stacksTo(16)));

        ResourceKey<CreativeModeTab> fossilMobTab = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            new ResourceLocation("fossil", "fa_mob_item_tab")
        );
        ItemGroupEvents.modifyEntriesEvent(fossilMobTab).register(entries -> {
            entries.accept(ARMADILLO_DNA);
            entries.accept(ARMADILLO_EMBRYO);
            entries.accept(COLD_CHICKEN_DNA);
            entries.accept(COLD_CHICKEN_EGG);
            entries.accept(WARM_CHICKEN_DNA);
            entries.accept(WARM_CHICKEN_EGG);
        });
    }
}
