package org.blahajenjoyer.compat.alexsmobs;

import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.PrehistoricMobType;
import com.github.teamfossilsarcheology.fossil.item.MammalEmbryoItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import org.blahajenjoyer.block.ReptileEggBlocks;
import org.blahajenjoyer.compat.alexsmobs.entity.ThrownAlexsBirdEgg;
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

public class AlexsMobsCompat {

    public static final String MOD_ID = "alexsmobs";
    public static final List<String> ANIMAL_KEYS = List.of(
        // Birds
        "shoebill", "blue_jay", "bald_eagle", "crow", "emu", "hummingbird", "seagull", "roadrunner", "potoo", "toucan",
        // Mammals
        "anteater", "bison", "cachalot_whale", "capuchin_monkey", "elephant", "kangaroo", "jerboa", "grizzly_bear",
        "gorilla", "gelada_monkey", "gazelle", "maned_wolf", "moose", "orca", "rhinoceros", "raccoon", "seal", "skunk",
        "snow_leopard", "sugar_glider", "tasmanian_devil", "tiger", "tusklin",
        // Fish / amphibians / invertebrates
        "banana_slug", "cockroach", "endergrade", "fly", "mudskipper", "mimic_octopus", "mantis_shrimp", "rain_frog",
        "warped_toad", "blobfish", "catfish", "comb_jelly", "cosmic_cod", "devils_hole_pupfish", "flying_fish",
        "frilled_shark", "giant_squid", "hammerhead_shark", "lobster", "tarantula_hawk", "cosmaw",
        // Reptiles
        "alligator_snapping_turtle", "anaconda", "froststalker", "komodo_dragon", "rattlesnake", "laviathan",
        // DNA only
        "caiman", "crocodile", "platypus", "terrapin", "triops", "leafcutter_ant"
    );

    private static final List<Item> TAB_ITEMS = new ArrayList<>();

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

    // Reptiles — DNA + Egg (placeable, hatches into a baby after a while, like a turtle/sniffer egg)
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

    public static Item LAVIATHAN_DNA;
    public static Item LAVIATHAN_EGG;

    // DNA only — mob already has an in-game egg or equivalent
    public static Item CAIMAN_DNA;
    public static Item CROCODILE_DNA;
    public static Item PLATYPUS_DNA;
    public static Item TERRAPIN_DNA;
    public static Item TRIOPS_DNA;
    public static Item LEAFCUTTER_ANT_DNA;

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

