package com.airplanegaming.michaelcraft.effect;

import com.airplanegaming.michaelcraft.mixin.LivingEntityAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;

public class SimpEffect extends StatusEffect {
    public SimpEffect() {
        super(StatusEffectType.HARMFUL, 0x7F00FF);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 5 == 0;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {
            ((PlayerEntity) entity).getInventory().dropAll();
        } else {
            ((LivingEntityAccessor) entity).invokeDropEquipment(DamageSource.GENERIC, 1, true);
        }
    }
}
