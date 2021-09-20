package com.airplanegaming.michaelcraft.mixin;

import com.airplanegaming.michaelcraft.MiChaelCraft;
import com.airplanegaming.michaelcraft.ModRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin extends LivingEntity {

    // Pretty sure this doesn't do anything, just makes the compiler happy
    private VillagerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    // This is overriding the method which is probably bad
    public boolean damage(DamageSource source, float amount) {
        if (this.world.isClient) return false;
        Entity attacker = source.getAttacker();
        if (attacker != null && attacker.isPlayer() && this.isBaby() && MiChaelCraft.RANDOM.nextInt(100) == 0) {
            MiChaelCraft.log(Level.INFO, "Baby Villager dropped Pokimane");
            this.dropItem(ModRegistry.POKIMAINE);
        }
        return super.damage(source, amount);
    }

}
