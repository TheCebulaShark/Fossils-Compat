package org.blahajenjoyer.compat.naturalist;

import com.starfish_studios.naturalist.core.registry.NaturalistEntityTypes;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import org.blahajenjoyer.compat.naturalist.entity.ThrownNaturalistBirdEgg;
import org.blahajenjoyer.item.ThrowableBirdEggItem;
import org.blahajenjoyer.util.FossilsCompatUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class NaturalistCompat {

    public static EntityType<ThrownNaturalistBirdEgg> THROWN_NATURALIST_BIRD_EGG;

    public static Item BLUE_JAY_DNA;
    public static Item BLUE_JAY_EGG;
    public static Item CARDINAL_DNA;
    public static Item CARDINAL_EGG;
    public static Item CANARY_DNA;
    public static Item CANARY_EGG;
    public static Item FINCH_DNA;
    public static Item FINCH_EGG;
    public static Item SPARROW_DNA;
    public static Item SPARROW_EGG;
    public static Item ROBIN_DNA;
    public static Item ROBIN_EGG;
    public static Item VULTURE_DNA;
    public static Item VULTURE_EGG;
    public static Item DUCK_DNA;
    public static Item DUCK_EGG;

    private static final Map<String, Supplier<EntityType<?>>> ENTITY_SUPPLIERS = new HashMap<>();
    private static final Map<String, Item> EGG_BY_KEY = new HashMap<>();
    private static final Set<String> BABY_ON_HATCH = Set.of("duck");

    public static EntityType<?> getEntityType(String key) {
        Supplier<EntityType<?>> supplier = ENTITY_SUPPLIERS.get(key);
        return supplier != null ? supplier.get() : null;
    }

    public static Item getEggByKey(String key) {
        return EGG_BY_KEY.getOrDefault(key, BLUE_JAY_EGG);
    }

    public static boolean isBabyOnHatch(String key) {
        return BABY_ON_HATCH.contains(key);
    }

    public static void register() {
        THROWN_NATURALIST_BIRD_EGG = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation("fossils_compat", "thrown_naturalist_bird_egg"),
            EntityType.Builder.<ThrownNaturalistBirdEgg>of(ThrownNaturalistBirdEgg::new, MobCategory.MISC)
                .sized(0.25F, 0.25F)
                .clientTrackingRange(4)
                .updateInterval(10)
                .build("thrown_naturalist_bird_egg")
        );

        BLUE_JAY_DNA  = FossilsCompatUtil.registerItem("naturalist/blue_jay_dna",  new Item(new Item.Properties()));
        BLUE_JAY_EGG  = registerBirdEgg("naturalist/blue_jay_egg",  "blue_jay",  () -> NaturalistEntityTypes.BLUEJAY.get());
        CARDINAL_DNA  = FossilsCompatUtil.registerItem("naturalist/cardinal_dna",  new Item(new Item.Properties()));
        CARDINAL_EGG  = registerBirdEgg("naturalist/cardinal_egg",  "cardinal",  () -> NaturalistEntityTypes.CARDINAL.get());
        CANARY_DNA    = FossilsCompatUtil.registerItem("naturalist/canary_dna",    new Item(new Item.Properties()));
        CANARY_EGG    = registerBirdEgg("naturalist/canary_egg",    "canary",    () -> NaturalistEntityTypes.CANARY.get());
        FINCH_DNA     = FossilsCompatUtil.registerItem("naturalist/finch_dna",     new Item(new Item.Properties()));
        FINCH_EGG     = registerBirdEgg("naturalist/finch_egg",     "finch",     () -> NaturalistEntityTypes.FINCH.get());
        SPARROW_DNA   = FossilsCompatUtil.registerItem("naturalist/sparrow_dna",   new Item(new Item.Properties()));
        SPARROW_EGG   = registerBirdEgg("naturalist/sparrow_egg",   "sparrow",   () -> NaturalistEntityTypes.SPARROW.get());
        ROBIN_DNA     = FossilsCompatUtil.registerItem("naturalist/robin_dna",     new Item(new Item.Properties()));
        ROBIN_EGG     = registerBirdEgg("naturalist/robin_egg",     "robin",     () -> NaturalistEntityTypes.ROBIN.get());
        VULTURE_DNA   = FossilsCompatUtil.registerItem("naturalist/vulture_dna",   new Item(new Item.Properties()));
        VULTURE_EGG   = registerBirdEgg("naturalist/vulture_egg",   "vulture",   () -> NaturalistEntityTypes.VULTURE.get());
        DUCK_DNA      = FossilsCompatUtil.registerItem("naturalist/duck_dna",      new Item(new Item.Properties()));
        DUCK_EGG      = registerBirdEgg("naturalist/duck_egg",      "duck",      () -> NaturalistEntityTypes.DUCK.get());

        ItemGroupEvents.modifyEntriesEvent(FossilsCompatUtil.FOSSIL_MOB_TAB).register(entries -> {
            entries.accept(BLUE_JAY_DNA);
            entries.accept(BLUE_JAY_EGG);
            entries.accept(CARDINAL_DNA);
            entries.accept(CARDINAL_EGG);
            entries.accept(CANARY_DNA);
            entries.accept(CANARY_EGG);
            entries.accept(FINCH_DNA);
            entries.accept(FINCH_EGG);
            entries.accept(SPARROW_DNA);
            entries.accept(SPARROW_EGG);
            entries.accept(ROBIN_DNA);
            entries.accept(ROBIN_EGG);
            entries.accept(VULTURE_DNA);
            entries.accept(VULTURE_EGG);
            entries.accept(DUCK_DNA);
            entries.accept(DUCK_EGG);
        });
    }

    private static Item registerBirdEgg(String id, String key, Supplier<EntityType<?>> supplier) {
        ENTITY_SUPPLIERS.put(key, supplier);
        Item egg = FossilsCompatUtil.registerItem(id, new ThrowableBirdEggItem(
            (level, player) -> new ThrownNaturalistBirdEgg(level, player, key),
            new Item.Properties().stacksTo(16)));
        EGG_BY_KEY.put(key, egg);
        return egg;
    }
}
