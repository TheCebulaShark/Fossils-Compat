package org.blahajenjoyer.compat.naturalist;

import com.crispytwig.naturalist.registry.NaturalistEntityTypes;
import com.crispytwig.naturalist.world.entity.variant.DataDrivenVariantAnimal;
import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.PrehistoricMobType;
import com.github.teamfossilsarcheology.fossil.item.MammalEmbryoItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import org.blahajenjoyer.block.ReptileEggBlocks;
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
import java.util.function.Consumer;
import java.util.function.Supplier;

public class NaturalistCompat {

    public static final String MOD_ID = "naturalist";
    public static final List<String> ANIMAL_KEYS = List.of(
        "blue_jay", "cardinal", "carolina_chickadee", "sparrow", "robin", "vulture", "duck", "turkey",
        "catfish", "bass", "firefly", "dragonfly",
        "anglerfish", "ray", "blobfish", "piranha", "starfish", "great_white_shark", "jungle_scorpion", "crab", "giant_isopod",
        "alligator", "tortoise", "snail", "butterfly", "desert_scorpion", "ant", "ostrich",
        "coral_snake", "snake", "rattlesnake", "lizard", "komodo_dragon",
        "bear", "boar", "hippo", "giraffe", "elephant", "deer", "lion", "rhino", "zebra",
        "black_bear", "capybara", "hedgehog", "mammoth", "mole", "rat", "tiger", "leopard", "whale"
    );

    private static final List<Item> TAB_ITEMS = new ArrayList<>();

    public static EntityType<ThrownNaturalistBirdEgg> THROWN_NATURALIST_BIRD_EGG;

    public static Item BLUE_JAY_DNA;
    public static Item BLUE_JAY_EGG;
    public static Item CARDINAL_DNA;
    public static Item CARDINAL_EGG;
    public static Item CAROLINA_CHICKADEE_DNA;
    public static Item CAROLINA_CHICKADEE_EGG;
    public static Item SPARROW_DNA;
    public static Item SPARROW_EGG;
    public static Item ROBIN_DNA;
    public static Item ROBIN_EGG;
    public static Item VULTURE_DNA;
    public static Item VULTURE_EGG;
    public static Item DUCK_DNA;
    public static Item DUCK_EGG;
    public static Item TURKEY_DNA;
    public static Item TURKEY_EGG;

    // Fish / invertebrates — DNA + spawn-egg-like Egg (adult on hatch)
    public static Item CATFISH_DNA;
    public static Item CATFISH_EGG;
    public static Item BASS_DNA;
    public static Item BASS_EGG;
    public static Item FIREFLY_DNA;
    public static Item FIREFLY_EGG;
    public static Item DRAGONFLY_DNA;
    public static Item DRAGONFLY_EGG;
    public static Item ANGLERFISH_DNA;
    public static Item ANGLERFISH_EGG;
    public static Item RAY_DNA;
    public static Item RAY_EGG;
    public static Item BLOBFISH_DNA;
    public static Item BLOBFISH_EGG;
    public static Item PIRANHA_DNA;
    public static Item PIRANHA_EGG;
    public static Item STARFISH_DNA;
    public static Item STARFISH_EGG;
    public static Item GREAT_WHITE_SHARK_DNA;
    public static Item GREAT_WHITE_SHARK_EGG;
    public static Item JUNGLE_SCORPION_DNA;
    public static Item JUNGLE_SCORPION_EGG;
    public static Item CRAB_DNA;
    public static Item CRAB_EGG;
    public static Item GIANT_ISOPOD_DNA;
    public static Item GIANT_ISOPOD_EGG;

    // DNA only — mob already has an in-game egg or equivalent
    public static Item ALLIGATOR_DNA;
    public static Item TORTOISE_DNA;
    public static Item SNAIL_DNA;
    public static Item BUTTERFLY_DNA;
    public static Item DESERT_SCORPION_DNA;
    public static Item ANT_DNA;
    public static Item OSTRICH_DNA;

