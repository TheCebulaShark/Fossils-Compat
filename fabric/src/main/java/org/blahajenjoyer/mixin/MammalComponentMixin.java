package org.blahajenjoyer.mixin;

import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.EntityInfo;
import com.github.teamfossilsarcheology.fossil.fabric.capabilities.MammalComponent;
import net.minecraft.nbt.CompoundTag;
import org.blahajenjoyer.util.SimpleEntityInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// EntityInfo.fromNbt only knows FAR's own two enums, so MammalComponent.readFromNbt silently
// drops the embryo (sets it to null) whenever it was a SimpleEntityInfo — breaking both the
// client sync of an in-progress compat-mod pregnancy (Dinopedia can't detect it) and world
// reload persistence. Falls back to SimpleEntityInfo's own registry when FAR's lookup missed.
@Mixin(value = MammalComponent.class, remap = false)
public abstract class MammalComponentMixin {

    @Shadow
    public abstract EntityInfo getEmbryo();

    @Shadow
    public abstract void setEmbryo(EntityInfo embryo);

    @Inject(method = "readFromNbt", at = @At("TAIL"))
    private void fossilsCompat$resolveCustomEmbryo(CompoundTag tag, CallbackInfo ci) {
        if (this.getEmbryo() == null && tag.contains("embryo")) {
            EntityInfo custom = SimpleEntityInfo.byName(tag.getString("embryo"));
            if (custom != null) {
                this.setEmbryo(custom);
            }
        }
    }
}
