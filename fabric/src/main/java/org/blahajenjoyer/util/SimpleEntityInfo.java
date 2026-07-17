package org.blahajenjoyer.util;

import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.EntityInfo;
import com.github.teamfossilsarcheology.fossil.entity.prehistoric.base.PrehistoricMobType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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

    public SimpleEntityInfo(String name, Supplier<EntityType<?>> entityType, PrehistoricMobType mobType,
                             Supplier<Component> displayName, Item dnaItem) {
        this.name = name;
        this.entityType = entityType;
        this.mobType = mobType;
        this.displayName = displayName;
        this.dnaItem = dnaItem;
        BY_NAME.put(name, this);
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
