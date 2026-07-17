package org.blahajenjoyer.compat.naturalist;

import com.starfish_studios.naturalist.core.registry.NaturalistEntityTypes;
import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.PrehistoricMobType;
import com.github.teamfossilsarcheology.fossil.item.MammalEmbryoItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.blahajenjoyer.block.IncubatingEggBlock;
import org.blahajenjoyer.compat.naturalist.entity.ThrownNaturalistBirdEgg;
import org.blahajenjoyer.config.FossilsCompatConfig;
import org.blahajenjoyer.item.SpawnEggLikeItem;
import org.blahajenjoyer.item.ThrowableBirdEggItem;
import org.blahajenjoyer.util.FossilsCompatUtil;
import org.blahajenjoyer.util.SimpleEntityInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class NaturalistCompat {

    public static final String MOD_ID = "naturalist";
    public static final List<String> ANIMAL_KEYS = List.of(
        "blue_jay", "cardinal", "canary", "finch", "sparrow", "robin", "vulture", "duck",
        "catfish", "bass", "firefly", "dragonfly",
        "alligator", "tortoise", "snail", "butterfly",
        "coral_snake", "snake", "rattlesnake", "lizard",
        "bear", "boar", "hippo", "giraffe", "elephant", "deer", "lion", "rhino", "zebra"
    );

    private static final List<Item> TAB_ITEMS = new ArrayList<>();

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

    // Fish / invertebrates — DNA + spawn-egg-like Egg (adult on hatch)
    public static Item CATFISH_DNA;
    public static Item CATFISH_EGG;
    public static Item BASS_DNA;
    public static Item BASS_EGG;
    public static Item FIREFLY_DNA;
    public static Item FIREFLY_EGG;
    public static Item DRAGONFLY_DNA;
    public static Item DRAGONFLY_EGG;

    // DNA only — mob already has an in-game egg or equivalent
    public static Item ALLIGATOR_DNA;
    public static Item TORTOISE_DNA;
    public static Item SNAIL_DNA;
    public static Item BUTTERFLY_DNA;

    // Reptiles — DNA + Egg (placeable, hatches into a baby after a while, like a turtle/sniffer egg)
    public static Item CORAL_SNAKE_DNA;
    public static Item CORAL_SNAKE_EGG;
    public static Item SNAKE_DNA;
    public static Item SNAKE_EGG;
    public static Item RATTLESNAKE_DNA;
    public static Item RATTLESNAKE_EGG;
    public static Item LIZARD_DNA;
    public static Item LIZARD_EGG;

    // Mammals — DNA + Embryo
    public static Item BROWN_BEAR_DNA;
    public static Item BROWN_BEAR_EMBRYO;
    public static Item BOAR_DNA;
    public static Item BOAR_EMBRYO;
    public static Item HIPPO_DNA;
    public static Item HIPPO_EMBRYO;
    public static Item GIRAFFE_DNA;
    public static Item GIRAFFE_EMBRYO;
    public static Item ELEPHANT_DNA;
    public static Item ELEPHANT_EMBRYO;
    public static Item DEER_DNA;
    public static Item DEER_EMBRYO;
    public static Item LION_DNA;
    public static Item LION_EMBRYO;
    public static Item RHINO_DNA;
    public static Item RHINO_EMBRYO;
    public static Item ZEBRA_DNA;
    public static Item ZEBRA_EMBRYO;

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

    private static boolean enabled(String key) {
        return FossilsCompatConfig.isEnabled(MOD_ID, key);
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

        if (enabled("blue_jay")) {
            BLUE_JAY_DNA = FossilsCompatUtil.registerItem("naturalist/blue_jay_dna", new Item(new Item.Properties()));
            BLUE_JAY_EGG = registerBirdEgg("naturalist/blue_jay_egg", "blue_jay", () -> NaturalistEntityTypes.BLUEJAY.get());
            TAB_ITEMS.add(BLUE_JAY_DNA);
            TAB_ITEMS.add(BLUE_JAY_EGG);
        }
        if (enabled("cardinal")) {
            CARDINAL_DNA = FossilsCompatUtil.registerItem("naturalist/cardinal_dna", new Item(new Item.Properties()));
            CARDINAL_EGG = registerBirdEgg("naturalist/cardinal_egg", "cardinal", () -> NaturalistEntityTypes.CARDINAL.get());
            TAB_ITEMS.add(CARDINAL_DNA);
            TAB_ITEMS.add(CARDINAL_EGG);
        }
        if (enabled("canary")) {
            CANARY_DNA = FossilsCompatUtil.registerItem("naturalist/canary_dna", new Item(new Item.Properties()));
            CANARY_EGG = registerBirdEgg("naturalist/canary_egg", "canary", () -> NaturalistEntityTypes.CANARY.get());
            TAB_ITEMS.add(CANARY_DNA);
            TAB_ITEMS.add(CANARY_EGG);
        }
        if (enabled("finch")) {
            FINCH_DNA = FossilsCompatUtil.registerItem("naturalist/finch_dna", new Item(new Item.Properties()));
            FINCH_EGG = registerBirdEgg("naturalist/finch_egg", "finch", () -> NaturalistEntityTypes.FINCH.get());
            TAB_ITEMS.add(FINCH_DNA);
            TAB_ITEMS.add(FINCH_EGG);
        }
        if (enabled("sparrow")) {
            SPARROW_DNA = FossilsCompatUtil.registerItem("naturalist/sparrow_dna", new Item(new Item.Properties()));
            SPARROW_EGG = registerBirdEgg("naturalist/sparrow_egg", "sparrow", () -> NaturalistEntityTypes.SPARROW.get());
            TAB_ITEMS.add(SPARROW_DNA);
            TAB_ITEMS.add(SPARROW_EGG);
        }
        if (enabled("robin")) {
            ROBIN_DNA = FossilsCompatUtil.registerItem("naturalist/robin_dna", new Item(new Item.Properties()));
            ROBIN_EGG = registerBirdEgg("naturalist/robin_egg", "robin", () -> NaturalistEntityTypes.ROBIN.get());
            TAB_ITEMS.add(ROBIN_DNA);
            TAB_ITEMS.add(ROBIN_EGG);
        }
        if (enabled("vulture")) {
            VULTURE_DNA = FossilsCompatUtil.registerItem("naturalist/vulture_dna", new Item(new Item.Properties()));
            VULTURE_EGG = registerBirdEgg("naturalist/vulture_egg", "vulture", () -> NaturalistEntityTypes.VULTURE.get());
            TAB_ITEMS.add(VULTURE_DNA);
            TAB_ITEMS.add(VULTURE_EGG);
        }
        if (enabled("duck")) {
            DUCK_DNA = FossilsCompatUtil.registerItem("naturalist/duck_dna", new Item(new Item.Properties()));
            DUCK_EGG = registerBirdEgg("naturalist/duck_egg", "duck", () -> NaturalistEntityTypes.DUCK.get());
            TAB_ITEMS.add(DUCK_DNA);
            TAB_ITEMS.add(DUCK_EGG);
        }

        // Fish / invertebrates
        if (enabled("catfish")) {
            CATFISH_DNA = FossilsCompatUtil.registerItem("naturalist/catfish_dna", new Item(new Item.Properties()));
            CATFISH_EGG = registerCreatureEgg("naturalist/catfish_egg", () -> NaturalistEntityTypes.CATFISH.get());
            TAB_ITEMS.add(CATFISH_DNA);
            TAB_ITEMS.add(CATFISH_EGG);
        }
        if (enabled("bass")) {
            BASS_DNA = FossilsCompatUtil.registerItem("naturalist/bass_dna", new Item(new Item.Properties()));
            BASS_EGG = registerCreatureEgg("naturalist/bass_egg", () -> NaturalistEntityTypes.BASS.get());
            TAB_ITEMS.add(BASS_DNA);
            TAB_ITEMS.add(BASS_EGG);
        }
        if (enabled("firefly")) {
            FIREFLY_DNA = FossilsCompatUtil.registerItem("naturalist/firefly_dna", new Item(new Item.Properties()));
            FIREFLY_EGG = registerCreatureEgg("naturalist/firefly_egg", () -> NaturalistEntityTypes.FIREFLY.get());
            TAB_ITEMS.add(FIREFLY_DNA);
            TAB_ITEMS.add(FIREFLY_EGG);
        }
        if (enabled("dragonfly")) {
            DRAGONFLY_DNA = FossilsCompatUtil.registerItem("naturalist/dragonfly_dna", new Item(new Item.Properties()));
            DRAGONFLY_EGG = registerCreatureEgg("naturalist/dragonfly_egg", () -> NaturalistEntityTypes.DRAGONFLY.get());
            TAB_ITEMS.add(DRAGONFLY_DNA);
            TAB_ITEMS.add(DRAGONFLY_EGG);
        }

        // DNA only
        if (enabled("alligator")) {
            ALLIGATOR_DNA = FossilsCompatUtil.registerItem("naturalist/alligator_dna", new Item(new Item.Properties()));
            TAB_ITEMS.add(ALLIGATOR_DNA);
        }
        if (enabled("tortoise")) {
            TORTOISE_DNA = FossilsCompatUtil.registerItem("naturalist/tortoise_dna", new Item(new Item.Properties()));
            TAB_ITEMS.add(TORTOISE_DNA);
        }
        if (enabled("snail")) {
            SNAIL_DNA = FossilsCompatUtil.registerItem("naturalist/snail_dna", new Item(new Item.Properties()));
            TAB_ITEMS.add(SNAIL_DNA);
        }
        if (enabled("butterfly")) {
            BUTTERFLY_DNA = FossilsCompatUtil.registerItem("naturalist/butterfly_dna", new Item(new Item.Properties()));
            TAB_ITEMS.add(BUTTERFLY_DNA);
        }

        // Reptiles — placeable egg block, hatches into a baby after a while
        if (enabled("coral_snake")) {
            CORAL_SNAKE_DNA = FossilsCompatUtil.registerItem("naturalist/coral_snake_dna", new Item(new Item.Properties()));
            CORAL_SNAKE_EGG = registerReptileEgg("naturalist/coral_snake_egg", () -> NaturalistEntityTypes.CORAL_SNAKE.get());
            TAB_ITEMS.add(CORAL_SNAKE_DNA);
            TAB_ITEMS.add(CORAL_SNAKE_EGG);
        }
        if (enabled("snake")) {
            SNAKE_DNA = FossilsCompatUtil.registerItem("naturalist/snake_dna", new Item(new Item.Properties()));
            SNAKE_EGG = registerReptileEgg("naturalist/snake_egg", () -> NaturalistEntityTypes.SNAKE.get());
            TAB_ITEMS.add(SNAKE_DNA);
            TAB_ITEMS.add(SNAKE_EGG);
        }
        if (enabled("rattlesnake")) {
            RATTLESNAKE_DNA = FossilsCompatUtil.registerItem("naturalist/rattlesnake_dna", new Item(new Item.Properties()));
            RATTLESNAKE_EGG = registerReptileEgg("naturalist/rattlesnake_egg", () -> NaturalistEntityTypes.RATTLESNAKE.get());
            TAB_ITEMS.add(RATTLESNAKE_DNA);
            TAB_ITEMS.add(RATTLESNAKE_EGG);
        }
        if (enabled("lizard")) {
            LIZARD_DNA = FossilsCompatUtil.registerItem("naturalist/lizard_dna", new Item(new Item.Properties()));
            LIZARD_EGG = registerReptileEgg("naturalist/lizard_egg", () -> NaturalistEntityTypes.LIZARD.get());
            TAB_ITEMS.add(LIZARD_DNA);
            TAB_ITEMS.add(LIZARD_EGG);
        }

        // Mammals
        if (enabled("bear")) {
            BROWN_BEAR_DNA = FossilsCompatUtil.registerItem("naturalist/brown_bear_dna", new Item(new Item.Properties()));
            BROWN_BEAR_EMBRYO = registerMammalEmbryo("naturalist/brown_bear_embryo", "bear", () -> NaturalistEntityTypes.BEAR.get(), BROWN_BEAR_DNA);
            TAB_ITEMS.add(BROWN_BEAR_DNA);
            TAB_ITEMS.add(BROWN_BEAR_EMBRYO);
        }
        if (enabled("boar")) {
            BOAR_DNA = FossilsCompatUtil.registerItem("naturalist/boar_dna", new Item(new Item.Properties()));
            BOAR_EMBRYO = registerMammalEmbryo("naturalist/boar_embryo", "boar", () -> NaturalistEntityTypes.BOAR.get(), BOAR_DNA);
            TAB_ITEMS.add(BOAR_DNA);
            TAB_ITEMS.add(BOAR_EMBRYO);
        }
        if (enabled("hippo")) {
            HIPPO_DNA = FossilsCompatUtil.registerItem("naturalist/hippo_dna", new Item(new Item.Properties()));
            HIPPO_EMBRYO = registerMammalEmbryo("naturalist/hippo_embryo", "hippo", () -> NaturalistEntityTypes.HIPPO.get(), HIPPO_DNA);
            TAB_ITEMS.add(HIPPO_DNA);
            TAB_ITEMS.add(HIPPO_EMBRYO);
        }
        if (enabled("giraffe")) {
            GIRAFFE_DNA = FossilsCompatUtil.registerItem("naturalist/giraffe_dna", new Item(new Item.Properties()));
            GIRAFFE_EMBRYO = registerMammalEmbryo("naturalist/giraffe_embryo", "giraffe", () -> NaturalistEntityTypes.GIRAFFE.get(), GIRAFFE_DNA);
            TAB_ITEMS.add(GIRAFFE_DNA);
            TAB_ITEMS.add(GIRAFFE_EMBRYO);
        }
        if (enabled("elephant")) {
            ELEPHANT_DNA = FossilsCompatUtil.registerItem("naturalist/elephant_dna", new Item(new Item.Properties()));
            ELEPHANT_EMBRYO = registerMammalEmbryo("naturalist/elephant_embryo", "elephant", () -> NaturalistEntityTypes.ELEPHANT.get(), ELEPHANT_DNA);
            TAB_ITEMS.add(ELEPHANT_DNA);
            TAB_ITEMS.add(ELEPHANT_EMBRYO);
        }
        if (enabled("deer")) {
            DEER_DNA = FossilsCompatUtil.registerItem("naturalist/deer_dna", new Item(new Item.Properties()));
            DEER_EMBRYO = registerMammalEmbryo("naturalist/deer_embryo", "deer", () -> NaturalistEntityTypes.DEER.get(), DEER_DNA);
            TAB_ITEMS.add(DEER_DNA);
            TAB_ITEMS.add(DEER_EMBRYO);
        }
        if (enabled("lion")) {
            LION_DNA = FossilsCompatUtil.registerItem("naturalist/lion_dna", new Item(new Item.Properties()));
            LION_EMBRYO = registerMammalEmbryo("naturalist/lion_embryo", "lion", () -> NaturalistEntityTypes.LION.get(), LION_DNA);
            TAB_ITEMS.add(LION_DNA);
            TAB_ITEMS.add(LION_EMBRYO);
        }
        if (enabled("rhino")) {
            RHINO_DNA = FossilsCompatUtil.registerItem("naturalist/rhino_dna", new Item(new Item.Properties()));
            RHINO_EMBRYO = registerMammalEmbryo("naturalist/rhino_embryo", "rhino", () -> NaturalistEntityTypes.RHINO.get(), RHINO_DNA);
            TAB_ITEMS.add(RHINO_DNA);
            TAB_ITEMS.add(RHINO_EMBRYO);
        }
        if (enabled("zebra")) {
            ZEBRA_DNA = FossilsCompatUtil.registerItem("naturalist/zebra_dna", new Item(new Item.Properties()));
            ZEBRA_EMBRYO = registerMammalEmbryo("naturalist/zebra_embryo", "zebra", () -> NaturalistEntityTypes.ZEBRA.get(), ZEBRA_DNA);
            TAB_ITEMS.add(ZEBRA_DNA);
            TAB_ITEMS.add(ZEBRA_EMBRYO);
        }

        ItemGroupEvents.modifyEntriesEvent(FossilsCompatUtil.FOSSIL_MOB_TAB).register(entries -> TAB_ITEMS.forEach(entries::accept));
    }

    private static Item registerCreatureEgg(String id, Supplier<EntityType<?>> supplier) {
        return FossilsCompatUtil.registerItem(id, new SpawnEggLikeItem(supplier, false, new Item.Properties().stacksTo(16)));
    }

    private static Item registerBirdEgg(String id, String key, Supplier<EntityType<?>> supplier) {
        ENTITY_SUPPLIERS.put(key, supplier);
        Item egg = FossilsCompatUtil.registerItem(id, new ThrowableBirdEggItem(
            (level, player) -> new ThrownNaturalistBirdEgg(level, player, key),
            new Item.Properties().stacksTo(16)));
        EGG_BY_KEY.put(key, egg);
        return egg;
    }

    private static Item registerMammalEmbryo(String id, String key, Supplier<EntityType<?>> entityType, Item dnaItem) {
        return FossilsCompatUtil.registerItem(id, new MammalEmbryoItem(new SimpleEntityInfo(
            "NATURALIST_" + key.toUpperCase(Locale.ROOT), entityType, PrehistoricMobType.MAMMAL,
            () -> Component.translatable("entity.naturalist." + key), dnaItem)));
    }

    private static Item registerReptileEgg(String id, Supplier<EntityType<?>> entityType) {
        IncubatingEggBlock block = new IncubatingEggBlock(entityType, true, BlockBehaviour.Properties.copy(Blocks.TURTLE_EGG));
        FossilsCompatUtil.registerBlock(id, block);
        return FossilsCompatUtil.registerItem(id, new BlockItem(block, new Item.Properties()));
    }
}