    // Reptiles — DNA + Egg (placeable, hatches into a baby after a while, like a turtle/sniffer egg)
    public static Item CORAL_SNAKE_DNA;
    public static Item CORAL_SNAKE_EGG;
    public static Item SNAKE_DNA;
    public static Item SNAKE_EGG;
    public static Item RATTLESNAKE_DNA;
    public static Item RATTLESNAKE_EGG;
    public static Item LIZARD_DNA;
    public static Item LIZARD_EGG;
    public static Item KOMODO_DRAGON_DNA;
    public static Item KOMODO_DRAGON_EGG;

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
    public static Item BLACK_BEAR_DNA;
    public static Item BLACK_BEAR_EMBRYO;
    public static Item CAPYBARA_DNA;
    public static Item CAPYBARA_EMBRYO;
    public static Item HEDGEHOG_DNA;
    public static Item HEDGEHOG_EMBRYO;
    public static Item MAMMOTH_DNA;
    public static Item MAMMOTH_EMBRYO;
    public static Item MOLE_DNA;
    public static Item MOLE_EMBRYO;
    public static Item RAT_DNA;
    public static Item RAT_EMBRYO;
    public static Item TIGER_DNA;
    public static Item TIGER_EMBRYO;
    public static Item LEOPARD_DNA;
    public static Item LEOPARD_EMBRYO;
    public static Item WHALE_DNA;
    public static Item WHALE_EMBRYO;

    private static final Map<String, Supplier<EntityType<?>>> ENTITY_SUPPLIERS = new HashMap<>();
    private static final Map<String, Item> EGG_BY_KEY = new HashMap<>();
    // Naturalist 2.0 merged songbirds into one naturalist:bird and coral snake/rattlesnake into
    // naturalist:snake, as data-driven variants — eggs force the variant after finalizeSpawn,
    // which would otherwise roll a biome-based one.
    private static final Map<String, String> VARIANT_BY_KEY = Map.of(
        "blue_jay", "naturalist:blue_jay",
        "cardinal", "naturalist:northern_cardinal",
        "carolina_chickadee", "naturalist:carolina_chickadee",
        "sparrow", "naturalist:white_throated_sparrow",
        "robin", "naturalist:american_robin",
        "coral_snake", "naturalist:coral_snake",
        "snake", "naturalist:snake",
        "rattlesnake", "naturalist:rattlesnake"
    );
    private static final Set<String> BABY_ON_HATCH = Set.of("duck", "turkey");
    // Mobula ray and stingray have spawn weight 0 in Naturalist (never spawn naturally), so the egg
    // picks evenly between all three to make them obtainable.
    private static final List<String> RAY_VARIANTS = List.of("naturalist:eagle_ray", "naturalist:mobula_ray", "naturalist:stingray");

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

    public static void applyVariant(Mob mob, String key) {
        setVariant(mob, VARIANT_BY_KEY.get(key));
    }

    private static void setVariant(Mob mob, String variant) {
        if (variant != null && mob instanceof DataDrivenVariantAnimal variantAnimal) {
            variantAnimal.setVariantString(variant);
        }
    }