    private static boolean enabled(String key) {
        return FossilsCompatConfig.isEnabled(MOD_ID, key);
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
        if (enabled("shoebill")) {
            SHOEBILL_DNA = registerItem("alexsmobs/shoebill_dna", new Item(new Item.Properties()));
            SHOEBILL_EGG = registerBirdEgg("alexsmobs/shoebill_egg", "shoebill", () -> AMEntityRegistry.SHOEBILL);
            TAB_ITEMS.add(SHOEBILL_DNA);
            TAB_ITEMS.add(SHOEBILL_EGG);
        }
        if (enabled("blue_jay")) {
            BLUE_JAY_DNA = registerItem("alexsmobs/blue_jay_dna", new Item(new Item.Properties()));
            BLUE_JAY_EGG = registerBirdEgg("alexsmobs/blue_jay_egg", "blue_jay", () -> AMEntityRegistry.BLUE_JAY);
            TAB_ITEMS.add(BLUE_JAY_DNA);
            TAB_ITEMS.add(BLUE_JAY_EGG);
        }
        if (enabled("bald_eagle")) {
            BALD_EAGLE_DNA = registerItem("alexsmobs/bald_eagle_dna", new Item(new Item.Properties()));
            BALD_EAGLE_EGG = registerBirdEgg("alexsmobs/bald_eagle_egg", "bald_eagle", () -> AMEntityRegistry.BALD_EAGLE);
            TAB_ITEMS.add(BALD_EAGLE_DNA);
            TAB_ITEMS.add(BALD_EAGLE_EGG);
        }
        if (enabled("crow")) {
            CROW_DNA = registerItem("alexsmobs/crow_dna", new Item(new Item.Properties()));
            CROW_EGG = registerBirdEgg("alexsmobs/crow_egg", "crow", () -> AMEntityRegistry.CROW);
            TAB_ITEMS.add(CROW_DNA);
            TAB_ITEMS.add(CROW_EGG);
        }
        if (enabled("emu")) {
            EMU_DNA = registerItem("alexsmobs/emu_dna", new Item(new Item.Properties()));
            EMU_EGG = registerBirdEgg("alexsmobs/emu_egg", "emu", () -> AMEntityRegistry.EMU);
            TAB_ITEMS.add(EMU_DNA);
            TAB_ITEMS.add(EMU_EGG);
        }
        if (enabled("hummingbird")) {
            HUMMINGBIRD_DNA = registerItem("alexsmobs/hummingbird_dna", new Item(new Item.Properties()));
            HUMMINGBIRD_EGG = registerBirdEgg("alexsmobs/hummingbird_egg", "hummingbird", () -> AMEntityRegistry.HUMMINGBIRD);
            TAB_ITEMS.add(HUMMINGBIRD_DNA);
            TAB_ITEMS.add(HUMMINGBIRD_EGG);
        }
        if (enabled("seagull")) {
            SEAGULL_DNA = registerItem("alexsmobs/seagull_dna", new Item(new Item.Properties()));
            SEAGULL_EGG = registerBirdEgg("alexsmobs/seagull_egg", "seagull", () -> AMEntityRegistry.SEAGULL);
            TAB_ITEMS.add(SEAGULL_DNA);
            TAB_ITEMS.add(SEAGULL_EGG);
        }
        if (enabled("roadrunner")) {
            ROADRUNNER_DNA = registerItem("alexsmobs/roadrunner_dna", new Item(new Item.Properties()));
            ROADRUNNER_EGG = registerBirdEgg("alexsmobs/roadrunner_egg", "roadrunner", () -> AMEntityRegistry.ROADRUNNER);
            TAB_ITEMS.add(ROADRUNNER_DNA);
            TAB_ITEMS.add(ROADRUNNER_EGG);
        }
        if (enabled("potoo")) {
            POTOO_DNA = registerItem("alexsmobs/potoo_dna", new Item(new Item.Properties()));
            POTOO_EGG = registerBirdEgg("alexsmobs/potoo_egg", "potoo", () -> AMEntityRegistry.POTOO);
            TAB_ITEMS.add(POTOO_DNA);
            TAB_ITEMS.add(POTOO_EGG);
        }
        if (enabled("toucan")) {
            TOUCAN_DNA = registerItem("alexsmobs/toucan_dna", new Item(new Item.Properties()));
            TOUCAN_EGG = registerBirdEgg("alexsmobs/toucan_egg", "toucan", () -> AMEntityRegistry.TOUCAN);
            TAB_ITEMS.add(TOUCAN_DNA);
            TAB_ITEMS.add(TOUCAN_EGG);
        }

        // Mammals — DNA item + MammalEmbryoItem (FAR's own pregnancy/birth mechanic:
        // right-click an eligible adult mammal to implant, it gestates, then gives birth)
        if (enabled("anteater")) {
            ANTEATER_DNA = registerItem("alexsmobs/anteater_dna", new Item(new Item.Properties()));
            ANTEATER_EMBRYO = registerMammalEmbryo("alexsmobs/anteater_embryo", "anteater", () -> AMEntityRegistry.ANTEATER, ANTEATER_DNA);
            TAB_ITEMS.add(ANTEATER_DNA);
            TAB_ITEMS.add(ANTEATER_EMBRYO);
        }
        if (enabled("bison")) {
            BISON_DNA = registerItem("alexsmobs/bison_dna", new Item(new Item.Properties()));
            BISON_EMBRYO = registerMammalEmbryo("alexsmobs/bison_embryo", "bison", () -> AMEntityRegistry.BISON, BISON_DNA);
            TAB_ITEMS.add(BISON_DNA);
            TAB_ITEMS.add(BISON_EMBRYO);
        }
        if (enabled("cachalot_whale")) {
            CACHALOT_WHALE_DNA = registerItem("alexsmobs/cachalot_whale_dna", new Item(new Item.Properties()));
            CACHALOT_WHALE_EMBRYO = registerMammalEmbryo("alexsmobs/cachalot_whale_embryo", "cachalot_whale", () -> AMEntityRegistry.CACHALOT_WHALE, CACHALOT_WHALE_DNA);
            TAB_ITEMS.add(CACHALOT_WHALE_DNA);
            TAB_ITEMS.add(CACHALOT_WHALE_EMBRYO);
        }
        if (enabled("capuchin_monkey")) {
            CAPUCHIN_MONKEY_DNA = registerItem("alexsmobs/capuchin_monkey_dna", new Item(new Item.Properties()));
            CAPUCHIN_MONKEY_EMBRYO = registerMammalEmbryo("alexsmobs/capuchin_monkey_embryo", "capuchin_monkey", () -> AMEntityRegistry.CAPUCHIN_MONKEY, CAPUCHIN_MONKEY_DNA);
            TAB_ITEMS.add(CAPUCHIN_MONKEY_DNA);
            TAB_ITEMS.add(CAPUCHIN_MONKEY_EMBRYO);
        }
        if (enabled("elephant")) {
            ELEPHANT_DNA = registerItem("alexsmobs/elephant_dna", new Item(new Item.Properties()));
            ELEPHANT_EMBRYO = registerMammalEmbryo("alexsmobs/elephant_embryo", "elephant", () -> AMEntityRegistry.ELEPHANT, ELEPHANT_DNA);
            TAB_ITEMS.add(ELEPHANT_DNA);
            TAB_ITEMS.add(ELEPHANT_EMBRYO);
        }
        if (enabled("kangaroo")) {
            KANGAROO_DNA = registerItem("alexsmobs/kangaroo_dna", new Item(new Item.Properties()));
            KANGAROO_EMBRYO = registerMammalEmbryo("alexsmobs/kangaroo_embryo", "kangaroo", () -> AMEntityRegistry.KANGAROO, KANGAROO_DNA);
            TAB_ITEMS.add(KANGAROO_DNA);
            TAB_ITEMS.add(KANGAROO_EMBRYO);
        }
        if (enabled("jerboa")) {
            JERBOA_DNA = registerItem("alexsmobs/jerboa_dna", new Item(new Item.Properties()));
            JERBOA_EMBRYO = registerMammalEmbryo("alexsmobs/jerboa_embryo", "jerboa", () -> AMEntityRegistry.JERBOA, JERBOA_DNA);
            TAB_ITEMS.add(JERBOA_DNA);
            TAB_ITEMS.add(JERBOA_EMBRYO);
        }
        if (enabled("grizzly_bear")) {
            GRIZZLY_BEAR_DNA = registerItem("alexsmobs/grizzly_bear_dna", new Item(new Item.Properties()));
            GRIZZLY_BEAR_EMBRYO = registerMammalEmbryo("alexsmobs/grizzly_bear_embryo", "grizzly_bear", () -> AMEntityRegistry.GRIZZLY_BEAR, GRIZZLY_BEAR_DNA);
            TAB_ITEMS.add(GRIZZLY_BEAR_DNA);
            TAB_ITEMS.add(GRIZZLY_BEAR_EMBRYO);
        }
        if (enabled("gorilla")) {
            GORILLA_DNA = registerItem("alexsmobs/gorilla_dna", new Item(new Item.Properties()));
            GORILLA_EMBRYO = registerMammalEmbryo("alexsmobs/gorilla_embryo", "gorilla", () -> AMEntityRegistry.GORILLA, GORILLA_DNA);
            TAB_ITEMS.add(GORILLA_DNA);
            TAB_ITEMS.add(GORILLA_EMBRYO);
        }
        if (enabled("gelada_monkey")) {
            GELADA_MONKEY_DNA = registerItem("alexsmobs/gelada_monkey_dna", new Item(new Item.Properties()));
            GELADA_MONKEY_EMBRYO = registerMammalEmbryo("alexsmobs/gelada_monkey_embryo", "gelada_monkey", () -> AMEntityRegistry.GELADA_MONKEY, GELADA_MONKEY_DNA);
            TAB_ITEMS.add(GELADA_MONKEY_DNA);
            TAB_ITEMS.add(GELADA_MONKEY_EMBRYO);
        }
        if (enabled("gazelle")) {
            GAZELLE_DNA = registerItem("alexsmobs/gazelle_dna", new Item(new Item.Properties()));
            GAZELLE_EMBRYO = registerMammalEmbryo("alexsmobs/gazelle_embryo", "gazelle", () -> AMEntityRegistry.GAZELLE, GAZELLE_DNA);
            TAB_ITEMS.add(GAZELLE_DNA);
            TAB_ITEMS.add(GAZELLE_EMBRYO);
        }
        if (enabled("maned_wolf")) {
            MANED_WOLF_DNA = registerItem("alexsmobs/maned_wolf_dna", new Item(new Item.Properties()));
            MANED_WOLF_EMBRYO = registerMammalEmbryo("alexsmobs/maned_wolf_embryo", "maned_wolf", () -> AMEntityRegistry.MANED_WOLF, MANED_WOLF_DNA);
            TAB_ITEMS.add(MANED_WOLF_DNA);
            TAB_ITEMS.add(MANED_WOLF_EMBRYO);
        }
        if (enabled("moose")) {
            MOOSE_DNA = registerItem("alexsmobs/moose_dna", new Item(new Item.Properties()));
            MOOSE_EMBRYO = registerMammalEmbryo("alexsmobs/moose_embryo", "moose", () -> AMEntityRegistry.MOOSE, MOOSE_DNA);
            TAB_ITEMS.add(MOOSE_DNA);
            TAB_ITEMS.add(MOOSE_EMBRYO);
        }
        if (enabled("orca")) {
            ORCA_DNA = registerItem("alexsmobs/orca_dna", new Item(new Item.Properties()));
            ORCA_EMBRYO = registerMammalEmbryo("alexsmobs/orca_embryo", "orca", () -> AMEntityRegistry.ORCA, ORCA_DNA);
            TAB_ITEMS.add(ORCA_DNA);
            TAB_ITEMS.add(ORCA_EMBRYO);
        }
        if (enabled("rhinoceros")) {
            RHINOCEROS_DNA = registerItem("alexsmobs/rhinoceros_dna", new Item(new Item.Properties()));
            RHINOCEROS_EMBRYO = registerMammalEmbryo("alexsmobs/rhinoceros_embryo", "rhinoceros", () -> AMEntityRegistry.RHINOCEROS, RHINOCEROS_DNA);
            TAB_ITEMS.add(RHINOCEROS_DNA);
            TAB_ITEMS.add(RHINOCEROS_EMBRYO);
        }
        if (enabled("raccoon")) {
            RACCOON_DNA = registerItem("alexsmobs/raccoon_dna", new Item(new Item.Properties()));
            RACCOON_EMBRYO = registerMammalEmbryo("alexsmobs/raccoon_embryo", "raccoon", () -> AMEntityRegistry.RACCOON, RACCOON_DNA);
            TAB_ITEMS.add(RACCOON_DNA);
            TAB_ITEMS.add(RACCOON_EMBRYO);
        }
        if (enabled("seal")) {
            SEAL_DNA = registerItem("alexsmobs/seal_dna", new Item(new Item.Properties()));
            SEAL_EMBRYO = registerMammalEmbryo("alexsmobs/seal_embryo", "seal", () -> AMEntityRegistry.SEAL, SEAL_DNA);
            TAB_ITEMS.add(SEAL_DNA);
            TAB_ITEMS.add(SEAL_EMBRYO);
        }
        if (enabled("skunk")) {
            SKUNK_DNA = registerItem("alexsmobs/skunk_dna", new Item(new Item.Properties()));
            SKUNK_EMBRYO = registerMammalEmbryo("alexsmobs/skunk_embryo", "skunk", () -> AMEntityRegistry.SKUNK, SKUNK_DNA);
            TAB_ITEMS.add(SKUNK_DNA);
            TAB_ITEMS.add(SKUNK_EMBRYO);
        }
        if (enabled("snow_leopard")) {
            SNOW_LEOPARD_DNA = registerItem("alexsmobs/snow_leopard_dna", new Item(new Item.Properties()));
            SNOW_LEOPARD_EMBRYO = registerMammalEmbryo("alexsmobs/snow_leopard_embryo", "snow_leopard", () -> AMEntityRegistry.SNOW_LEOPARD, SNOW_LEOPARD_DNA);
            TAB_ITEMS.add(SNOW_LEOPARD_DNA);
            TAB_ITEMS.add(SNOW_LEOPARD_EMBRYO);
        }
        if (enabled("sugar_glider")) {
            SUGAR_GLIDER_DNA = registerItem("alexsmobs/sugar_glider_dna", new Item(new Item.Properties()));
            SUGAR_GLIDER_EMBRYO = registerMammalEmbryo("alexsmobs/sugar_glider_embryo", "sugar_glider", () -> AMEntityRegistry.SUGAR_GLIDER, SUGAR_GLIDER_DNA);
            TAB_ITEMS.add(SUGAR_GLIDER_DNA);
            TAB_ITEMS.add(SUGAR_GLIDER_EMBRYO);
        }
        if (enabled("tasmanian_devil")) {
            TASMANIAN_DEVIL_DNA = registerItem("alexsmobs/tasmanian_devil_dna", new Item(new Item.Properties()));
            TASMANIAN_DEVIL_EMBRYO = registerMammalEmbryo("alexsmobs/tasmanian_devil_embryo", "tasmanian_devil", () -> AMEntityRegistry.TASMANIAN_DEVIL, TASMANIAN_DEVIL_DNA);
            TAB_ITEMS.add(TASMANIAN_DEVIL_DNA);
            TAB_ITEMS.add(TASMANIAN_DEVIL_EMBRYO);
        }
        if (enabled("tiger")) {
            TIGER_DNA = registerItem("alexsmobs/tiger_dna", new Item(new Item.Properties()));
            TIGER_EMBRYO = registerMammalEmbryo("alexsmobs/tiger_embryo", "tiger", () -> AMEntityRegistry.TIGER, TIGER_DNA);
            TAB_ITEMS.add(TIGER_DNA);
            TAB_ITEMS.add(TIGER_EMBRYO);
        }
        if (enabled("tusklin")) {
            TUSKLIN_DNA = registerItem("alexsmobs/tusklin_dna", new Item(new Item.Properties()));
            TUSKLIN_EMBRYO = registerMammalEmbryo("alexsmobs/tusklin_embryo", "tusklin", () -> AMEntityRegistry.TUSKLIN, TUSKLIN_DNA);
            TAB_ITEMS.add(TUSKLIN_DNA);
            TAB_ITEMS.add(TUSKLIN_EMBRYO);
        }

        // Fish / amphibians / invertebrates — baby on hatch
        if (enabled("banana_slug")) {
            BANANA_SLUG_DNA = registerItem("alexsmobs/banana_slug_dna", new Item(new Item.Properties()));
            BANANA_SLUG_EGG = registerCreatureEgg("alexsmobs/banana_slug_egg", () -> AMEntityRegistry.BANANA_SLUG, true);
            TAB_ITEMS.add(BANANA_SLUG_DNA);
            TAB_ITEMS.add(BANANA_SLUG_EGG);
        }
        if (enabled("cockroach")) {
            COCKROACH_DNA = registerItem("alexsmobs/cockroach_dna", new Item(new Item.Properties()));
            COCKROACH_EGG = registerCreatureEgg("alexsmobs/cockroach_egg", () -> AMEntityRegistry.COCKROACH, true);
            TAB_ITEMS.add(COCKROACH_DNA);
            TAB_ITEMS.add(COCKROACH_EGG);
        }
        if (enabled("endergrade")) {
            ENDERGRADE_DNA = registerItem("alexsmobs/endergrade_dna", new Item(new Item.Properties()));
            ENDERGRADE_EGG = registerCreatureEgg("alexsmobs/endergrade_egg", () -> AMEntityRegistry.ENDERGRADE, true);
            TAB_ITEMS.add(ENDERGRADE_DNA);
            TAB_ITEMS.add(ENDERGRADE_EGG);
        }
        if (enabled("fly")) {
            FLY_DNA = registerItem("alexsmobs/fly_dna", new Item(new Item.Properties()));
            FLY_EGG = registerCreatureEgg("alexsmobs/fly_egg", () -> AMEntityRegistry.FLY, true);
            TAB_ITEMS.add(FLY_DNA);
            TAB_ITEMS.add(FLY_EGG);
        }
        if (enabled("mudskipper")) {
            MUDSKIPPER_DNA = registerItem("alexsmobs/mudskipper_dna", new Item(new Item.Properties()));
            MUDSKIPPER_EGG = registerCreatureEgg("alexsmobs/mudskipper_egg", () -> AMEntityRegistry.MUDSKIPPER, true);
            TAB_ITEMS.add(MUDSKIPPER_DNA);
            TAB_ITEMS.add(MUDSKIPPER_EGG);
        }
        if (enabled("mimic_octopus")) {
            MIMIC_OCTOPUS_DNA = registerItem("alexsmobs/mimic_octopus_dna", new Item(new Item.Properties()));
            MIMIC_OCTOPUS_EGG = registerCreatureEgg("alexsmobs/mimic_octopus_egg", () -> AMEntityRegistry.MIMIC_OCTOPUS, true);
            TAB_ITEMS.add(MIMIC_OCTOPUS_DNA);
            TAB_ITEMS.add(MIMIC_OCTOPUS_EGG);
        }
        if (enabled("mantis_shrimp")) {
            MANTIS_SHRIMP_DNA = registerItem("alexsmobs/mantis_shrimp_dna", new Item(new Item.Properties()));
            MANTIS_SHRIMP_EGG = registerCreatureEgg("alexsmobs/mantis_shrimp_egg", () -> AMEntityRegistry.MANTIS_SHRIMP, true);
            TAB_ITEMS.add(MANTIS_SHRIMP_DNA);
            TAB_ITEMS.add(MANTIS_SHRIMP_EGG);
        }
        if (enabled("rain_frog")) {
            RAIN_FROG_DNA = registerItem("alexsmobs/rain_frog_dna", new Item(new Item.Properties()));
            RAIN_FROG_EGG = registerCreatureEgg("alexsmobs/rain_frog_egg", () -> AMEntityRegistry.RAIN_FROG, true);
            TAB_ITEMS.add(RAIN_FROG_DNA);
            TAB_ITEMS.add(RAIN_FROG_EGG);
        }
        if (enabled("warped_toad")) {
            WARPED_TOAD_DNA = registerItem("alexsmobs/warped_toad_dna", new Item(new Item.Properties()));
            WARPED_TOAD_EGG = registerCreatureEgg("alexsmobs/warped_toad_egg", () -> AMEntityRegistry.WARPED_TOAD, true);
            TAB_ITEMS.add(WARPED_TOAD_DNA);
            TAB_ITEMS.add(WARPED_TOAD_EGG);
        }

        // Fish / amphibians / invertebrates — adult on hatch
        if (enabled("blobfish")) {
            BLOBFISH_DNA = registerItem("alexsmobs/blobfish_dna", new Item(new Item.Properties()));
            BLOBFISH_EGG = registerCreatureEgg("alexsmobs/blobfish_egg", () -> AMEntityRegistry.BLOBFISH, false);
            TAB_ITEMS.add(BLOBFISH_DNA);
            TAB_ITEMS.add(BLOBFISH_EGG);
        }
        if (enabled("catfish")) {
            CATFISH_DNA = registerItem("alexsmobs/catfish_dna", new Item(new Item.Properties()));
            CATFISH_EGG = registerCreatureEgg("alexsmobs/catfish_egg", () -> AMEntityRegistry.CATFISH, false);
            TAB_ITEMS.add(CATFISH_DNA);
            TAB_ITEMS.add(CATFISH_EGG);
        }
        if (enabled("comb_jelly")) {
            COMB_JELLY_DNA = registerItem("alexsmobs/comb_jelly_dna", new Item(new Item.Properties()));
            COMB_JELLY_EGG = registerCreatureEgg("alexsmobs/comb_jelly_egg", () -> AMEntityRegistry.COMB_JELLY, false);
            TAB_ITEMS.add(COMB_JELLY_DNA);
            TAB_ITEMS.add(COMB_JELLY_EGG);
        }
        if (enabled("cosmic_cod")) {
            COSMIC_COD_DNA = registerItem("alexsmobs/cosmic_cod_dna", new Item(new Item.Properties()));
            COSMIC_COD_EGG = registerCreatureEgg("alexsmobs/cosmic_cod_egg", () -> AMEntityRegistry.COSMIC_COD, false);
            TAB_ITEMS.add(COSMIC_COD_DNA);
            TAB_ITEMS.add(COSMIC_COD_EGG);
        }
        if (enabled("devils_hole_pupfish")) {
            DEVILS_HOLE_PUPFISH_DNA = registerItem("alexsmobs/devils_hole_pupfish_dna", new Item(new Item.Properties()));
            DEVILS_HOLE_PUPFISH_EGG = registerCreatureEgg("alexsmobs/devils_hole_pupfish_egg", () -> AMEntityRegistry.DEVILS_HOLE_PUPFISH, false);
            TAB_ITEMS.add(DEVILS_HOLE_PUPFISH_DNA);
            TAB_ITEMS.add(DEVILS_HOLE_PUPFISH_EGG);
        }
        if (enabled("flying_fish")) {
            FLYING_FISH_DNA = registerItem("alexsmobs/flying_fish_dna", new Item(new Item.Properties()));
            FLYING_FISH_EGG = registerCreatureEgg("alexsmobs/flying_fish_egg", () -> AMEntityRegistry.FLYING_FISH, false);
            TAB_ITEMS.add(FLYING_FISH_DNA);
            TAB_ITEMS.add(FLYING_FISH_EGG);
        }
        if (enabled("frilled_shark")) {
            FRILLED_SHARK_DNA = registerItem("alexsmobs/frilled_shark_dna", new Item(new Item.Properties()));
            FRILLED_SHARK_EGG = registerCreatureEgg("alexsmobs/frilled_shark_egg", () -> AMEntityRegistry.FRILLED_SHARK, false);
            TAB_ITEMS.add(FRILLED_SHARK_DNA);
            TAB_ITEMS.add(FRILLED_SHARK_EGG);
        }
        if (enabled("giant_squid")) {
            GIANT_SQUID_DNA = registerItem("alexsmobs/giant_squid_dna", new Item(new Item.Properties()));
            GIANT_SQUID_EGG = registerCreatureEgg("alexsmobs/giant_squid_egg", () -> AMEntityRegistry.GIANT_SQUID, false);
            TAB_ITEMS.add(GIANT_SQUID_DNA);
            TAB_ITEMS.add(GIANT_SQUID_EGG);
        }
        if (enabled("hammerhead_shark")) {
            HAMMERHEAD_SHARK_DNA = registerItem("alexsmobs/hammerhead_shark_dna", new Item(new Item.Properties()));
            HAMMERHEAD_SHARK_EGG = registerCreatureEgg("alexsmobs/hammerhead_shark_egg", () -> AMEntityRegistry.HAMMERHEAD_SHARK, false);
            TAB_ITEMS.add(HAMMERHEAD_SHARK_DNA);
            TAB_ITEMS.add(HAMMERHEAD_SHARK_EGG);
        }
        if (enabled("lobster")) {
            LOBSTER_DNA = registerItem("alexsmobs/lobster_dna", new Item(new Item.Properties()));
            LOBSTER_EGG = registerCreatureEgg("alexsmobs/lobster_egg", () -> AMEntityRegistry.LOBSTER, false);
            TAB_ITEMS.add(LOBSTER_DNA);
            TAB_ITEMS.add(LOBSTER_EGG);
        }
        if (enabled("tarantula_hawk")) {
            TARANTULA_HAWK_DNA = registerItem("alexsmobs/tarantula_hawk_dna", new Item(new Item.Properties()));
            TARANTULA_HAWK_EGG = registerCreatureEgg("alexsmobs/tarantula_hawk_egg", () -> AMEntityRegistry.TARANTULA_HAWK, false);
            TAB_ITEMS.add(TARANTULA_HAWK_DNA);
            TAB_ITEMS.add(TARANTULA_HAWK_EGG);
        }
        if (enabled("cosmaw")) {
            COSMAW_DNA = registerItem("alexsmobs/cosmaw_dna", new Item(new Item.Properties()));
            COSMAW_EGG = registerCreatureEgg("alexsmobs/cosmaw_egg", () -> AMEntityRegistry.COSMAW, true);
            TAB_ITEMS.add(COSMAW_DNA);
            TAB_ITEMS.add(COSMAW_EGG);
        }

        // Reptiles — placeable egg block, hatches into a baby after a while
        if (enabled("alligator_snapping_turtle")) {
            ALLIGATOR_SNAPPING_TURTLE_DNA = registerItem("alexsmobs/alligator_snapping_turtle_dna", new Item(new Item.Properties()));
            ALLIGATOR_SNAPPING_TURTLE_EGG = ReptileEggBlocks.registerSmall("alexsmobs/alligator_snapping_turtle_egg", () -> AMEntityRegistry.ALLIGATOR_SNAPPING_TURTLE, true);
            TAB_ITEMS.add(ALLIGATOR_SNAPPING_TURTLE_DNA);
            TAB_ITEMS.add(ALLIGATOR_SNAPPING_TURTLE_EGG);
        }
        if (enabled("anaconda")) {
            ANACONDA_DNA = registerItem("alexsmobs/anaconda_dna", new Item(new Item.Properties()));
            ANACONDA_EGG = ReptileEggBlocks.registerMedium("alexsmobs/anaconda_egg", () -> AMEntityRegistry.ANACONDA, true);
            TAB_ITEMS.add(ANACONDA_DNA);
            TAB_ITEMS.add(ANACONDA_EGG);
        }
        if (enabled("froststalker")) {
            FROSTSTALKER_DNA = registerItem("alexsmobs/froststalker_dna", new Item(new Item.Properties()));
            FROSTSTALKER_EGG = ReptileEggBlocks.registerMedium("alexsmobs/froststalker_egg", () -> AMEntityRegistry.FROSTSTALKER, true);
            TAB_ITEMS.add(FROSTSTALKER_DNA);
            TAB_ITEMS.add(FROSTSTALKER_EGG);
        }
        if (enabled("komodo_dragon")) {
            KOMODO_DRAGON_DNA = registerItem("alexsmobs/komodo_dragon_dna", new Item(new Item.Properties()));
            KOMODO_DRAGON_EGG = ReptileEggBlocks.registerMedium("alexsmobs/komodo_dragon_egg", () -> AMEntityRegistry.KOMODO_DRAGON, true);
            TAB_ITEMS.add(KOMODO_DRAGON_DNA);
            TAB_ITEMS.add(KOMODO_DRAGON_EGG);
        }
        if (enabled("rattlesnake")) {
            RATTLESNAKE_DNA = registerItem("alexsmobs/rattlesnake_dna", new Item(new Item.Properties()));
            RATTLESNAKE_EGG = ReptileEggBlocks.registerSmall("alexsmobs/rattlesnake_egg", () -> AMEntityRegistry.RATTLESNAKE, true, BlockTags.SAND);
            TAB_ITEMS.add(RATTLESNAKE_DNA);
            TAB_ITEMS.add(RATTLESNAKE_EGG);
        }
        if (enabled("laviathan")) {
            LAVIATHAN_DNA = registerItem("alexsmobs/laviathan_dna", new Item(new Item.Properties()));
            LAVIATHAN_EGG = ReptileEggBlocks.registerLarge("alexsmobs/laviathan_egg", () -> AMEntityRegistry.LAVIATHAN, true, ReptileEggBlocks.LAVA_EGG_HATCH_BOOST);
            TAB_ITEMS.add(LAVIATHAN_DNA);
            TAB_ITEMS.add(LAVIATHAN_EGG);
        }

        // DNA only
        if (enabled("caiman")) {
            CAIMAN_DNA = registerItem("alexsmobs/caiman_dna", new Item(new Item.Properties()));
            TAB_ITEMS.add(CAIMAN_DNA);
        }
        if (enabled("crocodile")) {
            CROCODILE_DNA = registerItem("alexsmobs/crocodile_dna", new Item(new Item.Properties()));
            TAB_ITEMS.add(CROCODILE_DNA);
        }
        if (enabled("platypus")) {
            PLATYPUS_DNA = registerItem("alexsmobs/platypus_dna", new Item(new Item.Properties()));
            TAB_ITEMS.add(PLATYPUS_DNA);
        }
        if (enabled("terrapin")) {
            TERRAPIN_DNA = registerItem("alexsmobs/terrapin_dna", new Item(new Item.Properties()));
            TAB_ITEMS.add(TERRAPIN_DNA);
        }
        if (enabled("triops")) {
            TRIOPS_DNA = registerItem("alexsmobs/triops_dna", new Item(new Item.Properties()));
            TAB_ITEMS.add(TRIOPS_DNA);
        }
        if (enabled("leafcutter_ant")) {
            LEAFCUTTER_ANT_DNA = registerItem("alexsmobs/leafcutter_ant_dna", new Item(new Item.Properties()));
            TAB_ITEMS.add(LEAFCUTTER_ANT_DNA);
        }

        ItemGroupEvents.modifyEntriesEvent(FossilsCompatUtil.FOSSIL_MOB_TAB).register(entries -> TAB_ITEMS.forEach(entries::accept));
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

    private static Item registerMammalEmbryo(String id, String key, Supplier<EntityType<?>> entityType, Item dnaItem) {
        return registerItem(id, new MammalEmbryoItem(new SimpleEntityInfo(
            "ALEXSMOBS_" + key.toUpperCase(Locale.ROOT), entityType, PrehistoricMobType.MAMMAL,
            () -> Component.translatable("entity.alexsmobs." + key), dnaItem)));
    }
}
