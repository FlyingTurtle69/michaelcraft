package com.airplanegaming.michaelcraft.mixin;

import com.airplanegaming.michaelcraft.ModRegistry;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {
    @Inject(method = "addPlainsMobs", at = @At("HEAD"), cancellable = true)
    private static void addPlainsMobs(SpawnSettings.Builder builder, CallbackInfo info) {
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModRegistry.TOBY, 100, 1, 1));
    }
}