    /** Tiger and leopard are both naturalist:tiger variants; each embryo has a small chance of its rare colour morph. */
    private static void rollVariant(Mob mob, String common, String rare, int rareOneIn) {
        setVariant(mob, mob.getRandom().nextInt(rareOneIn) == 0 ? rare : common);
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
            BLUE_JAY_EGG = registerBirdEgg("naturalist/blue_jay_egg", "blue_jay", () -> NaturalistEntityTypes.BIRD.get());
            TAB_ITEMS.add(BLUE_JAY_DNA);
            TAB_ITEMS.add(BLUE_JAY_EGG);
        }
        if (enabled("cardinal")) {
            CARDINAL_DNA = FossilsCompatUtil.registerItem("naturalist/cardinal_dna", new Item(new Item.Properties()));
            CARDINAL_EGG = registerBirdEgg("naturalist/cardinal_egg", "cardinal", () -> NaturalistEntityTypes.BIRD.get());
            TAB_ITEMS.add(CARDINAL_DNA);
            TAB_ITEMS.add(CARDINAL_EGG);
        }
        if (enabled("carolina_chickadee")) {
            CAROLINA_CHICKADEE_DNA = FossilsCompatUtil.registerItem("naturalist/carolina_chickadee_dna", new Item(new Item.Properties()));
            CAROLINA_CHICKADEE_EGG = registerBirdEgg("naturalist/carolina_chickadee_egg", "carolina_chickadee", () -> NaturalistEntityTypes.BIRD.get());
            TAB_ITEMS.add(CAROLINA_CHICKADEE_DNA);
            TAB_ITEMS.add(CAROLINA_CHICKADEE_EGG);
        }
        if (enabled("sparrow")) {
            SPARROW_DNA = FossilsCompatUtil.registerItem("naturalist/sparrow_dna", new Item(new Item.Properties()));
            SPARROW_EGG = registerBirdEgg("naturalist/sparrow_egg", "sparrow", () -> NaturalistEntityTypes.BIRD.get());
            TAB_ITEMS.add(SPARROW_DNA);
            TAB_ITEMS.add(SPARROW_EGG);
        }
        if (enabled("robin")) {
            ROBIN_DNA = FossilsCompatUtil.registerItem("naturalist/robin_dna", new Item(new Item.Properties()));
            ROBIN_EGG = registerBirdEgg("naturalist/robin_egg", "robin", () -> NaturalistEntityTypes.BIRD.get());
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
        if (enabled("turkey")) {
            TURKEY_DNA = FossilsCompatUtil.registerItem("naturalist/turkey_dna", new Item(new Item.Properties()));
            TURKEY_EGG = registerBirdEgg("naturalist/turkey_egg", "turkey", () -> NaturalistEntityTypes.TURKEY.get());
            TAB_ITEMS.add(TURKEY_DNA);
            TAB_ITEMS.add(TURKEY_EGG);
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
        if (enabled("anglerfish")) {
            ANGLERFISH_DNA = FossilsCompatUtil.registerItem("naturalist/anglerfish_dna", new Item(new Item.Properties()));
            ANGLERFISH_EGG = registerCreatureEgg("naturalist/anglerfish_egg", () -> NaturalistEntityTypes.ANGLERFISH.get());
            TAB_ITEMS.add(ANGLERFISH_DNA);
            TAB_ITEMS.add(ANGLERFISH_EGG);
        }
        if (enabled("ray")) {
            RAY_DNA = FossilsCompatUtil.registerItem("naturalist/ray_dna", new Item(new Item.Properties()));
            RAY_EGG = registerCreatureEgg("naturalist/ray_egg", () -> NaturalistEntityTypes.RAY.get(),
                mob -> setVariant(mob, RAY_VARIANTS.get(mob.getRandom().nextInt(RAY_VARIANTS.size()))));
            TAB_ITEMS.add(RAY_DNA);
            TAB_ITEMS.add(RAY_EGG);
        }
        if (enabled("blobfish")) {
            BLOBFISH_DNA = FossilsCompatUtil.registerItem("naturalist/blobfish_dna", new Item(new Item.Properties()));
            BLOBFISH_EGG = registerCreatureEgg("naturalist/blobfish_egg", () -> NaturalistEntityTypes.BLOBFISH.get());
            TAB_ITEMS.add(BLOBFISH_DNA);
            TAB_ITEMS.add(BLOBFISH_EGG);
        }
        if (enabled("piranha")) {
            PIRANHA_DNA = FossilsCompatUtil.registerItem("naturalist/piranha_dna", new Item(new Item.Properties()));
            PIRANHA_EGG = registerCreatureEgg("naturalist/piranha_egg", () -> NaturalistEntityTypes.PIRANHA.get());
            TAB_ITEMS.add(PIRANHA_DNA);
            TAB_ITEMS.add(PIRANHA_EGG);
        }
        if (enabled("starfish")) {
            STARFISH_DNA = FossilsCompatUtil.registerItem("naturalist/starfish_dna", new Item(new Item.Properties()));
            STARFISH_EGG = registerCreatureEgg("naturalist/starfish_egg", () -> NaturalistEntityTypes.STARFISH.get());
            TAB_ITEMS.add(STARFISH_DNA);
            TAB_ITEMS.add(STARFISH_EGG);
        }
        if (enabled("great_white_shark")) {
            GREAT_WHITE_SHARK_DNA = FossilsCompatUtil.registerItem("naturalist/great_white_shark_dna", new Item(new Item.Properties()));
            GREAT_WHITE_SHARK_EGG = registerCreatureEgg("naturalist/great_white_shark_egg", () -> NaturalistEntityTypes.GREAT_WHITE_SHARK.get());
            TAB_ITEMS.add(GREAT_WHITE_SHARK_DNA);
            TAB_ITEMS.add(GREAT_WHITE_SHARK_EGG);
        }
        if (enabled("jungle_scorpion")) {
            JUNGLE_SCORPION_DNA = FossilsCompatUtil.registerItem("naturalist/jungle_scorpion_dna", new Item(new Item.Properties()));
            JUNGLE_SCORPION_EGG = registerCreatureEgg("naturalist/jungle_scorpion_egg", () -> NaturalistEntityTypes.JUNGLE_SCORPION.get());
            TAB_ITEMS.add(JUNGLE_SCORPION_DNA);
            TAB_ITEMS.add(JUNGLE_SCORPION_EGG);
        }
        if (enabled("crab")) {
            CRAB_DNA = FossilsCompatUtil.registerItem("naturalist/crab_dna", new Item(new Item.Properties()));
            CRAB_EGG = registerCreatureEgg("naturalist/crab_egg", () -> NaturalistEntityTypes.CRAB.get(), true, null);
            TAB_ITEMS.add(CRAB_DNA);
            TAB_ITEMS.add(CRAB_EGG);
        }
        if (enabled("giant_isopod")) {
            GIANT_ISOPOD_DNA = FossilsCompatUtil.registerItem("naturalist/giant_isopod_dna", new Item(new Item.Properties()));
            GIANT_ISOPOD_EGG = registerCreatureEgg("naturalist/giant_isopod_egg", () -> NaturalistEntityTypes.GIANT_ISOPOD.get(), true, null);
            TAB_ITEMS.add(GIANT_ISOPOD_DNA);
            TAB_ITEMS.add(GIANT_ISOPOD_EGG);
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
        if (enabled("desert_scorpion")) {
            DESERT_SCORPION_DNA = FossilsCompatUtil.registerItem("naturalist/desert_scorpion_dna", new Item(new Item.Properties()));
            TAB_ITEMS.add(DESERT_SCORPION_DNA);
        }
        if (enabled("ant")) {
            ANT_DNA = FossilsCompatUtil.registerItem("naturalist/ant_dna", new Item(new Item.Properties()));
            TAB_ITEMS.add(ANT_DNA);
        }
        if (enabled("ostrich")) {
            OSTRICH_DNA = FossilsCompatUtil.registerItem("naturalist/ostrich_dna", new Item(new Item.Properties()));
            TAB_ITEMS.add(OSTRICH_DNA);
        }

        // Reptiles — placeable egg block, hatches into a baby after a while
        if (enabled("coral_snake")) {
            CORAL_SNAKE_DNA = FossilsCompatUtil.registerItem("naturalist/coral_snake_dna", new Item(new Item.Properties()));
            CORAL_SNAKE_EGG = ReptileEggBlocks.registerSmall("naturalist/coral_snake_egg", () -> NaturalistEntityTypes.SNAKE.get(), true, null, mob -> applyVariant(mob, "coral_snake"));
            TAB_ITEMS.add(CORAL_SNAKE_DNA);
            TAB_ITEMS.add(CORAL_SNAKE_EGG);
        }
        if (enabled("snake")) {
            SNAKE_DNA = FossilsCompatUtil.registerItem("naturalist/snake_dna", new Item(new Item.Properties()));
            SNAKE_EGG = ReptileEggBlocks.registerSmall("naturalist/snake_egg", () -> NaturalistEntityTypes.SNAKE.get(), true, null, mob -> applyVariant(mob, "snake"));
            TAB_ITEMS.add(SNAKE_DNA);
            TAB_ITEMS.add(SNAKE_EGG);
        }
        if (enabled("rattlesnake")) {
            RATTLESNAKE_DNA = FossilsCompatUtil.registerItem("naturalist/rattlesnake_dna", new Item(new Item.Properties()));
            RATTLESNAKE_EGG = ReptileEggBlocks.registerSmall("naturalist/rattlesnake_egg", () -> NaturalistEntityTypes.SNAKE.get(), true, BlockTags.SAND, mob -> applyVariant(mob, "rattlesnake"));
            TAB_ITEMS.add(RATTLESNAKE_DNA);
            TAB_ITEMS.add(RATTLESNAKE_EGG);
        }
        if (enabled("lizard")) {
            LIZARD_DNA = FossilsCompatUtil.registerItem("naturalist/lizard_dna", new Item(new Item.Properties()));
            LIZARD_EGG = ReptileEggBlocks.registerSmall("naturalist/lizard_egg", () -> NaturalistEntityTypes.LIZARD.get(), true);
            TAB_ITEMS.add(LIZARD_DNA);
            TAB_ITEMS.add(LIZARD_EGG);
        }
        // Same egg as Alex's Mobs' komodo dragon (medium tier, shared block models/textures)
        if (enabled("komodo_dragon")) {
            KOMODO_DRAGON_DNA = FossilsCompatUtil.registerItem("naturalist/komodo_dragon_dna", new Item(new Item.Properties()));
            KOMODO_DRAGON_EGG = ReptileEggBlocks.registerMedium("naturalist/komodo_dragon_egg", () -> NaturalistEntityTypes.KOMODO_DRAGON.get(), true);
            TAB_ITEMS.add(KOMODO_DRAGON_DNA);
            TAB_ITEMS.add(KOMODO_DRAGON_EGG);
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
        if (enabled("black_bear")) {
            BLACK_BEAR_DNA = FossilsCompatUtil.registerItem("naturalist/black_bear_dna", new Item(new Item.Properties()));
            BLACK_BEAR_EMBRYO = registerMammalEmbryo("naturalist/black_bear_embryo", "black_bear", () -> NaturalistEntityTypes.BLACK_BEAR.get(), BLACK_BEAR_DNA);
            TAB_ITEMS.add(BLACK_BEAR_DNA);
            TAB_ITEMS.add(BLACK_BEAR_EMBRYO);
        }
        if (enabled("capybara")) {
            CAPYBARA_DNA = FossilsCompatUtil.registerItem("naturalist/capybara_dna", new Item(new Item.Properties()));
            CAPYBARA_EMBRYO = registerMammalEmbryo("naturalist/capybara_embryo", "capybara", () -> NaturalistEntityTypes.CAPYBARA.get(), CAPYBARA_DNA);
            TAB_ITEMS.add(CAPYBARA_DNA);
            TAB_ITEMS.add(CAPYBARA_EMBRYO);
        }
        if (enabled("hedgehog")) {
            HEDGEHOG_DNA = FossilsCompatUtil.registerItem("naturalist/hedgehog_dna", new Item(new Item.Properties()));
            HEDGEHOG_EMBRYO = registerMammalEmbryo("naturalist/hedgehog_embryo", "hedgehog", () -> NaturalistEntityTypes.HEDGEHOG.get(), HEDGEHOG_DNA);
            TAB_ITEMS.add(HEDGEHOG_DNA);
            TAB_ITEMS.add(HEDGEHOG_EMBRYO);
        }
        if (enabled("mammoth")) {
            MAMMOTH_DNA = FossilsCompatUtil.registerItem("naturalist/mammoth_dna", new Item(new Item.Properties()));
            MAMMOTH_EMBRYO = registerMammalEmbryo("naturalist/mammoth_embryo", "mammoth", () -> NaturalistEntityTypes.MAMMOTH.get(), MAMMOTH_DNA);
            TAB_ITEMS.add(MAMMOTH_DNA);
            TAB_ITEMS.add(MAMMOTH_EMBRYO);
        }
        if (enabled("mole")) {
            MOLE_DNA = FossilsCompatUtil.registerItem("naturalist/mole_dna", new Item(new Item.Properties()));
            MOLE_EMBRYO = registerMammalEmbryo("naturalist/mole_embryo", "mole", () -> NaturalistEntityTypes.MOLE.get(), MOLE_DNA);
            TAB_ITEMS.add(MOLE_DNA);
            TAB_ITEMS.add(MOLE_EMBRYO);
        }
        if (enabled("rat")) {
            RAT_DNA = FossilsCompatUtil.registerItem("naturalist/rat_dna", new Item(new Item.Properties()));
            RAT_EMBRYO = registerMammalEmbryo("naturalist/rat_embryo", "rat", () -> NaturalistEntityTypes.RAT.get(), RAT_DNA);
            TAB_ITEMS.add(RAT_DNA);
            TAB_ITEMS.add(RAT_EMBRYO);
        }
        if (enabled("tiger")) {
            TIGER_DNA = FossilsCompatUtil.registerItem("naturalist/tiger_dna", new Item(new Item.Properties()));
            TIGER_EMBRYO = registerMammalEmbryo("naturalist/tiger_embryo", "tiger", () -> NaturalistEntityTypes.TIGER.get(), TIGER_DNA,
                Component.translatable("entity.naturalist.tiger"), mob -> rollVariant(mob, "naturalist:tiger", "naturalist:white_tiger", 20));
            TAB_ITEMS.add(TIGER_DNA);
            TAB_ITEMS.add(TIGER_EMBRYO);
        }
        if (enabled("leopard")) {
            LEOPARD_DNA = FossilsCompatUtil.registerItem("naturalist/leopard_dna", new Item(new Item.Properties()));
            LEOPARD_EMBRYO = registerMammalEmbryo("naturalist/leopard_embryo", "leopard", () -> NaturalistEntityTypes.TIGER.get(), LEOPARD_DNA,
                Component.translatable("entity.fossils_compat.naturalist.leopard"), mob -> rollVariant(mob, "naturalist:leopard", "naturalist:black_panther", 4));
            TAB_ITEMS.add(LEOPARD_DNA);
            TAB_ITEMS.add(LEOPARD_EMBRYO);
        }
        if (enabled("whale")) {
            WHALE_DNA = FossilsCompatUtil.registerItem("naturalist/whale_dna", new Item(new Item.Properties()));
            WHALE_EMBRYO = registerMammalEmbryo("naturalist/whale_embryo", "whale", () -> NaturalistEntityTypes.WHALE.get(), WHALE_DNA);
            TAB_ITEMS.add(WHALE_DNA);
            TAB_ITEMS.add(WHALE_EMBRYO);
        }

        ItemGroupEvents.modifyEntriesEvent(FossilsCompatUtil.FOSSIL_MOB_TAB).register(entries -> TAB_ITEMS.forEach(entries::accept));
    }

    private static Item registerCreatureEgg(String id, Supplier<EntityType<?>> supplier) {
        return registerCreatureEgg(id, supplier, null);
    }

    private static Item registerCreatureEgg(String id, Supplier<EntityType<?>> supplier, Consumer<Mob> onSpawn) {
        return registerCreatureEgg(id, supplier, false, onSpawn);
    }

    private static Item registerCreatureEgg(String id, Supplier<EntityType<?>> supplier, boolean babyOnHatch, Consumer<Mob> onSpawn) {
        return FossilsCompatUtil.registerItem(id, new SpawnEggLikeItem(supplier, babyOnHatch, onSpawn, new Item.Properties().stacksTo(16)));
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
        return registerMammalEmbryo(id, key, entityType, dnaItem, Component.translatable("entity.naturalist." + key), null);
    }

    private static Item registerMammalEmbryo(String id, String key, Supplier<EntityType<?>> entityType, Item dnaItem,
                                             Component displayName, Consumer<Mob> onBirth) {
        return FossilsCompatUtil.registerItem(id, new MammalEmbryoItem(new SimpleEntityInfo(
            "NATURALIST_" + key.toUpperCase(Locale.ROOT), entityType, PrehistoricMobType.MAMMAL,
            () -> displayName, dnaItem, onBirth)));
    }
}
