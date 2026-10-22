package org.blahajenjoyer.util;

import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.EntityInfo;
import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.PrehistoricMobType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

// EntityInfo.fromNbt only resolves FAR's own two enums, so every instance self-registers
// here — MammalComponentMixin falls back to this map when FAR's own lookup fails, letting
// mammal pregnancies referencing a SimpleEntityInfo survive syncing to client and world reloads.
public class SimpleEntityInfo implements EntityInfo {

    private static final Map<String, SimpleEntityInfo> BY_NAME = new ConcurrentHashMap<>();

    private final String name;
    private final Supplier<EntityType<?>> entityType;
    private final PrehistoricMobType mobType;
    private final Supplier<Component> displayName;
    private final Item dnaItem;
    private final Consumer<Mob> onBirth;

    public SimpleEntityInfo(String name, Supplier<EntityType<?>> entityType, PrehistoricMobType mobType,
                             Supplier<Component> displayName, Item dnaItem) {
        this(name, entityType, mobType, displayName, dnaItem, null);
    }

    /** @param onBirth applied to the newborn right before FAR adds it to the world (e.g. to force a variant), or {@code null} */
    public SimpleEntityInfo(String name, Supplier<EntityType<?>> entityType, PrehistoricMobType mobType,
                             Supplier<Component> displayName, Item dnaItem, Consumer<Mob> onBirth) {
        this.name = name;
        this.entityType = entityType;
        this.mobType = mobType;
        this.displayName = displayName;
        this.dnaItem = dnaItem;
        this.onBirth = onBirth;
        BY_NAME.put(name, this);
    }

    public void onBirth(Mob newborn) {
        if (onBirth != null) {
            onBirth.accept(newborn);
        }
    }

    public static EntityInfo byName(String name) {
        return BY_NAME.get(name);
    }

    @Override
    public EntityType<? extends Entity> entityType() {
        return entityType.get();
    }

    @Override
    public PrehistoricMobType mobType() {
        return mobType;
    }

    @Override
    public Item getDNAResult() {
        return null;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Supplier<Component> displayName() {
        return displayName;
    }

    @Override
    public Item dnaItem() {
        return dnaItem;
    }
}
