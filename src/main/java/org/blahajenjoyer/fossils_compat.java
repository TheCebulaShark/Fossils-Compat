package org.blahajenjoyer;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.blahajenjoyer.compat.alexsmobs.AlexsMobsCompat;
import org.blahajenjoyer.compat.crittersandcompanions.CrittersAndCompanionsCompat;
import org.blahajenjoyer.compat.naturalist.NaturalistCompat;
import org.blahajenjoyer.compat.vanillabackport.VanillaBackportCompat;

public class fossils_compat implements ModInitializer {

    @Override
    public void onInitialize() {
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
    }
}
