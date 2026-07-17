package org.blahajenjoyer.compat.faunify;

import com.pepper.faunify.registry.FaunifyEntities;
import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.PrehistoricMobType;
import com.github.teamfossilsarcheology.fossil.item.MammalEmbryoItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import org.blahajenjoyer.config.FossilsCompatConfig;
import org.blahajenjoyer.item.SpawnEggLikeItem;
import org.blahajenjoyer.util.FossilsCompatUtil;
import org.blahajenjoyer.util.SimpleEntityInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

public class FaunifyCompat {

    public static final String MOD_ID = "faunify";
    public static final List<String> ANIMAL_KEYS = List.of(
        "silk_moth",
        "weasel", "fennec", "chinchilla", "hedgehog", "ringtailcat", "opossum", "mouse",
        "beetle", "ladybug", "weevil", "beefly", "mantis", "millipede", "rolypoly", "dragonfly",
        "lacewing", "stickbug", "leafinsect", "grasshopper", "leafsheep"
    );

    private static final List<Item> TAB_ITEMS = new ArrayList<>();

    // DNA only — mob already has an in-game egg or equivalent
    public static Item SILK_MOTH_DNA;

    // Mammals — DNA + Embryo
    public static Item WEASEL_DNA;
    public static Item WEASEL_EMBRYO;
    public static Item FENNEC_FOX_DNA;
    public static Item FENNEC_FOX_EMBRYO;
    public static Item CHINCHILLA_DNA;
    public static Item CHINCHILLA_EMBRYO;
    public static Item HEDGEHOG_DNA;
    public static Item HEDGEHOG_EMBRYO;
    public static Item RINGTAIL_CAT_DNA;
    public static Item RINGTAIL_CAT_EMBRYO;
    public static Item OPOSSUM_DNA;
    public static Item OPOSSUM_EMBRYO;
    public static Item MOUSE_DNA;
    public static Item MOUSE_EMBRYO;

    // Invertebrates — DNA + spawn-egg-like Egg (adult on hatch)
    public static Item BEETLE_DNA;
    public static Item BEETLE_EGG;
    public static Item LADYBUG_DNA;
    public static Item LADYBUG_EGG;
    public static Item WEEVIL_DNA;
    public static Item WEEVIL_EGG;
    public static Item BEEFLY_DNA;
    public static Item BEEFLY_EGG;
    public static Item MANTIS_DNA;
    public static Item MANTIS_EGG;
    public static Item MILLIPEDE_DNA;
    public static Item MILLIPEDE_EGG;
    public static Item ROLLY_POLY_DNA;
    public static Item ROLLY_POLY_EGG;
    public static Item DRAGONFLY_DNA;
    public static Item DRAGONFLY_EGG;
    public static Item LACEWING_DNA;
    public static Item LACEWING_EGG;
    public static Item STICK_BUG_DNA;
    public static Item STICK_BUG_EGG;
    public static Item LEAF_INSECT_DNA;
    public static Item LEAF_INSECT_EGG;
    public static Item GRASSHOPPER_DNA;
    public static Item GRASSHOPPER_EGG;
    public static Item LEAF_SHEEP_DNA;
    public static Item LEAF_SHEEP_EGG;

    private static boolean enabled(String key) {
        return FossilsCompatConfig.isEnabled(MOD_ID, key);
    }

