package org.blahajenjoyer;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.blahajenjoyer.compat.alexsmobs.AlexsMobsCompat;
import org.blahajenjoyer.compat.crittersandcompanions.CrittersAndCompanionsCompat;
import org.blahajenjoyer.compat.faunify.FaunifyCompat;
import org.blahajenjoyer.compat.naturalist.NaturalistCompat;
import org.blahajenjoyer.compat.vanillabackport.VanillaBackportCompat;
import org.blahajenjoyer.config.FossilsCompatConfig;

import java.util.Map;

public class fossils_compat implements ModInitializer {

    @Override
    public void onInitialize() {
        FossilsCompatConfig.init(Map.of(
            VanillaBackportCompat.MOD_ID, VanillaBackportCompat.ANIMAL_KEYS,
            AlexsMobsCompat.MOD_ID, AlexsMobsCompat.ANIMAL_KEYS,
            NaturalistCompat.MOD_ID, NaturalistCompat.ANIMAL_KEYS,
            CrittersAndCompanionsCompat.MOD_ID, CrittersAndCompanionsCompat.ANIMAL_KEYS,
            FaunifyCompat.MOD_ID, FaunifyCompat.ANIMAL_KEYS
        ));

        if (FabricLoader.getInstance().isModLoaded("vanillabackport")) {
            VanillaBackportCompat.register();
        }
        if (FabricLoader.getInstance().isModLoaded("alexsmobs")) {
            AlexsMobsCompat.register();
        }
        if (FabricLoader.getInstance().isModLoaded("naturalist")) {
            NaturalistCompat.register();
        }
        if (FabricLoader.getInstance().isModLoaded("crittersandcompanions")) {
            CrittersAndCompanionsCompat.register();
        }
        if (FabricLoader.getInstance().isModLoaded("faunify")) {
            FaunifyCompat.register();
        }
    }
}
