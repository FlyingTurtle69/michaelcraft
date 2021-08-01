package com.airplanegaming.michaelcraft.entity;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.world.World;

// TODO: Can trade with cheemscoin like piglin bartering
public class Kai extends SilverfishEntity {

    public Kai(EntityType<? extends SilverfishEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.06f;
    }

}
