package com.airplanegaming.michaelcraft.mixin;

import com.airplanegaming.michaelcraft.MiChaelCraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.UUID;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends Entity {

    @Shadow public abstract void sendSystemMessage(Text message, UUID sender);

    // Does nothing but necessary to use super methods
    public ServerPlayerEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onDeath", at = @At("TAIL"), cancellable = true)
    private void onDeath(DamageSource source, CallbackInfo ci) {
        String message = " died at " + this.getBlockPos().toString();
        MiChaelCraft.log(Level.INFO, this.getDisplayName().asString() + message);
        this.sendSystemMessage(Text.of("You" + message), Util.NIL_UUID);
    }

}
