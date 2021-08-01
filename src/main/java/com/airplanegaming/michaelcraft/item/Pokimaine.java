package com.airplanegaming.michaelcraft.item;

import com.airplanegaming.michaelcraft.MiChaelCraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Pokimaine extends Item {

    public Pokimaine() {
        super(MiChaelCraft.itemSettings().maxCount(1));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.takeKnockback(20, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 100));
        stack.decrement(1);
        return true;
    }

}
