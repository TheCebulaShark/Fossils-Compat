package org.blahajenjoyer.compat.vanillabackport;

import com.blackgear.vanillabackport.common.registries.ModEntities;
import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.PrehistoricMobType;
import com.github.teamfossilsarcheology.fossil.item.MammalEmbryoItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import org.blahajenjoyer.compat.vanillabackport.entity.ThrownVariantChickenEgg;
import org.blahajenjoyer.compat.vanillabackport.item.VariantChickenEggItem;
import org.blahajenjoyer.config.FossilsCompatConfig;
import org.blahajenjoyer.util.FossilsCompatUtil;
import org.blahajenjoyer.util.SimpleEntityInfo;

import java.util.ArrayList;
import java.util.List;

public class VanillaBackportCompat {

    public static final String MOD_ID = "vanillabackport";
    public static final List<String> ANIMAL_KEYS = List.of("armadillo", "cold_chicken", "warm_chicken");

    private static final List<Item> TAB_ITEMS = new ArrayList<>();

    public static EntityType<ThrownVariantChickenEgg> THROWN_VARIANT_CHICKEN_EGG;

    public static Item ARMADILLO_DNA;
    public static Item ARMADILLO_EMBRYO;
    public static Item COLD_CHICKEN_DNA;
    public static Item COLD_CHICKEN_EGG;
    public static Item WARM_CHICKEN_DNA;
    public static Item WARM_CHICKEN_EGG;

    private static boolean enabled(String key) {
        return FossilsCompatConfig.isEnabled(MOD_ID, key);
    }

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

        if (enabled("armadillo")) {
            ARMADILLO_DNA    = FossilsCompatUtil.registerItem("vanillabackport/armadillo_dna",    new Item(new Item.Properties()));
            ARMADILLO_EMBRYO = FossilsCompatUtil.registerItem("vanillabackport/armadillo_embryo", new MammalEmbryoItem(new SimpleEntityInfo(
                "VANILLABACKPORT_ARMADILLO", () -> ModEntities.ARMADILLO.get(), PrehistoricMobType.MAMMAL,
                () -> Component.translatable("entity.minecraft.armadillo"), ARMADILLO_DNA)));
            TAB_ITEMS.add(ARMADILLO_DNA);
            TAB_ITEMS.add(ARMADILLO_EMBRYO);
        }
        if (enabled("cold_chicken")) {
            COLD_CHICKEN_DNA = FossilsCompatUtil.registerItem("vanillabackport/cold_chicken_dna", new Item(new Item.Properties()));
            COLD_CHICKEN_EGG = FossilsCompatUtil.registerItem("vanillabackport/cold_chicken_egg", new VariantChickenEggItem("cold", new Item.Properties().stacksTo(16)));
            TAB_ITEMS.add(COLD_CHICKEN_DNA);
            TAB_ITEMS.add(COLD_CHICKEN_EGG);
        }
        if (enabled("warm_chicken")) {
            WARM_CHICKEN_DNA = FossilsCompatUtil.registerItem("vanillabackport/warm_chicken_dna", new Item(new Item.Properties()));
            WARM_CHICKEN_EGG = FossilsCompatUtil.registerItem("vanillabackport/warm_chicken_egg", new VariantChickenEggItem("warm", new Item.Properties().stacksTo(16)));
            TAB_ITEMS.add(WARM_CHICKEN_DNA);
            TAB_ITEMS.add(WARM_CHICKEN_EGG);
        }

        ItemGroupEvents.modifyEntriesEvent(FossilsCompatUtil.FOSSIL_MOB_TAB).register(entries -> TAB_ITEMS.forEach(entries::accept));
    }
}
