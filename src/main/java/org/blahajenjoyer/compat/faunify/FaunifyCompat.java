package org.blahajenjoyer.compat.faunify;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.Item;
import org.blahajenjoyer.util.FossilsCompatUtil;

public class FaunifyCompat {

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

    public static void register() {
        WEASEL_DNA       = FossilsCompatUtil.registerItem("faunify/weasel_dna",       new Item(new Item.Properties()));
        WEASEL_EMBRYO    = FossilsCompatUtil.registerItem("faunify/weasel_embryo",    new Item(new Item.Properties()));
        FENNEC_FOX_DNA   = FossilsCompatUtil.registerItem("faunify/fennec_fox_dna",   new Item(new Item.Properties()));
        FENNEC_FOX_EMBRYO= FossilsCompatUtil.registerItem("faunify/fennec_fox_embryo",new Item(new Item.Properties()));
        CHINCHILLA_DNA   = FossilsCompatUtil.registerItem("faunify/chinchilla_dna",   new Item(new Item.Properties()));
        CHINCHILLA_EMBRYO= FossilsCompatUtil.registerItem("faunify/chinchilla_embryo",new Item(new Item.Properties()));
        HEDGEHOG_DNA     = FossilsCompatUtil.registerItem("faunify/hedgehog_dna",     new Item(new Item.Properties()));
        HEDGEHOG_EMBRYO  = FossilsCompatUtil.registerItem("faunify/hedgehog_embryo",  new Item(new Item.Properties()));
        RINGTAIL_CAT_DNA = FossilsCompatUtil.registerItem("faunify/ringtail_cat_dna", new Item(new Item.Properties()));
        RINGTAIL_CAT_EMBRYO=FossilsCompatUtil.registerItem("faunify/ringtail_cat_embryo",new Item(new Item.Properties()));
        OPOSSUM_DNA      = FossilsCompatUtil.registerItem("faunify/opossum_dna",      new Item(new Item.Properties()));
        OPOSSUM_EMBRYO   = FossilsCompatUtil.registerItem("faunify/opossum_embryo",   new Item(new Item.Properties()));
        MOUSE_DNA        = FossilsCompatUtil.registerItem("faunify/mouse_dna",        new Item(new Item.Properties()));
        MOUSE_EMBRYO     = FossilsCompatUtil.registerItem("faunify/mouse_embryo",     new Item(new Item.Properties()));

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
        });
    }
}
