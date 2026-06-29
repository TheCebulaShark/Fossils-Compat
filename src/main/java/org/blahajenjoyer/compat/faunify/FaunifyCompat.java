package org.blahajenjoyer.compat.faunify;

import com.pepper.faunify.registry.FaunifyEntities;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.Item;
import org.blahajenjoyer.item.SpawnEggLikeItem;
import org.blahajenjoyer.util.FossilsCompatUtil;

public class FaunifyCompat {

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

    public static void register() {
        WEASEL_DNA         = reg("faunify/weasel_dna",         new Item(new Item.Properties()));
        WEASEL_EMBRYO      = reg("faunify/weasel_embryo",      new Item(new Item.Properties()));
        FENNEC_FOX_DNA     = reg("faunify/fennec_fox_dna",     new Item(new Item.Properties()));
        FENNEC_FOX_EMBRYO  = reg("faunify/fennec_fox_embryo",  new Item(new Item.Properties()));
        CHINCHILLA_DNA     = reg("faunify/chinchilla_dna",     new Item(new Item.Properties()));
        CHINCHILLA_EMBRYO  = reg("faunify/chinchilla_embryo",  new Item(new Item.Properties()));
        HEDGEHOG_DNA       = reg("faunify/hedgehog_dna",       new Item(new Item.Properties()));
        HEDGEHOG_EMBRYO    = reg("faunify/hedgehog_embryo",    new Item(new Item.Properties()));
        RINGTAIL_CAT_DNA   = reg("faunify/ringtail_cat_dna",   new Item(new Item.Properties()));
        RINGTAIL_CAT_EMBRYO= reg("faunify/ringtail_cat_embryo",new Item(new Item.Properties()));
        OPOSSUM_DNA        = reg("faunify/opossum_dna",        new Item(new Item.Properties()));
        OPOSSUM_EMBRYO     = reg("faunify/opossum_embryo",     new Item(new Item.Properties()));
        MOUSE_DNA          = reg("faunify/mouse_dna",          new Item(new Item.Properties()));
        MOUSE_EMBRYO       = reg("faunify/mouse_embryo",       new Item(new Item.Properties()));

        BEETLE_DNA      = reg("faunify/beetle_dna",      new Item(new Item.Properties()));
        BEETLE_EGG      = regCreatureEgg("faunify/beetle_egg",      () -> FaunifyEntities.BEETLE);
        LADYBUG_DNA     = reg("faunify/ladybug_dna",     new Item(new Item.Properties()));
        LADYBUG_EGG     = regCreatureEgg("faunify/ladybug_egg",     () -> FaunifyEntities.LADYBUG);
        WEEVIL_DNA      = reg("faunify/weevil_dna",      new Item(new Item.Properties()));
        WEEVIL_EGG      = regCreatureEgg("faunify/weevil_egg",      () -> FaunifyEntities.WEEVIL);
        BEEFLY_DNA      = reg("faunify/beefly_dna",      new Item(new Item.Properties()));
        BEEFLY_EGG      = regCreatureEgg("faunify/beefly_egg",      () -> FaunifyEntities.BEEFLY);
        MANTIS_DNA      = reg("faunify/mantis_dna",      new Item(new Item.Properties()));
        MANTIS_EGG      = regCreatureEgg("faunify/mantis_egg",      () -> FaunifyEntities.MANTIS);
        MILLIPEDE_DNA   = reg("faunify/millipede_dna",   new Item(new Item.Properties()));
        MILLIPEDE_EGG   = regCreatureEgg("faunify/millipede_egg",   () -> FaunifyEntities.MILLIPEDE_HEAD);
        ROLLY_POLY_DNA  = reg("faunify/rolly_poly_dna",  new Item(new Item.Properties()));
        ROLLY_POLY_EGG  = regCreatureEgg("faunify/rolly_poly_egg",  () -> FaunifyEntities.ROLYPOLY);
        DRAGONFLY_DNA   = reg("faunify/dragonfly_dna",   new Item(new Item.Properties()));
        DRAGONFLY_EGG   = regCreatureEgg("faunify/dragonfly_egg",   () -> FaunifyEntities.DRAGONFLY);
        LACEWING_DNA    = reg("faunify/lacewing_dna",    new Item(new Item.Properties()));
        LACEWING_EGG    = regCreatureEgg("faunify/lacewing_egg",    () -> FaunifyEntities.LACEWING);
        STICK_BUG_DNA   = reg("faunify/stick_bug_dna",   new Item(new Item.Properties()));
        STICK_BUG_EGG   = regCreatureEgg("faunify/stick_bug_egg",   () -> FaunifyEntities.STICKBUG);
        LEAF_INSECT_DNA = reg("faunify/leaf_insect_dna", new Item(new Item.Properties()));
        LEAF_INSECT_EGG = regCreatureEgg("faunify/leaf_insect_egg", () -> FaunifyEntities.LEAFINSECT);
        GRASSHOPPER_DNA = reg("faunify/grasshopper_dna", new Item(new Item.Properties()));
        GRASSHOPPER_EGG = regCreatureEgg("faunify/grasshopper_egg", () -> FaunifyEntities.GRASSHOPPER);
        LEAF_SHEEP_DNA  = reg("faunify/leaf_sheep_dna",  new Item(new Item.Properties()));
        LEAF_SHEEP_EGG  = regCreatureEgg("faunify/leaf_sheep_egg",  () -> FaunifyEntities.LEAFSHEEP);

        ItemGroupEvents.modifyEntriesEvent(FossilsCompatUtil.FOSSIL_MOB_TAB).register(entries -> {
            entries.accept(WEASEL_DNA);
            entries.accept(WEASEL_EMBRYO);
            entries.accept(FENNEC_FOX_DNA);
            entries.accept(FENNEC_FOX_EMBRYO);
            entries.accept(CHINCHILLA_DNA);
            entries.accept(CHINCHILLA_EMBRYO);
            entries.accept(HEDGEHOG_DNA);
            entries.accept(HEDGEHOG_EMBRYO);
            entries.accept(RINGTAIL_CAT_DNA);
            entries.accept(RINGTAIL_CAT_EMBRYO);
            entries.accept(OPOSSUM_DNA);
            entries.accept(OPOSSUM_EMBRYO);
            entries.accept(MOUSE_DNA);
            entries.accept(MOUSE_EMBRYO);
            entries.accept(BEETLE_DNA);
            entries.accept(BEETLE_EGG);
            entries.accept(LADYBUG_DNA);
            entries.accept(LADYBUG_EGG);
            entries.accept(WEEVIL_DNA);
            entries.accept(WEEVIL_EGG);
            entries.accept(BEEFLY_DNA);
            entries.accept(BEEFLY_EGG);
            entries.accept(MANTIS_DNA);
            entries.accept(MANTIS_EGG);
            entries.accept(MILLIPEDE_DNA);
            entries.accept(MILLIPEDE_EGG);
            entries.accept(ROLLY_POLY_DNA);
            entries.accept(ROLLY_POLY_EGG);
            entries.accept(DRAGONFLY_DNA);
            entries.accept(DRAGONFLY_EGG);
            entries.accept(LACEWING_DNA);
            entries.accept(LACEWING_EGG);
            entries.accept(STICK_BUG_DNA);
            entries.accept(STICK_BUG_EGG);
            entries.accept(LEAF_INSECT_DNA);
            entries.accept(LEAF_INSECT_EGG);
            entries.accept(GRASSHOPPER_DNA);
            entries.accept(GRASSHOPPER_EGG);
            entries.accept(LEAF_SHEEP_DNA);
            entries.accept(LEAF_SHEEP_EGG);
        });
    }

    private static Item reg(String id, Item item) {
        return FossilsCompatUtil.registerItem(id, item);
    }

    private static Item regCreatureEgg(String id, java.util.function.Supplier<net.minecraft.world.entity.EntityType<?>> supplier) {
        return reg(id, new SpawnEggLikeItem(supplier, false, new Item.Properties().stacksTo(16)));
    }
}
