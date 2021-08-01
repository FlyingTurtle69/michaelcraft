package com.airplanegaming.michaelcraft;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Random;

public class MiChaelCraft implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();
    public static Random RANDOM = new Random();

    public static final String MOD_ID = "michael";
    public static final String MOD_NAME = "michaelcraft";

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "general"),
            () -> new ItemStack(ModRegistry.POKIMAINE)
    );

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        ModRegistry.registerThings();
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

    public static Item.Settings itemSettings() {
        return new Item.Settings().group(ITEM_GROUP);
    }

}