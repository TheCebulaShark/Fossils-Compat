package org.blahajenjoyer.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import org.blahajenjoyer.compat.alexsmobs.AlexsMobsCompat;
import org.blahajenjoyer.compat.vanillabackport.VanillaBackportCompat;

@Environment(EnvType.CLIENT)
public class fossils_compatClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        if (FabricLoader.getInstance().isModLoaded("vanillabackport")) {
            EntityRendererRegistry.register(VanillaBackportCompat.THROWN_VARIANT_CHICKEN_EGG, ThrownItemRenderer::new);
        }
        if (FabricLoader.getInstance().isModLoaded("alexsmobs")) {
            EntityRendererRegistry.register(AlexsMobsCompat.THROWN_ALEXS_BIRD_EGG, ThrownItemRenderer::new);
        }
    }
}
