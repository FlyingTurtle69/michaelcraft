package com.airplanegaming.michaelcraft.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.AttackGoal;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.attribute.DefaultAttributeContainer.Builder;

public class Michael extends PathAwareEntity {

    public Michael(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(4, new AttackGoal(this));
        this.targetSelector.add(0, new FollowTargetGoal<>(this, PlayerEntity.class, false));
    }

    public static Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 100.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 3.5)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0);
    }

}
