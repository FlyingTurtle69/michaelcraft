package com.airplanegaming.michaelcraft.entity;

import com.airplanegaming.michaelcraft.ModRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

public class Toby extends CowEntity {

    public Toby(EntityType<? extends CowEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModRegistry.TOBY_TARKOV_SOUND;
    }

    @Nullable
    @Override
    public CowEntity createChild(ServerWorld world, PassiveEntity entity) {
        world.createExplosion(entity, entity.getX(), entity.getY(), entity.getZ(), 69f, Explosion.DestructionType.DESTROY);
        return null;
    }
}
