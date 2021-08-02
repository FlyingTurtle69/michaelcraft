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
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModRegistry.TOBY, 1, 1, 1));
    }

    @Inject(method = "addMonsters", at = @At("HEAD"), cancellable = true)
    private static void addMonsters(SpawnSettings.Builder builder, int zombieWeight, int zombieVillagerWeight, int skeletonWeight, CallbackInfo info) {
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(ModRegistry.KAI, 4, 1, 2));
    }

    @Inject(method = "addFarmAnimals", at = @At("HEAD"), cancellable = true)
    private static void addFarmAnimals(SpawnSettings.Builder builder, CallbackInfo info) {
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModRegistry.MILLAGER, 1, 1, 3));
    }
}
