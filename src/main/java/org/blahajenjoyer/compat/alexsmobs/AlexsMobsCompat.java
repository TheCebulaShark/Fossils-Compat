package org.blahajenjoyer.compat.alexsmobs;

import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import org.blahajenjoyer.compat.alexsmobs.entity.ThrownAlexsBirdEgg;
import org.blahajenjoyer.item.SpawnEggLikeItem;
import org.blahajenjoyer.item.ThrowableBirdEggItem;
import org.blahajenjoyer.util.FossilsCompatUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class AlexsMobsCompat {

    public static EntityType<ThrownAlexsBirdEgg> THROWN_ALEXS_BIRD_EGG;

    // Birds — DNA + throwable Egg
    public static Item SHOEBILL_DNA;
    public static Item SHOEBILL_EGG;
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

    // Mammals — DNA + Embryo
    public static Item ANTEATER_DNA;
    public static Item ANTEATER_EMBRYO;
    public static Item BISON_DNA;
    public static Item BISON_EMBRYO;
    public static Item CACHALOT_WHALE_DNA;
    public static Item CACHALOT_WHALE_EMBRYO;
    public static Item CAPUCHIN_MONKEY_DNA;
    public static Item CAPUCHIN_MONKEY_EMBRYO;
    public static Item ELEPHANT_DNA;
    public static Item ELEPHANT_EMBRYO;
    public static Item KANGAROO_DNA;
    public static Item KANGAROO_EMBRYO;
    public static Item JERBOA_DNA;
    public static Item JERBOA_EMBRYO;
    public static Item GRIZZLY_BEAR_DNA;
    public static Item GRIZZLY_BEAR_EMBRYO;
    public static Item GORILLA_DNA;
    public static Item GORILLA_EMBRYO;
    public static Item GELADA_MONKEY_DNA;
    public static Item GELADA_MONKEY_EMBRYO;
    public static Item GAZELLE_DNA;
    public static Item GAZELLE_EMBRYO;
    public static Item MANED_WOLF_DNA;
    public static Item MANED_WOLF_EMBRYO;
    public static Item MOOSE_DNA;
    public static Item MOOSE_EMBRYO;
    public static Item ORCA_DNA;
    public static Item ORCA_EMBRYO;
    public static Item RHINOCEROS_DNA;
    public static Item RHINOCEROS_EMBRYO;
    public static Item RACCOON_DNA;
    public static Item RACCOON_EMBRYO;
    public static Item SEAL_DNA;
    public static Item SEAL_EMBRYO;
    public static Item SKUNK_DNA;
    public static Item SKUNK_EMBRYO;
    public static Item SNOW_LEOPARD_DNA;
    public static Item SNOW_LEOPARD_EMBRYO;
    public static Item SUGAR_GLIDER_DNA;
    public static Item SUGAR_GLIDER_EMBRYO;
    public static Item TASMANIAN_DEVIL_DNA;
    public static Item TASMANIAN_DEVIL_EMBRYO;
    public static Item TIGER_DNA;
    public static Item TIGER_EMBRYO;
    public static Item TUSKLIN_DNA;
    public static Item TUSKLIN_EMBRYO;

    // Fish, amphibians, invertebrates — DNA + spawn-egg-like Egg (baby on hatch)
    public static Item BANANA_SLUG_DNA;
    public static Item BANANA_SLUG_EGG;
    public static Item COCKROACH_DNA;
    public static Item COCKROACH_EGG;
    public static Item ENDERGRADE_DNA;
    public static Item ENDERGRADE_EGG;
    public static Item FLY_DNA;
    public static Item FLY_EGG;
    public static Item MUDSKIPPER_DNA;
    public static Item MUDSKIPPER_EGG;
    public static Item MIMIC_OCTOPUS_DNA;
    public static Item MIMIC_OCTOPUS_EGG;
    public static Item MANTIS_SHRIMP_DNA;
    public static Item MANTIS_SHRIMP_EGG;
    public static Item RAIN_FROG_DNA;
    public static Item RAIN_FROG_EGG;
    public static Item WARPED_TOAD_DNA;
    public static Item WARPED_TOAD_EGG;
    // Fish, amphibians, invertebrates — DNA + spawn-egg-like Egg (adult on hatch)
    public static Item BLOBFISH_DNA;
    public static Item BLOBFISH_EGG;
    public static Item CATFISH_DNA;
    public static Item CATFISH_EGG;
    public static Item COMB_JELLY_DNA;
    public static Item COMB_JELLY_EGG;
    public static Item COSMIC_COD_DNA;
    public static Item COSMIC_COD_EGG;
    public static Item DEVILS_HOLE_PUPFISH_DNA;
    public static Item DEVILS_HOLE_PUPFISH_EGG;
    public static Item FLYING_FISH_DNA;
    public static Item FLYING_FISH_EGG;
    public static Item FRILLED_SHARK_DNA;
    public static Item FRILLED_SHARK_EGG;
    public static Item GIANT_SQUID_DNA;
    public static Item GIANT_SQUID_EGG;
    public static Item HAMMERHEAD_SHARK_DNA;
    public static Item HAMMERHEAD_SHARK_EGG;
    public static Item LOBSTER_DNA;
    public static Item LOBSTER_EGG;
    public static Item TARANTULA_HAWK_DNA;
    public static Item TARANTULA_HAWK_EGG;
    public static Item COSMAW_DNA;
    public static Item COSMAW_EGG;

    // Reptiles — DNA + Egg (dino-egg style, not yet implemented)
    public static Item ALLIGATOR_SNAPPING_TURTLE_DNA;
    public static Item ALLIGATOR_SNAPPING_TURTLE_EGG;
    public static Item ANACONDA_DNA;
    public static Item ANACONDA_EGG;
    public static Item FROSTSTALKER_DNA;
    public static Item FROSTSTALKER_EGG;
    public static Item KOMODO_DRAGON_DNA;
    public static Item KOMODO_DRAGON_EGG;
    public static Item RATTLESNAKE_DNA;
    public static Item RATTLESNAKE_EGG;

    // DNA only — mob already has an in-game egg or equivalent
    public static Item CAIMAN_DNA;
    public static Item CROCODILE_DNA;
    public static Item PLATYPUS_DNA;
    public static Item TERRAPIN_DNA;
    public static Item TRIOPS_DNA;
    public static Item LEAFCUTTER_ANT_DNA;
    public static Item LAVIATHAN_DNA;

    private static final Set<String> ADULT_ON_HATCH_BIRDS = Set.of("shoebill");

    private static final Map<String, Supplier<EntityType<?>>> ENTITY_SUPPLIERS = new HashMap<>();
    private static final Map<String, Item> EGG_BY_KEY = new HashMap<>();

    public static boolean isBabyOnHatchBird(String key) {
        return !ADULT_ON_HATCH_BIRDS.contains(key);
    }

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

        // Birds
        SHOEBILL_DNA = registerItem("alexsmobs/shoebill_dna", new Item(new Item.Properties()));
        SHOEBILL_EGG = registerBirdEgg("alexsmobs/shoebill_egg", "shoebill", () -> AMEntityRegistry.SHOEBILL);
        BLUE_JAY_DNA    = registerItem("alexsmobs/blue_jay_dna",    new Item(new Item.Properties()));
        BLUE_JAY_EGG    = registerBirdEgg("alexsmobs/blue_jay_egg",    "blue_jay",    () -> AMEntityRegistry.BLUE_JAY);
        BALD_EAGLE_DNA  = registerItem("alexsmobs/bald_eagle_dna",  new Item(new Item.Properties()));
        BALD_EAGLE_EGG  = registerBirdEgg("alexsmobs/bald_eagle_egg",  "bald_eagle",  () -> AMEntityRegistry.BALD_EAGLE);
        CROW_DNA        = registerItem("alexsmobs/crow_dna",        new Item(new Item.Properties()));
        CROW_EGG        = registerBirdEgg("alexsmobs/crow_egg",        "crow",        () -> AMEntityRegistry.CROW);
        EMU_DNA         = registerItem("alexsmobs/emu_dna",         new Item(new Item.Properties()));
        EMU_EGG         = registerBirdEgg("alexsmobs/emu_egg",         "emu",         () -> AMEntityRegistry.EMU);
        HUMMINGBIRD_DNA = registerItem("alexsmobs/hummingbird_dna", new Item(new Item.Properties()));
        HUMMINGBIRD_EGG = registerBirdEgg("alexsmobs/hummingbird_egg", "hummingbird", () -> AMEntityRegistry.HUMMINGBIRD);
        SEAGULL_DNA     = registerItem("alexsmobs/seagull_dna",     new Item(new Item.Properties()));
        SEAGULL_EGG     = registerBirdEgg("alexsmobs/seagull_egg",     "seagull",     () -> AMEntityRegistry.SEAGULL);
        ROADRUNNER_DNA  = registerItem("alexsmobs/roadrunner_dna",  new Item(new Item.Properties()));
        ROADRUNNER_EGG  = registerBirdEgg("alexsmobs/roadrunner_egg",  "roadrunner",  () -> AMEntityRegistry.ROADRUNNER);
        POTOO_DNA       = registerItem("alexsmobs/potoo_dna",       new Item(new Item.Properties()));
        POTOO_EGG       = registerBirdEgg("alexsmobs/potoo_egg",       "potoo",       () -> AMEntityRegistry.POTOO);
        TOUCAN_DNA      = registerItem("alexsmobs/toucan_dna",      new Item(new Item.Properties()));
        TOUCAN_EGG      = registerBirdEgg("alexsmobs/toucan_egg",      "toucan",      () -> AMEntityRegistry.TOUCAN);

        // Mammals
        ANTEATER_DNA          = registerItem("alexsmobs/anteater_dna",          new Item(new Item.Properties()));
        ANTEATER_EMBRYO       = registerItem("alexsmobs/anteater_embryo",       new Item(new Item.Properties()));
        BISON_DNA             = registerItem("alexsmobs/bison_dna",             new Item(new Item.Properties()));
        BISON_EMBRYO          = registerItem("alexsmobs/bison_embryo",          new Item(new Item.Properties()));
        CACHALOT_WHALE_DNA    = registerItem("alexsmobs/cachalot_whale_dna",    new Item(new Item.Properties()));
        CACHALOT_WHALE_EMBRYO = registerItem("alexsmobs/cachalot_whale_embryo", new Item(new Item.Properties()));
        CAPUCHIN_MONKEY_DNA   = registerItem("alexsmobs/capuchin_monkey_dna",   new Item(new Item.Properties()));
        CAPUCHIN_MONKEY_EMBRYO= registerItem("alexsmobs/capuchin_monkey_embryo",new Item(new Item.Properties()));
        ELEPHANT_DNA          = registerItem("alexsmobs/elephant_dna",          new Item(new Item.Properties()));
        ELEPHANT_EMBRYO       = registerItem("alexsmobs/elephant_embryo",       new Item(new Item.Properties()));
        KANGAROO_DNA          = registerItem("alexsmobs/kangaroo_dna",          new Item(new Item.Properties()));
        KANGAROO_EMBRYO       = registerItem("alexsmobs/kangaroo_embryo",       new Item(new Item.Properties()));
        JERBOA_DNA            = registerItem("alexsmobs/jerboa_dna",            new Item(new Item.Properties()));
        JERBOA_EMBRYO         = registerItem("alexsmobs/jerboa_embryo",         new Item(new Item.Properties()));
        GRIZZLY_BEAR_DNA      = registerItem("alexsmobs/grizzly_bear_dna",      new Item(new Item.Properties()));
        GRIZZLY_BEAR_EMBRYO   = registerItem("alexsmobs/grizzly_bear_embryo",   new Item(new Item.Properties()));
        GORILLA_DNA           = registerItem("alexsmobs/gorilla_dna",           new Item(new Item.Properties()));
        GORILLA_EMBRYO        = registerItem("alexsmobs/gorilla_embryo",        new Item(new Item.Properties()));
        GELADA_MONKEY_DNA     = registerItem("alexsmobs/gelada_monkey_dna",     new Item(new Item.Properties()));
        GELADA_MONKEY_EMBRYO  = registerItem("alexsmobs/gelada_monkey_embryo",  new Item(new Item.Properties()));
        GAZELLE_DNA           = registerItem("alexsmobs/gazelle_dna",           new Item(new Item.Properties()));
        GAZELLE_EMBRYO        = registerItem("alexsmobs/gazelle_embryo",        new Item(new Item.Properties()));
        MANED_WOLF_DNA        = registerItem("alexsmobs/maned_wolf_dna",        new Item(new Item.Properties()));
        MANED_WOLF_EMBRYO     = registerItem("alexsmobs/maned_wolf_embryo",     new Item(new Item.Properties()));
        MOOSE_DNA             = registerItem("alexsmobs/moose_dna",             new Item(new Item.Properties()));
        MOOSE_EMBRYO          = registerItem("alexsmobs/moose_embryo",          new Item(new Item.Properties()));
        ORCA_DNA              = registerItem("alexsmobs/orca_dna",              new Item(new Item.Properties()));
        ORCA_EMBRYO           = registerItem("alexsmobs/orca_embryo",           new Item(new Item.Properties()));
        RHINOCEROS_DNA        = registerItem("alexsmobs/rhinoceros_dna",        new Item(new Item.Properties()));
        RHINOCEROS_EMBRYO     = registerItem("alexsmobs/rhinoceros_embryo",     new Item(new Item.Properties()));
        RACCOON_DNA           = registerItem("alexsmobs/raccoon_dna",           new Item(new Item.Properties()));
        RACCOON_EMBRYO        = registerItem("alexsmobs/raccoon_embryo",        new Item(new Item.Properties()));
        SEAL_DNA              = registerItem("alexsmobs/seal_dna",              new Item(new Item.Properties()));
        SEAL_EMBRYO           = registerItem("alexsmobs/seal_embryo",           new Item(new Item.Properties()));
        SKUNK_DNA             = registerItem("alexsmobs/skunk_dna",             new Item(new Item.Properties()));
        SKUNK_EMBRYO          = registerItem("alexsmobs/skunk_embryo",          new Item(new Item.Properties()));
        SNOW_LEOPARD_DNA      = registerItem("alexsmobs/snow_leopard_dna",      new Item(new Item.Properties()));
        SNOW_LEOPARD_EMBRYO   = registerItem("alexsmobs/snow_leopard_embryo",   new Item(new Item.Properties()));
        SUGAR_GLIDER_DNA      = registerItem("alexsmobs/sugar_glider_dna",      new Item(new Item.Properties()));
        SUGAR_GLIDER_EMBRYO   = registerItem("alexsmobs/sugar_glider_embryo",   new Item(new Item.Properties()));
        TASMANIAN_DEVIL_DNA   = registerItem("alexsmobs/tasmanian_devil_dna",   new Item(new Item.Properties()));
        TASMANIAN_DEVIL_EMBRYO= registerItem("alexsmobs/tasmanian_devil_embryo",new Item(new Item.Properties()));
        TIGER_DNA             = registerItem("alexsmobs/tiger_dna",             new Item(new Item.Properties()));
        TIGER_EMBRYO          = registerItem("alexsmobs/tiger_embryo",          new Item(new Item.Properties()));
        TUSKLIN_DNA           = registerItem("alexsmobs/tusklin_dna",           new Item(new Item.Properties()));
        TUSKLIN_EMBRYO        = registerItem("alexsmobs/tusklin_embryo",        new Item(new Item.Properties()));

        // Fish / amphibians / invertebrates — baby on hatch
        BANANA_SLUG_DNA   = registerItem("alexsmobs/banana_slug_dna",   new Item(new Item.Properties()));
        BANANA_SLUG_EGG   = registerCreatureEgg("alexsmobs/banana_slug_egg",   () -> AMEntityRegistry.BANANA_SLUG,   true);
        COCKROACH_DNA     = registerItem("alexsmobs/cockroach_dna",     new Item(new Item.Properties()));
        COCKROACH_EGG     = registerCreatureEgg("alexsmobs/cockroach_egg",     () -> AMEntityRegistry.COCKROACH,     true);
        ENDERGRADE_DNA    = registerItem("alexsmobs/endergrade_dna",    new Item(new Item.Properties()));
        ENDERGRADE_EGG    = registerCreatureEgg("alexsmobs/endergrade_egg",    () -> AMEntityRegistry.ENDERGRADE,    true);
        FLY_DNA           = registerItem("alexsmobs/fly_dna",           new Item(new Item.Properties()));
        FLY_EGG           = registerCreatureEgg("alexsmobs/fly_egg",           () -> AMEntityRegistry.FLY,           true);
        MUDSKIPPER_DNA    = registerItem("alexsmobs/mudskipper_dna",    new Item(new Item.Properties()));
        MUDSKIPPER_EGG    = registerCreatureEgg("alexsmobs/mudskipper_egg",    () -> AMEntityRegistry.MUDSKIPPER,    true);
        MIMIC_OCTOPUS_DNA = registerItem("alexsmobs/mimic_octopus_dna", new Item(new Item.Properties()));
        MIMIC_OCTOPUS_EGG = registerCreatureEgg("alexsmobs/mimic_octopus_egg", () -> AMEntityRegistry.MIMIC_OCTOPUS, true);
        MANTIS_SHRIMP_DNA = registerItem("alexsmobs/mantis_shrimp_dna", new Item(new Item.Properties()));
        MANTIS_SHRIMP_EGG = registerCreatureEgg("alexsmobs/mantis_shrimp_egg", () -> AMEntityRegistry.MANTIS_SHRIMP, true);
        RAIN_FROG_DNA     = registerItem("alexsmobs/rain_frog_dna",     new Item(new Item.Properties()));
        RAIN_FROG_EGG     = registerCreatureEgg("alexsmobs/rain_frog_egg",     () -> AMEntityRegistry.RAIN_FROG,     true);
        WARPED_TOAD_DNA   = registerItem("alexsmobs/warped_toad_dna",   new Item(new Item.Properties()));
        WARPED_TOAD_EGG   = registerCreatureEgg("alexsmobs/warped_toad_egg",   () -> AMEntityRegistry.WARPED_TOAD,   true);

        // Fish / amphibians / invertebrates — adult on hatch
        BLOBFISH_DNA          = registerItem("alexsmobs/blobfish_dna",          new Item(new Item.Properties()));
        BLOBFISH_EGG          = registerCreatureEgg("alexsmobs/blobfish_egg",          () -> AMEntityRegistry.BLOBFISH,          false);
        CATFISH_DNA           = registerItem("alexsmobs/catfish_dna",           new Item(new Item.Properties()));
        CATFISH_EGG           = registerCreatureEgg("alexsmobs/catfish_egg",           () -> AMEntityRegistry.CATFISH,           false);
        COMB_JELLY_DNA        = registerItem("alexsmobs/comb_jelly_dna",        new Item(new Item.Properties()));
        COMB_JELLY_EGG        = registerCreatureEgg("alexsmobs/comb_jelly_egg",        () -> AMEntityRegistry.COMB_JELLY,        false);
        COSMIC_COD_DNA        = registerItem("alexsmobs/cosmic_cod_dna",        new Item(new Item.Properties()));
        COSMIC_COD_EGG        = registerCreatureEgg("alexsmobs/cosmic_cod_egg",        () -> AMEntityRegistry.COSMIC_COD,        false);
        DEVILS_HOLE_PUPFISH_DNA = registerItem("alexsmobs/devils_hole_pupfish_dna", new Item(new Item.Properties()));
        DEVILS_HOLE_PUPFISH_EGG = registerCreatureEgg("alexsmobs/devils_hole_pupfish_egg", () -> AMEntityRegistry.DEVILS_HOLE_PUPFISH, false);
        FLYING_FISH_DNA       = registerItem("alexsmobs/flying_fish_dna",       new Item(new Item.Properties()));
        FLYING_FISH_EGG       = registerCreatureEgg("alexsmobs/flying_fish_egg",       () -> AMEntityRegistry.FLYING_FISH,       false);
        FRILLED_SHARK_DNA     = registerItem("alexsmobs/frilled_shark_dna",     new Item(new Item.Properties()));
        FRILLED_SHARK_EGG     = registerCreatureEgg("alexsmobs/frilled_shark_egg",     () -> AMEntityRegistry.FRILLED_SHARK,     false);
        GIANT_SQUID_DNA       = registerItem("alexsmobs/giant_squid_dna",       new Item(new Item.Properties()));
        GIANT_SQUID_EGG       = registerCreatureEgg("alexsmobs/giant_squid_egg",       () -> AMEntityRegistry.GIANT_SQUID,       false);
        HAMMERHEAD_SHARK_DNA  = registerItem("alexsmobs/hammerhead_shark_dna",  new Item(new Item.Properties()));
        HAMMERHEAD_SHARK_EGG  = registerCreatureEgg("alexsmobs/hammerhead_shark_egg",  () -> AMEntityRegistry.HAMMERHEAD_SHARK,  false);
        LOBSTER_DNA           = registerItem("alexsmobs/lobster_dna",           new Item(new Item.Properties()));
        LOBSTER_EGG           = registerCreatureEgg("alexsmobs/lobster_egg",           () -> AMEntityRegistry.LOBSTER,           false);
        TARANTULA_HAWK_DNA    = registerItem("alexsmobs/tarantula_hawk_dna",    new Item(new Item.Properties()));
        TARANTULA_HAWK_EGG    = registerCreatureEgg("alexsmobs/tarantula_hawk_egg",    () -> AMEntityRegistry.TARANTULA_HAWK,    false);
        COSMAW_DNA        = registerItem("alexsmobs/cosmaw_dna",        new Item(new Item.Properties()));
        COSMAW_EGG        = registerCreatureEgg("alexsmobs/cosmaw_egg", () -> AMEntityRegistry.COSMAW, true);

        // Reptiles — dino-egg style (placeholder items, not yet functional)
        ALLIGATOR_SNAPPING_TURTLE_DNA = registerItem("alexsmobs/alligator_snapping_turtle_dna", new Item(new Item.Properties()));
        ALLIGATOR_SNAPPING_TURTLE_EGG = registerItem("alexsmobs/alligator_snapping_turtle_egg", new Item(new Item.Properties()));
        ANACONDA_DNA      = registerItem("alexsmobs/anaconda_dna",      new Item(new Item.Properties()));
        ANACONDA_EGG      = registerItem("alexsmobs/anaconda_egg",      new Item(new Item.Properties()));
        FROSTSTALKER_DNA  = registerItem("alexsmobs/froststalker_dna",  new Item(new Item.Properties()));
        FROSTSTALKER_EGG  = registerItem("alexsmobs/froststalker_egg",  new Item(new Item.Properties()));
        KOMODO_DRAGON_DNA = registerItem("alexsmobs/komodo_dragon_dna", new Item(new Item.Properties()));
        KOMODO_DRAGON_EGG = registerItem("alexsmobs/komodo_dragon_egg", new Item(new Item.Properties()));
        RATTLESNAKE_DNA   = registerItem("alexsmobs/rattlesnake_dna",   new Item(new Item.Properties()));
        RATTLESNAKE_EGG   = registerItem("alexsmobs/rattlesnake_egg",   new Item(new Item.Properties()));

        // DNA only
        CAIMAN_DNA        = registerItem("alexsmobs/caiman_dna",        new Item(new Item.Properties()));
        CROCODILE_DNA     = registerItem("alexsmobs/crocodile_dna",     new Item(new Item.Properties()));
        PLATYPUS_DNA      = registerItem("alexsmobs/platypus_dna",      new Item(new Item.Properties()));
        TERRAPIN_DNA      = registerItem("alexsmobs/terrapin_dna",      new Item(new Item.Properties()));
        TRIOPS_DNA        = registerItem("alexsmobs/triops_dna",        new Item(new Item.Properties()));
        LEAFCUTTER_ANT_DNA= registerItem("alexsmobs/leafcutter_ant_dna",new Item(new Item.Properties()));
        LAVIATHAN_DNA     = registerItem("alexsmobs/laviathan_dna",     new Item(new Item.Properties()));

        ItemGroupEvents.modifyEntriesEvent(FossilsCompatUtil.FOSSIL_MOB_TAB).register(entries -> {
            entries.accept(SHOEBILL_DNA);
            entries.accept(SHOEBILL_EGG);
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
            entries.accept(ANTEATER_DNA);
            entries.accept(ANTEATER_EMBRYO);
            entries.accept(BISON_DNA);
            entries.accept(BISON_EMBRYO);
            entries.accept(CACHALOT_WHALE_DNA);
            entries.accept(CACHALOT_WHALE_EMBRYO);
            entries.accept(CAPUCHIN_MONKEY_DNA);
            entries.accept(CAPUCHIN_MONKEY_EMBRYO);
            entries.accept(ELEPHANT_DNA);
            entries.accept(ELEPHANT_EMBRYO);
            entries.accept(KANGAROO_DNA);
            entries.accept(KANGAROO_EMBRYO);
            entries.accept(JERBOA_DNA);
            entries.accept(JERBOA_EMBRYO);
            entries.accept(GRIZZLY_BEAR_DNA);
            entries.accept(GRIZZLY_BEAR_EMBRYO);
            entries.accept(GORILLA_DNA);
            entries.accept(GORILLA_EMBRYO);
            entries.accept(GELADA_MONKEY_DNA);
            entries.accept(GELADA_MONKEY_EMBRYO);
            entries.accept(GAZELLE_DNA);
            entries.accept(GAZELLE_EMBRYO);
            entries.accept(MANED_WOLF_DNA);
            entries.accept(MANED_WOLF_EMBRYO);
            entries.accept(MOOSE_DNA);
            entries.accept(MOOSE_EMBRYO);
            entries.accept(ORCA_DNA);
            entries.accept(ORCA_EMBRYO);
            entries.accept(RHINOCEROS_DNA);
            entries.accept(RHINOCEROS_EMBRYO);
            entries.accept(RACCOON_DNA);
            entries.accept(RACCOON_EMBRYO);
            entries.accept(SEAL_DNA);
            entries.accept(SEAL_EMBRYO);
            entries.accept(SKUNK_DNA);
            entries.accept(SKUNK_EMBRYO);
            entries.accept(SNOW_LEOPARD_DNA);
            entries.accept(SNOW_LEOPARD_EMBRYO);
            entries.accept(SUGAR_GLIDER_DNA);
            entries.accept(SUGAR_GLIDER_EMBRYO);
            entries.accept(TASMANIAN_DEVIL_DNA);
            entries.accept(TASMANIAN_DEVIL_EMBRYO);
            entries.accept(TIGER_DNA);
            entries.accept(TIGER_EMBRYO);
            entries.accept(TUSKLIN_DNA);
            entries.accept(TUSKLIN_EMBRYO);
            entries.accept(BANANA_SLUG_DNA);
            entries.accept(BANANA_SLUG_EGG);
            entries.accept(COCKROACH_DNA);
            entries.accept(COCKROACH_EGG);
            entries.accept(ENDERGRADE_DNA);
            entries.accept(ENDERGRADE_EGG);
            entries.accept(FLY_DNA);
            entries.accept(FLY_EGG);
            entries.accept(MUDSKIPPER_DNA);
            entries.accept(MUDSKIPPER_EGG);
            entries.accept(MIMIC_OCTOPUS_DNA);
            entries.accept(MIMIC_OCTOPUS_EGG);
            entries.accept(MANTIS_SHRIMP_DNA);
            entries.accept(MANTIS_SHRIMP_EGG);
            entries.accept(RAIN_FROG_DNA);
            entries.accept(RAIN_FROG_EGG);
            entries.accept(WARPED_TOAD_DNA);
            entries.accept(WARPED_TOAD_EGG);
            entries.accept(BLOBFISH_DNA);
            entries.accept(BLOBFISH_EGG);
            entries.accept(CATFISH_DNA);
            entries.accept(CATFISH_EGG);
            entries.accept(COMB_JELLY_DNA);
            entries.accept(COMB_JELLY_EGG);
            entries.accept(COSMIC_COD_DNA);
            entries.accept(COSMIC_COD_EGG);
            entries.accept(DEVILS_HOLE_PUPFISH_DNA);
            entries.accept(DEVILS_HOLE_PUPFISH_EGG);
            entries.accept(FLYING_FISH_DNA);
            entries.accept(FLYING_FISH_EGG);
            entries.accept(FRILLED_SHARK_DNA);
            entries.accept(FRILLED_SHARK_EGG);
            entries.accept(GIANT_SQUID_DNA);
            entries.accept(GIANT_SQUID_EGG);
            entries.accept(HAMMERHEAD_SHARK_DNA);
            entries.accept(HAMMERHEAD_SHARK_EGG);
            entries.accept(LOBSTER_DNA);
            entries.accept(LOBSTER_EGG);
            entries.accept(TARANTULA_HAWK_DNA);
            entries.accept(TARANTULA_HAWK_EGG);
            entries.accept(COSMAW_DNA);
            entries.accept(COSMAW_EGG);
            entries.accept(ALLIGATOR_SNAPPING_TURTLE_DNA);
            entries.accept(ALLIGATOR_SNAPPING_TURTLE_EGG);
            entries.accept(ANACONDA_DNA);
            entries.accept(ANACONDA_EGG);
            entries.accept(FROSTSTALKER_DNA);
            entries.accept(FROSTSTALKER_EGG);
            entries.accept(KOMODO_DRAGON_DNA);
            entries.accept(KOMODO_DRAGON_EGG);
            entries.accept(RATTLESNAKE_DNA);
            entries.accept(RATTLESNAKE_EGG);
            entries.accept(CAIMAN_DNA);
            entries.accept(CROCODILE_DNA);
            entries.accept(PLATYPUS_DNA);
            entries.accept(TERRAPIN_DNA);
            entries.accept(TRIOPS_DNA);
            entries.accept(LEAFCUTTER_ANT_DNA);
            entries.accept(LAVIATHAN_DNA);
        });
    }

    private static Item registerItem(String id, Item item) {
        return FossilsCompatUtil.registerItem(id, item);
    }

    private static Item registerBirdEgg(String id, String key, Supplier<EntityType<?>> supplier) {
        ENTITY_SUPPLIERS.put(key, supplier);
        Item egg = registerItem(id, new ThrowableBirdEggItem(
            (level, player) -> new ThrownAlexsBirdEgg(level, player, key),
            new Item.Properties().stacksTo(16)));
        EGG_BY_KEY.put(key, egg);
        return egg;
    }

    private static Item registerCreatureEgg(String id, Supplier<EntityType<?>> supplier, boolean babyOnHatch) {
        return registerItem(id, new SpawnEggLikeItem(supplier, babyOnHatch, new Item.Properties().stacksTo(16)));
    }
}
