package org.blahajenjoyer.compat.vanillabackport.entity;

import com.blackgear.vanillabackport.common.api.variant.VariantDataHolder;
import com.blackgear.vanillabackport.common.level.entities.animal.ChickenVariant;
import com.blackgear.vanillabackport.common.level.entities.animal.ChickenVariants;
import net.minecraft.resources.ResourceKey;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.blahajenjoyer.compat.vanillabackport.VanillaBackportCompat;

public class ThrownVariantChickenEgg extends ThrowableItemProjectile {

    private static final EntityDataAccessor<String> DATA_VARIANT =
        SynchedEntityData.defineId(ThrownVariantChickenEgg.class, EntityDataSerializers.STRING);

    public ThrownVariantChickenEgg(EntityType<? extends ThrownVariantChickenEgg> type, Level level) {
        super(type, level);
    }

    public ThrownVariantChickenEgg(Level level, LivingEntity thrower, String variantKey) {
        super(VanillaBackportCompat.THROWN_VARIANT_CHICKEN_EGG, thrower, level);
        this.entityData.set(DATA_VARIANT, variantKey);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT, "cold");
    }

    @Override
    protected Item getDefaultItem() {
        String variant = this.entityData.get(DATA_VARIANT);
        return "warm".equals(variant) ? VanillaBackportCompat.WARM_CHICKEN_EGG : VanillaBackportCompat.COLD_CHICKEN_EGG;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        result.getEntity().hurt(damageSources().thrown(this, getOwner()), 0.0F);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!level().isClientSide) {
            Chicken chicken = EntityType.CHICKEN.create(level());
            if (chicken != null) {
                setVariant(chicken);
                chicken.setAge(-24000);
                chicken.moveTo(getX(), getY(), getZ(), getYRot(), 0);
                level().addFreshEntity(chicken);
            }
            level().broadcastEntityEvent(this, (byte) 3);
            discard();
        }
    }

    @SuppressWarnings("unchecked")
    private void setVariant(Chicken chicken) {
        if (!(chicken instanceof VariantDataHolder<?>)) return;
        VariantDataHolder<ChickenVariant> holder = (VariantDataHolder<ChickenVariant>) chicken;
        ResourceKey<ChickenVariant> key = "warm".equals(entityData.get(DATA_VARIANT))
            ? ChickenVariants.WARM : ChickenVariants.COLD;
        ChickenVariant variant = ChickenVariants.REGISTRY.get(key);
        if (variant != null) {
            holder.setVariantData(variant);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("Variant", this.entityData.get(DATA_VARIANT));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("Variant")) {
            this.entityData.set(DATA_VARIANT, tag.getString("Variant"));
        }
    }
}
