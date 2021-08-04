package com.airplanegaming.michaelcraft.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.world.World;
import net.minecraft.entity.attribute.DefaultAttributeContainer.Builder;

@SuppressWarnings("EntityConstructor")
public class GiantChicken extends ChickenEntity {

    public GiantChicken(EntityType<? extends ChickenEntity> entityType, World world) {
        super(entityType, world);
    }

    public static Builder createAttributes() {
        return ChickenEntity.createChickenAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 300);
    }

    public static EntityType<GiantChicken> createEntityType() {
        return FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GiantChicken::new)
                .dimensions(EntityDimensions.fixed(7.2f, 10.5f)).build();
    }

}
