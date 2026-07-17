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
import net.minecraft.world.item.Item;
import org.blahajenjoyer.compat.naturalist.entity.ThrownNaturalistBirdEgg;
import org.blahajenjoyer.item.SpawnEggLikeItem;
import org.blahajenjoyer.item.ThrowableBirdEggItem;
import org.blahajenjoyer.util.FossilsCompatUtil;
import org.blahajenjoyer.util.SimpleEntityInfo;

import java.util.HashMap;
import java.util.Locale;
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

    // Reptiles — DNA + Egg (dino-egg style, not yet implemented)
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

        // Fish / invertebrates
        CATFISH_DNA  = FossilsCompatUtil.registerItem("naturalist/catfish_dna",  new Item(new Item.Properties()));
        CATFISH_EGG  = registerCreatureEgg("naturalist/catfish_egg",  () -> NaturalistEntityTypes.CATFISH.get());
        BASS_DNA     = FossilsCompatUtil.registerItem("naturalist/bass_dna",     new Item(new Item.Properties()));
        BASS_EGG     = registerCreatureEgg("naturalist/bass_egg",     () -> NaturalistEntityTypes.BASS.get());
        FIREFLY_DNA  = FossilsCompatUtil.registerItem("naturalist/firefly_dna",  new Item(new Item.Properties()));
        FIREFLY_EGG  = registerCreatureEgg("naturalist/firefly_egg",  () -> NaturalistEntityTypes.FIREFLY.get());
        DRAGONFLY_DNA= FossilsCompatUtil.registerItem("naturalist/dragonfly_dna",new Item(new Item.Properties()));
        DRAGONFLY_EGG= registerCreatureEgg("naturalist/dragonfly_egg",() -> NaturalistEntityTypes.DRAGONFLY.get());

        // DNA only
        ALLIGATOR_DNA = FossilsCompatUtil.registerItem("naturalist/alligator_dna", new Item(new Item.Properties()));
        TORTOISE_DNA  = FossilsCompatUtil.registerItem("naturalist/tortoise_dna",  new Item(new Item.Properties()));
        SNAIL_DNA     = FossilsCompatUtil.registerItem("naturalist/snail_dna",     new Item(new Item.Properties()));
        BUTTERFLY_DNA = FossilsCompatUtil.registerItem("naturalist/butterfly_dna", new Item(new Item.Properties()));

        // Reptiles
        CORAL_SNAKE_DNA  = FossilsCompatUtil.registerItem("naturalist/coral_snake_dna",  new Item(new Item.Properties()));
        CORAL_SNAKE_EGG  = FossilsCompatUtil.registerItem("naturalist/coral_snake_egg",  new Item(new Item.Properties()));
        SNAKE_DNA        = FossilsCompatUtil.registerItem("naturalist/snake_dna",        new Item(new Item.Properties()));
        SNAKE_EGG        = FossilsCompatUtil.registerItem("naturalist/snake_egg",        new Item(new Item.Properties()));
        RATTLESNAKE_DNA  = FossilsCompatUtil.registerItem("naturalist/rattlesnake_dna",  new Item(new Item.Properties()));
        RATTLESNAKE_EGG  = FossilsCompatUtil.registerItem("naturalist/rattlesnake_egg",  new Item(new Item.Properties()));
        LIZARD_DNA       = FossilsCompatUtil.registerItem("naturalist/lizard_dna",       new Item(new Item.Properties()));
        LIZARD_EGG       = FossilsCompatUtil.registerItem("naturalist/lizard_egg",       new Item(new Item.Properties()));

        // Mammals
        BROWN_BEAR_DNA   = FossilsCompatUtil.registerItem("naturalist/brown_bear_dna",   new Item(new Item.Properties()));
        BROWN_BEAR_EMBRYO= registerMammalEmbryo("naturalist/brown_bear_embryo", "bear",    () -> NaturalistEntityTypes.BEAR.get(),    BROWN_BEAR_DNA);
        BOAR_DNA         = FossilsCompatUtil.registerItem("naturalist/boar_dna",         new Item(new Item.Properties()));
        BOAR_EMBRYO      = registerMammalEmbryo("naturalist/boar_embryo",      "boar",    () -> NaturalistEntityTypes.BOAR.get(),    BOAR_DNA);
        HIPPO_DNA        = FossilsCompatUtil.registerItem("naturalist/hippo_dna",        new Item(new Item.Properties()));
        HIPPO_EMBRYO     = registerMammalEmbryo("naturalist/hippo_embryo",     "hippo",   () -> NaturalistEntityTypes.HIPPO.get(),   HIPPO_DNA);
        GIRAFFE_DNA      = FossilsCompatUtil.registerItem("naturalist/giraffe_dna",      new Item(new Item.Properties()));
        GIRAFFE_EMBRYO   = registerMammalEmbryo("naturalist/giraffe_embryo",   "giraffe", () -> NaturalistEntityTypes.GIRAFFE.get(), GIRAFFE_DNA);
        ELEPHANT_DNA     = FossilsCompatUtil.registerItem("naturalist/elephant_dna",     new Item(new Item.Properties()));
        ELEPHANT_EMBRYO  = registerMammalEmbryo("naturalist/elephant_embryo",  "elephant",() -> NaturalistEntityTypes.ELEPHANT.get(),ELEPHANT_DNA);
        DEER_DNA         = FossilsCompatUtil.registerItem("naturalist/deer_dna",         new Item(new Item.Properties()));
        DEER_EMBRYO      = registerMammalEmbryo("naturalist/deer_embryo",      "deer",    () -> NaturalistEntityTypes.DEER.get(),    DEER_DNA);
        LION_DNA         = FossilsCompatUtil.registerItem("naturalist/lion_dna",         new Item(new Item.Properties()));
        LION_EMBRYO      = registerMammalEmbryo("naturalist/lion_embryo",      "lion",    () -> NaturalistEntityTypes.LION.get(),    LION_DNA);
        RHINO_DNA        = FossilsCompatUtil.registerItem("naturalist/rhino_dna",        new Item(new Item.Properties()));
        RHINO_EMBRYO     = registerMammalEmbryo("naturalist/rhino_embryo",     "rhino",   () -> NaturalistEntityTypes.RHINO.get(),   RHINO_DNA);
        ZEBRA_DNA        = FossilsCompatUtil.registerItem("naturalist/zebra_dna",        new Item(new Item.Properties()));
        ZEBRA_EMBRYO     = registerMammalEmbryo("naturalist/zebra_embryo",     "zebra",   () -> NaturalistEntityTypes.ZEBRA.get(),   ZEBRA_DNA);

        ItemGroupEvents.modifyEntriesEvent(FossilsCompatUtil.FOSSIL_MOB_TAB).register(entries -> {
            entries.accept(ALLIGATOR_DNA);
            entries.accept(TORTOISE_DNA);
            entries.accept(SNAIL_DNA);
            entries.accept(BUTTERFLY_DNA);
            entries.accept(CORAL_SNAKE_DNA);
            entries.accept(CORAL_SNAKE_EGG);
            entries.accept(SNAKE_DNA);
            entries.accept(SNAKE_EGG);
            entries.accept(RATTLESNAKE_DNA);
            entries.accept(RATTLESNAKE_EGG);
            entries.accept(LIZARD_DNA);
            entries.accept(LIZARD_EGG);
            entries.accept(CATFISH_DNA);
            entries.accept(CATFISH_EGG);
            entries.accept(BASS_DNA);
            entries.accept(BASS_EGG);
            entries.accept(FIREFLY_DNA);
            entries.accept(FIREFLY_EGG);
            entries.accept(DRAGONFLY_DNA);
            entries.accept(DRAGONFLY_EGG);
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
            entries.accept(BROWN_BEAR_DNA);
            entries.accept(BROWN_BEAR_EMBRYO);
            entries.accept(BOAR_DNA);
            entries.accept(BOAR_EMBRYO);
            entries.accept(HIPPO_DNA);
            entries.accept(HIPPO_EMBRYO);
            entries.accept(GIRAFFE_DNA);
            entries.accept(GIRAFFE_EMBRYO);
            entries.accept(ELEPHANT_DNA);
            entries.accept(ELEPHANT_EMBRYO);
            entries.accept(DEER_DNA);
            entries.accept(DEER_EMBRYO);
            entries.accept(LION_DNA);
            entries.accept(LION_EMBRYO);
            entries.accept(RHINO_DNA);
            entries.accept(RHINO_EMBRYO);
            entries.accept(ZEBRA_DNA);
            entries.accept(ZEBRA_EMBRYO);
        });
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
}
