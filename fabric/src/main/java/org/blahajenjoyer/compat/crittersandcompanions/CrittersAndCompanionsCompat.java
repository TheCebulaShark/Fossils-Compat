package org.blahajenjoyer.compat.crittersandcompanions;

import com.github.eterdelta.crittersandcompanions.registry.CACEntities;
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
import org.blahajenjoyer.compat.crittersandcompanions.entity.ThrownCACBirdEgg;
import org.blahajenjoyer.item.SpawnEggLikeItem;
import org.blahajenjoyer.item.ThrowableBirdEggItem;
import org.blahajenjoyer.util.FossilsCompatUtil;
import org.blahajenjoyer.util.SimpleEntityInfo;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class CrittersAndCompanionsCompat {

    public static EntityType<ThrownCACBirdEgg> THROWN_CAC_BIRD_EGG;

    public static Item SHIMA_ENAGA_DNA;
    public static Item SHIMA_ENAGA_EGG;

    // Invertebrates / aquatics — DNA + spawn-egg-like Egg (adult on hatch)
    public static Item DRAGONFLY_DNA;
    public static Item DRAGONFLY_EGG;
    public static Item DUMBO_OCTOPUS_DNA;
    public static Item DUMBO_OCTOPUS_EGG;
    public static Item JUMPING_SPIDER_DNA;
    public static Item JUMPING_SPIDER_EGG;
    public static Item KOI_FISH_DNA;
    public static Item KOI_FISH_EGG;
    public static Item LEAF_INSECT_DNA;
    public static Item LEAF_INSECT_EGG;
    public static Item SEA_BUNNY_DNA;
    public static Item SEA_BUNNY_EGG;

    // Mammals — DNA + Embryo
    public static Item FERRET_DNA;
    public static Item FERRET_EMBRYO;
    public static Item OTTER_DNA;
    public static Item OTTER_EMBRYO;
    public static Item RED_PANDA_DNA;
    public static Item RED_PANDA_EMBRYO;

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

        DRAGONFLY_DNA      = FossilsCompatUtil.registerItem("crittersandcompanions/dragonfly_dna",      new Item(new Item.Properties()));
        DRAGONFLY_EGG      = registerCreatureEgg("crittersandcompanions/dragonfly_egg",      () -> CACEntities.DRAGONFLY.get());
        DUMBO_OCTOPUS_DNA  = FossilsCompatUtil.registerItem("crittersandcompanions/dumbo_octopus_dna",  new Item(new Item.Properties()));
        DUMBO_OCTOPUS_EGG  = registerCreatureEgg("crittersandcompanions/dumbo_octopus_egg",  () -> CACEntities.DUMBO_OCTOPUS.get());
        JUMPING_SPIDER_DNA = FossilsCompatUtil.registerItem("crittersandcompanions/jumping_spider_dna", new Item(new Item.Properties()));
        JUMPING_SPIDER_EGG = registerCreatureEgg("crittersandcompanions/jumping_spider_egg", () -> CACEntities.JUMPING_SPIDER.get());
        KOI_FISH_DNA       = FossilsCompatUtil.registerItem("crittersandcompanions/koi_fish_dna",       new Item(new Item.Properties()));
        KOI_FISH_EGG       = registerCreatureEgg("crittersandcompanions/koi_fish_egg",       () -> CACEntities.KOI_FISH.get());
        LEAF_INSECT_DNA    = FossilsCompatUtil.registerItem("crittersandcompanions/leaf_insect_dna",    new Item(new Item.Properties()));
        LEAF_INSECT_EGG    = registerCreatureEgg("crittersandcompanions/leaf_insect_egg",    () -> CACEntities.LEAF_INSECT.get());
        SEA_BUNNY_DNA      = FossilsCompatUtil.registerItem("crittersandcompanions/sea_bunny_dna",      new Item(new Item.Properties()));
        SEA_BUNNY_EGG      = registerCreatureEgg("crittersandcompanions/sea_bunny_egg",      () -> CACEntities.SEA_BUNNY.get());

        FERRET_DNA    = FossilsCompatUtil.registerItem("crittersandcompanions/ferret_dna",    new Item(new Item.Properties()));
        FERRET_EMBRYO = registerMammalEmbryo("crittersandcompanions/ferret_embryo", "ferret", () -> CACEntities.FERRET.get(), FERRET_DNA);
        OTTER_DNA     = FossilsCompatUtil.registerItem("crittersandcompanions/otter_dna",     new Item(new Item.Properties()));
        OTTER_EMBRYO  = registerMammalEmbryo("crittersandcompanions/otter_embryo", "otter", () -> CACEntities.OTTER.get(), OTTER_DNA);
        RED_PANDA_DNA    = FossilsCompatUtil.registerItem("crittersandcompanions/red_panda_dna",    new Item(new Item.Properties()));
        RED_PANDA_EMBRYO = registerMammalEmbryo("crittersandcompanions/red_panda_embryo", "red_panda", () -> CACEntities.RED_PANDA.get(), RED_PANDA_DNA);

        ItemGroupEvents.modifyEntriesEvent(FossilsCompatUtil.FOSSIL_MOB_TAB).register(entries -> {
            entries.accept(DRAGONFLY_DNA);
            entries.accept(DRAGONFLY_EGG);
            entries.accept(DUMBO_OCTOPUS_DNA);
            entries.accept(DUMBO_OCTOPUS_EGG);
            entries.accept(JUMPING_SPIDER_DNA);
            entries.accept(JUMPING_SPIDER_EGG);
            entries.accept(KOI_FISH_DNA);
            entries.accept(KOI_FISH_EGG);
            entries.accept(LEAF_INSECT_DNA);
            entries.accept(LEAF_INSECT_EGG);
            entries.accept(SEA_BUNNY_DNA);
            entries.accept(SEA_BUNNY_EGG);
            entries.accept(SHIMA_ENAGA_DNA);
            entries.accept(SHIMA_ENAGA_EGG);
            entries.accept(FERRET_DNA);
            entries.accept(FERRET_EMBRYO);
            entries.accept(OTTER_DNA);
            entries.accept(OTTER_EMBRYO);
            entries.accept(RED_PANDA_DNA);
            entries.accept(RED_PANDA_EMBRYO);
        });
    }

    private static Item registerCreatureEgg(String id, Supplier<EntityType<?>> supplier) {
        return FossilsCompatUtil.registerItem(id, new SpawnEggLikeItem(supplier, false, new Item.Properties().stacksTo(16)));
    }

    private static Item registerBirdEgg(String id, String key, Supplier<EntityType<?>> supplier) {
        ENTITY_SUPPLIERS.put(key, supplier);
        Item egg = FossilsCompatUtil.registerItem(id, new ThrowableBirdEggItem(
            (level, player) -> new ThrownCACBirdEgg(level, player, key),
            new Item.Properties().stacksTo(16)));
        EGG_BY_KEY.put(key, egg);
        return egg;
    }

    private static Item registerMammalEmbryo(String id, String key, Supplier<EntityType<?>> entityType, Item dnaItem) {
        return FossilsCompatUtil.registerItem(id, new MammalEmbryoItem(new SimpleEntityInfo(
            "CRITTERSANDCOMPANIONS_" + key.toUpperCase(Locale.ROOT), entityType, PrehistoricMobType.MAMMAL,
            () -> Component.translatable("entity.crittersandcompanions." + key), dnaItem)));
    }
}
