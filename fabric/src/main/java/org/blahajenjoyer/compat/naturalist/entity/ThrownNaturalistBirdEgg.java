package org.blahajenjoyer.compat.naturalist.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.blahajenjoyer.compat.naturalist.NaturalistCompat;

public class ThrownNaturalistBirdEgg extends ThrowableItemProjectile {

    private static final EntityDataAccessor<String> DATA_BIRD_KEY =
        SynchedEntityData.defineId(ThrownNaturalistBirdEgg.class, EntityDataSerializers.STRING);

    public ThrownNaturalistBirdEgg(EntityType<? extends ThrownNaturalistBirdEgg> type, Level level) {
        super(type, level);
    }

    public ThrownNaturalistBirdEgg(Level level, LivingEntity thrower, String birdKey) {
        super(NaturalistCompat.THROWN_NATURALIST_BIRD_EGG, thrower, level);
        this.entityData.set(DATA_BIRD_KEY, birdKey);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_BIRD_KEY, "blue_jay");
    }

    @Override
    protected Item getDefaultItem() {
        return NaturalistCompat.getEggByKey(this.entityData.get(DATA_BIRD_KEY));
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
            String birdKey = this.entityData.get(DATA_BIRD_KEY);
            EntityType<?> type = NaturalistCompat.getEntityType(birdKey);
            if (type != null) {
                Entity entity = type.create(level());
                if (entity instanceof Mob mob && level() instanceof ServerLevel serverLevel) {
                    mob.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(blockPosition()),
                        MobSpawnType.SPAWN_EGG, null, null);
                    if (NaturalistCompat.isBabyOnHatch(birdKey) && mob instanceof AgeableMob ageable) {
                        ageable.setAge(-24000);
                    }
                    mob.moveTo(getX(), getY(), getZ(), getYRot(), 0);
                    level().addFreshEntity(mob);
                }
            }
            level().broadcastEntityEvent(this, (byte) 3);
            discard();
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("BirdKey", this.entityData.get(DATA_BIRD_KEY));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("BirdKey")) {
            this.entityData.set(DATA_BIRD_KEY, tag.getString("BirdKey"));
        }
    }
}
