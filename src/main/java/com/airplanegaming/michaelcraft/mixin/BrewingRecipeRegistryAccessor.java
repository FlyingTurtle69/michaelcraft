package com.airplanegaming.michaelcraft.mixin;

// Copied from https://github.com/Nuclearfarts/more-potions/blob/master/src/main/java/com/nuclearfarts/morepotions/mixin/BrewingRecipeRegistryAccessor.java

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BrewingRecipeRegistry.class)
public interface BrewingRecipeRegistryAccessor {
    @Invoker
    public static void invokeRegisterPotionRecipe(Potion in, Item ingredient, Potion result) {}
}