    public static void register() {
        if (enabled("silk_moth")) {
            SILK_MOTH_DNA = reg("faunify/silk_moth_dna", new Item(new Item.Properties()));
            TAB_ITEMS.add(SILK_MOTH_DNA);
        }

        if (enabled("weasel")) {
            WEASEL_DNA = reg("faunify/weasel_dna", new Item(new Item.Properties()));
            WEASEL_EMBRYO = regMammalEmbryo("faunify/weasel_embryo", "weasel", () -> FaunifyEntities.WEASEL, WEASEL_DNA);
            TAB_ITEMS.add(WEASEL_DNA);
            TAB_ITEMS.add(WEASEL_EMBRYO);
        }
        if (enabled("fennec")) {
            FENNEC_FOX_DNA = reg("faunify/fennec_fox_dna", new Item(new Item.Properties()));
            FENNEC_FOX_EMBRYO = regMammalEmbryo("faunify/fennec_fox_embryo", "fennec", () -> FaunifyEntities.FENNEC, FENNEC_FOX_DNA);
            TAB_ITEMS.add(FENNEC_FOX_DNA);
            TAB_ITEMS.add(FENNEC_FOX_EMBRYO);
        }
        if (enabled("chinchilla")) {
            CHINCHILLA_DNA = reg("faunify/chinchilla_dna", new Item(new Item.Properties()));
            CHINCHILLA_EMBRYO = regMammalEmbryo("faunify/chinchilla_embryo", "chinchilla", () -> FaunifyEntities.CHINCHILLA, CHINCHILLA_DNA);
            TAB_ITEMS.add(CHINCHILLA_DNA);
            TAB_ITEMS.add(CHINCHILLA_EMBRYO);
        }
        if (enabled("hedgehog")) {
            HEDGEHOG_DNA = reg("faunify/hedgehog_dna", new Item(new Item.Properties()));
            HEDGEHOG_EMBRYO = regMammalEmbryo("faunify/hedgehog_embryo", "hedgehog", () -> FaunifyEntities.HEDGEHOG, HEDGEHOG_DNA);
            TAB_ITEMS.add(HEDGEHOG_DNA);
            TAB_ITEMS.add(HEDGEHOG_EMBRYO);
        }
        if (enabled("ringtailcat")) {
            RINGTAIL_CAT_DNA = reg("faunify/ringtail_cat_dna", new Item(new Item.Properties()));
            RINGTAIL_CAT_EMBRYO = regMammalEmbryo("faunify/ringtail_cat_embryo", "ringtailcat", () -> FaunifyEntities.RINGTAIL, RINGTAIL_CAT_DNA);
            TAB_ITEMS.add(RINGTAIL_CAT_DNA);
            TAB_ITEMS.add(RINGTAIL_CAT_EMBRYO);
        }
        if (enabled("opossum")) {
            OPOSSUM_DNA = reg("faunify/opossum_dna", new Item(new Item.Properties()));
            OPOSSUM_EMBRYO = regMammalEmbryo("faunify/opossum_embryo", "opossum", () -> FaunifyEntities.OPOSSUM, OPOSSUM_DNA);
            TAB_ITEMS.add(OPOSSUM_DNA);
            TAB_ITEMS.add(OPOSSUM_EMBRYO);
        }
        if (enabled("mouse")) {
            MOUSE_DNA = reg("faunify/mouse_dna", new Item(new Item.Properties()));
            MOUSE_EMBRYO = regMammalEmbryo("faunify/mouse_embryo", "mouse", () -> FaunifyEntities.MOUSE, MOUSE_DNA);
            TAB_ITEMS.add(MOUSE_DNA);
            TAB_ITEMS.add(MOUSE_EMBRYO);
        }

        if (enabled("beetle")) {
            BEETLE_DNA = reg("faunify/beetle_dna", new Item(new Item.Properties()));
            BEETLE_EGG = regCreatureEgg("faunify/beetle_egg", () -> FaunifyEntities.BEETLE);
            TAB_ITEMS.add(BEETLE_DNA);
            TAB_ITEMS.add(BEETLE_EGG);
        }
        if (enabled("ladybug")) {
            LADYBUG_DNA = reg("faunify/ladybug_dna", new Item(new Item.Properties()));
            LADYBUG_EGG = regCreatureEgg("faunify/ladybug_egg", () -> FaunifyEntities.LADYBUG);
            TAB_ITEMS.add(LADYBUG_DNA);
            TAB_ITEMS.add(LADYBUG_EGG);
        }
        if (enabled("weevil")) {
            WEEVIL_DNA = reg("faunify/weevil_dna", new Item(new Item.Properties()));
            WEEVIL_EGG = regCreatureEgg("faunify/weevil_egg", () -> FaunifyEntities.WEEVIL);
            TAB_ITEMS.add(WEEVIL_DNA);
            TAB_ITEMS.add(WEEVIL_EGG);
        }
        if (enabled("beefly")) {
            BEEFLY_DNA = reg("faunify/beefly_dna", new Item(new Item.Properties()));
            BEEFLY_EGG = regCreatureEgg("faunify/beefly_egg", () -> FaunifyEntities.BEEFLY);
            TAB_ITEMS.add(BEEFLY_DNA);
            TAB_ITEMS.add(BEEFLY_EGG);
        }
        if (enabled("mantis")) {
            MANTIS_DNA = reg("faunify/mantis_dna", new Item(new Item.Properties()));
            MANTIS_EGG = regCreatureEgg("faunify/mantis_egg", () -> FaunifyEntities.MANTIS);
            TAB_ITEMS.add(MANTIS_DNA);
            TAB_ITEMS.add(MANTIS_EGG);
        }
        if (enabled("millipede")) {
            MILLIPEDE_DNA = reg("faunify/millipede_dna", new Item(new Item.Properties()));
            MILLIPEDE_EGG = regCreatureEgg("faunify/millipede_egg", () -> FaunifyEntities.MILLIPEDE_HEAD);
            TAB_ITEMS.add(MILLIPEDE_DNA);
            TAB_ITEMS.add(MILLIPEDE_EGG);
        }
        if (enabled("rolypoly")) {
            ROLLY_POLY_DNA = reg("faunify/rolly_poly_dna", new Item(new Item.Properties()));
            ROLLY_POLY_EGG = regCreatureEgg("faunify/rolly_poly_egg", () -> FaunifyEntities.ROLYPOLY);
            TAB_ITEMS.add(ROLLY_POLY_DNA);
            TAB_ITEMS.add(ROLLY_POLY_EGG);
        }
        if (enabled("dragonfly")) {
            DRAGONFLY_DNA = reg("faunify/dragonfly_dna", new Item(new Item.Properties()));
            DRAGONFLY_EGG = regCreatureEgg("faunify/dragonfly_egg", () -> FaunifyEntities.DRAGONFLY);
            TAB_ITEMS.add(DRAGONFLY_DNA);
            TAB_ITEMS.add(DRAGONFLY_EGG);
        }
        if (enabled("lacewing")) {
            LACEWING_DNA = reg("faunify/lacewing_dna", new Item(new Item.Properties()));
            LACEWING_EGG = regCreatureEgg("faunify/lacewing_egg", () -> FaunifyEntities.LACEWING);
            TAB_ITEMS.add(LACEWING_DNA);
            TAB_ITEMS.add(LACEWING_EGG);
        }
        if (enabled("stickbug")) {
            STICK_BUG_DNA = reg("faunify/stick_bug_dna", new Item(new Item.Properties()));
            STICK_BUG_EGG = regCreatureEgg("faunify/stick_bug_egg", () -> FaunifyEntities.STICKBUG);
            TAB_ITEMS.add(STICK_BUG_DNA);
            TAB_ITEMS.add(STICK_BUG_EGG);
        }
        if (enabled("leafinsect")) {
            LEAF_INSECT_DNA = reg("faunify/leaf_insect_dna", new Item(new Item.Properties()));
            LEAF_INSECT_EGG = regCreatureEgg("faunify/leaf_insect_egg", () -> FaunifyEntities.LEAFINSECT);
            TAB_ITEMS.add(LEAF_INSECT_DNA);
            TAB_ITEMS.add(LEAF_INSECT_EGG);
        }
        if (enabled("grasshopper")) {
            GRASSHOPPER_DNA = reg("faunify/grasshopper_dna", new Item(new Item.Properties()));
            GRASSHOPPER_EGG = regCreatureEgg("faunify/grasshopper_egg", () -> FaunifyEntities.GRASSHOPPER);
            TAB_ITEMS.add(GRASSHOPPER_DNA);
            TAB_ITEMS.add(GRASSHOPPER_EGG);
        }
        if (enabled("leafsheep")) {
            LEAF_SHEEP_DNA = reg("faunify/leaf_sheep_dna", new Item(new Item.Properties()));
            LEAF_SHEEP_EGG = regCreatureEgg("faunify/leaf_sheep_egg", () -> FaunifyEntities.LEAFSHEEP);
            TAB_ITEMS.add(LEAF_SHEEP_DNA);
            TAB_ITEMS.add(LEAF_SHEEP_EGG);
        }

        ItemGroupEvents.modifyEntriesEvent(FossilsCompatUtil.FOSSIL_MOB_TAB).register(entries -> TAB_ITEMS.forEach(entries::accept));
    }

    private static Item reg(String id, Item item) {
        return FossilsCompatUtil.registerItem(id, item);
    }

    private static Item regCreatureEgg(String id, Supplier<EntityType<?>> supplier) {
        return reg(id, new SpawnEggLikeItem(supplier, false, new Item.Properties().stacksTo(16)));
    }

    private static Item regMammalEmbryo(String id, String key, Supplier<EntityType<?>> entityType, Item dnaItem) {
        return reg(id, new MammalEmbryoItem(new SimpleEntityInfo(
            "FAUNIFY_" + key.toUpperCase(Locale.ROOT), entityType, PrehistoricMobType.MAMMAL,
            () -> Component.translatable("entity.faunify." + key), dnaItem)));
    }
}
