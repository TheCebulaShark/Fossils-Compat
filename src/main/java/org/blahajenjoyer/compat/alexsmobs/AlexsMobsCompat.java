package org.blahajenjoyer.compat.alexsmobs;

import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
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
import org.blahajenjoyer.compat.alexsmobs.entity.ThrownAlexsBirdEgg;
import org.blahajenjoyer.compat.alexsmobs.item.AlexsBirdEggItem;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AlexsMobsCompat {

    public static EntityType<ThrownAlexsBirdEgg> THROWN_ALEXS_BIRD_EGG;

    public static Item BLUE_JAY_DNA;
    public static Item BLUE_JAY_EGG;
    public static Item BALD_EAGLE_DNA;
    public static Item BALD_EAGLE_EGG;
    public static Item CROW_DNA;
    public static Item CROW_EGG;
    public static Item EMU_DNA;
    public static Item EMU_EGG;
    public static Item HUMMINGBIRD_DNA;
    public static Item HUMMINGBIRD_EGG;
    public static Item SEAGULL_DNA;
    public static Item SEAGULL_EGG;
    public static Item ROADRUNNER_DNA;
    public static Item ROADRUNNER_EGG;
    public static Item POTOO_DNA;
    public static Item POTOO_EGG;
    public static Item TOUCAN_DNA;
    public static Item TOUCAN_EGG;

    private static final Map<String, Supplier<EntityType<?>>> ENTITY_SUPPLIERS = new HashMap<>();
    private static final Map<String, Item> EGG_BY_KEY = new HashMap<>();

    public static EntityType<?> getEntityType(String key) {
        Supplier<EntityType<?>> supplier = ENTITY_SUPPLIERS.get(key);
        return supplier != null ? supplier.get() : null;
    }

    public static Item getEggByKey(String key) {
        return EGG_BY_KEY.getOrDefault(key, BLUE_JAY_EGG);
    }

    public static void register() {
        THROWN_ALEXS_BIRD_EGG = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation("fossils_compat", "thrown_alexs_bird_egg"),
            EntityType.Builder.<ThrownAlexsBirdEgg>of(ThrownAlexsBirdEgg::new, MobCategory.MISC)
                .sized(0.25F, 0.25F)
                .clientTrackingRange(4)
                .updateInterval(10)
                .build("thrown_alexs_bird_egg")
        );

        BLUE_JAY_DNA    = registerItem("blue_jay_dna",    new Item(new Item.Properties()));
        BLUE_JAY_EGG    = registerBirdEgg("blue_jay_egg",    "blue_jay",    () -> AMEntityRegistry.BLUE_JAY);

        BALD_EAGLE_DNA  = registerItem("bald_eagle_dna",  new Item(new Item.Properties()));
        BALD_EAGLE_EGG  = registerBirdEgg("bald_eagle_egg",  "bald_eagle",  () -> AMEntityRegistry.BALD_EAGLE);

        CROW_DNA        = registerItem("crow_dna",        new Item(new Item.Properties()));
        CROW_EGG        = registerBirdEgg("crow_egg",        "crow",        () -> AMEntityRegistry.CROW);

        EMU_DNA         = registerItem("emu_dna",         new Item(new Item.Properties()));
        EMU_EGG         = registerBirdEgg("emu_egg",         "emu",         () -> AMEntityRegistry.EMU);

        HUMMINGBIRD_DNA = registerItem("hummingbird_dna", new Item(new Item.Properties()));
        HUMMINGBIRD_EGG = registerBirdEgg("hummingbird_egg", "hummingbird", () -> AMEntityRegistry.HUMMINGBIRD);

        SEAGULL_DNA     = registerItem("seagull_dna",     new Item(new Item.Properties()));
        SEAGULL_EGG     = registerBirdEgg("seagull_egg",     "seagull",     () -> AMEntityRegistry.SEAGULL);

        ROADRUNNER_DNA  = registerItem("roadrunner_dna",  new Item(new Item.Properties()));
        ROADRUNNER_EGG  = registerBirdEgg("roadrunner_egg",  "roadrunner",  () -> AMEntityRegistry.ROADRUNNER);

        POTOO_DNA       = registerItem("potoo_dna",       new Item(new Item.Properties()));
        POTOO_EGG       = registerBirdEgg("potoo_egg",       "potoo",       () -> AMEntityRegistry.POTOO);

        TOUCAN_DNA      = registerItem("toucan_dna",      new Item(new Item.Properties()));
        TOUCAN_EGG      = registerBirdEgg("toucan_egg",      "toucan",      () -> AMEntityRegistry.TOUCAN);

        ResourceKey<CreativeModeTab> fossilMobTab = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            new ResourceLocation("fossil", "fa_mob_item_tab")
        );
        ItemGroupEvents.modifyEntriesEvent(fossilMobTab).register(entries -> {
            entries.accept(BLUE_JAY_DNA);
            entries.accept(BLUE_JAY_EGG);
            entries.accept(BALD_EAGLE_DNA);
            entries.accept(BALD_EAGLE_EGG);
            entries.accept(CROW_DNA);
            entries.accept(CROW_EGG);
            entries.accept(EMU_DNA);
            entries.accept(EMU_EGG);
            entries.accept(HUMMINGBIRD_DNA);
            entries.accept(HUMMINGBIRD_EGG);
            entries.accept(SEAGULL_DNA);
            entries.accept(SEAGULL_EGG);
            entries.accept(ROADRUNNER_DNA);
            entries.accept(ROADRUNNER_EGG);
            entries.accept(POTOO_DNA);
            entries.accept(POTOO_EGG);
            entries.accept(TOUCAN_DNA);
            entries.accept(TOUCAN_EGG);
        });
    }

    private static Item registerItem(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("fossils_compat", id), item);
    }

    private static Item registerBirdEgg(String id, String key, Supplier<EntityType<?>> supplier) {
        ENTITY_SUPPLIERS.put(key, supplier);
        Item egg = registerItem(id, new AlexsBirdEggItem(key, new Item.Properties().stacksTo(16)));
        EGG_BY_KEY.put(key, egg);
        return egg;
    }
}
