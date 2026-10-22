package org.blahajenjoyer.mixin;

import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.EntityInfo;
import com.github.teamfossilsarcheology.fossil.event.ModEvents;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.blahajenjoyer.util.SimpleEntityInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// FAR's growEntity (mammal birth) only runs finalizeSpawn on the newborn, so variant mobs roll a
// biome-based variant. Lets a SimpleEntityInfo adjust the newborn (e.g. pick its variant) right
// before it's added to the world.
@Mixin(value = ModEvents.class, remap = false)
public abstract class ModEventsMixin {

    @WrapOperation(method = "growEntity",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z", remap = true))
    private static boolean fossilsCompat$onBirth(ServerLevel level, Entity newborn, Operation<Boolean> original,
                                                 EntityInfo embryo, LivingEntity parent) {
        if (embryo instanceof SimpleEntityInfo info && newborn instanceof Mob mob) {
            info.onBirth(mob);
        }
        return original.call(level, newborn);
    }
}
