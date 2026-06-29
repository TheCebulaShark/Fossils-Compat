package org.blahajenjoyer.compat.crittersandcompanions;

import com.github.eterdelta.crittersandcompanions.registry.CACEntities;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import org.blahajenjoyer.compat.crittersandcompanions.entity.ThrownCACBirdEgg;
import org.blahajenjoyer.item.ThrowableBirdEggItem;
import org.blahajenjoyer.util.FossilsCompatUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class CrittersAndCompanionsCompat {

    public static EntityType<ThrownCACBirdEgg> THROWN_CAC_BIRD_EGG;

    public static Item SHIMA_ENAGA_DNA;
    public static Item SHIMA_ENAGA_EGG;

    private static final Set<String> BABY_ON_HATCH = Set.of();
    private static final Map<String, Supplier<EntityType<?>>> ENTITY_SUPPLIERS = new HashMap<>();
    private static final Map<String, Item> EGG_BY_KEY = new HashMap<>();

    public static EntityType<?> getEntityType(String key) {
        Supplier<EntityType<?>> supplier = ENTITY_SUPPLIERS.get(key);
        return supplier != null ? supplier.get() : null;
    }

    public static Item getEggByKey(String key) {
        return EGG_BY_KEY.getOrDefault(key, SHIMA_ENAGA_EGG);
    }

    public static boolean isBabyOnHatch(String key) {
        return BABY_ON_HATCH.contains(key);
    }

    public static void register() {
        THROWN_CAC_BIRD_EGG = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation("fossils_compat", "thrown_cac_bird_egg"),
            EntityType.Builder.<ThrownCACBirdEgg>of(ThrownCACBirdEgg::new, MobCategory.MISC)
                .sized(0.25F, 0.25F)
                .clientTrackingRange(4)
                .updateInterval(10)
                .build("thrown_cac_bird_egg")
        );

        SHIMA_ENAGA_DNA = FossilsCompatUtil.registerItem("crittersandcompanions/shima_enaga_dna", new Item(new Item.Properties()));
        SHIMA_ENAGA_EGG = registerBirdEgg("crittersandcompanions/shima_enaga_egg", "shima_enaga", () -> CACEntities.SHIMA_ENAGA.get());

        ItemGroupEvents.modifyEntriesEvent(FossilsCompatUtil.FOSSIL_MOB_TAB).register(entries -> {
            entries.accept(SHIMA_ENAGA_DNA);
            entries.accept(SHIMA_ENAGA_EGG);
        });
    }

    private static Item registerBirdEgg(String id, String key, Supplier<EntityType<?>> supplier) {
        ENTITY_SUPPLIERS.put(key, supplier);
        Item egg = FossilsCompatUtil.registerItem(id, new ThrowableBirdEggItem(
            (level, player) -> new ThrownCACBirdEgg(level, player, key),
            new Item.Properties().stacksTo(16)));
        EGG_BY_KEY.put(key, egg);
        return egg;
    }
}
