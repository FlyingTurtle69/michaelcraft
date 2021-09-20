package com.airplanegaming.michaelcraft;

import com.airplanegaming.michaelcraft.block.Minion;
import com.airplanegaming.michaelcraft.item.EmergencyMeeting;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
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

    // Used for networking for EmergencyMeeting
    public static Identifier SUS_PACKET_ID = new Identifier(MOD_ID, "sus");
    public static Identifier SUS_PACKET_ID2 = new Identifier(MOD_ID, "sus2");


    // Sorry for the shitty name lol
    public static EmergencyMeeting.SusPart2 susPart2 = null;

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        ModRegistry.registerThings();

        // Register events

        // Start Michael Events
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            MichaelEvent.server = server;
            new Minion().scheduleMichaelEvent(server.getOverworld());
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            if (susPart2 == null) return;
            // Alarm sound has finished
            if (susPart2.ticks == 60) {
                susPart2.teleportPlayers();
                susPart2 = null;
            } else susPart2.ticks++;
        });

        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            // 5% chance of michael spawning
            if (newPlayer.getRandom().nextInt(20) == 0) MichaelEvent.RUN.execute(newPlayer);
        });
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

    public static void log(String message){
        LOGGER.log(Level.INFO, "["+MOD_NAME+"] " + message);
    }

    public static Item.Settings itemSettings() {
        return new Item.Settings().group(ITEM_GROUP);
    }

}