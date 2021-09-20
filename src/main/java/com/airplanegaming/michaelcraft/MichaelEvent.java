package com.airplanegaming.michaelcraft;

import com.airplanegaming.michaelcraft.entity.Michael;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import scheduler.Scheduleable;
import scheduler.Scheduler;

import java.util.List;
import java.util.function.Function;

public enum MichaelEvent {

    BOOM(player -> {
        player.getEntityWorld().createExplosion(null, player.getX(), player.getY(), player.getZ(), 6.9f, Explosion.DestructionType.NONE);
        return null;
    }),
    RUN(player -> {
        var world = player.getEntityWorld();
        Michael michael = ((EntityType<Michael>) EntityType.get("michael:michael").get()).create(world);
        assert michael != null;
        michael.updatePosition(player.getX() - 16, player.getY(), player.getZ());
        world.spawnEntity(michael);
        Scheduler.Builder(new ScheduleBlock() {
            @Override
            public void onScheduleEnd(World world2, BlockPos pos, int scheduleId, NbtCompound additionalData) {
                michael.kill();
            }
        }, world).schedule(80);
        return null;
    }),
    FOOT(player -> {
        player.giveItemStack(new ItemStack(ModRegistry.FOOT, 1));
        return null;
    });

    // Is set when the server starts
    public static MinecraftServer server;
    // So they can be executed within this file
    private final Function<ServerPlayerEntity, Void> function;

    MichaelEvent(Function<ServerPlayerEntity, Void> function) {
        this.function = function;
    }

    public void execute(ServerPlayerEntity player) {
        MiChaelCraft.log(this.name() + " Michael Event");
        function.apply(player);
    }

    public static void executeRandom() {
        randomArrayElement(values()).execute(randomListElement(getAllPlayers()));
    }

    private static <T> T randomArrayElement(T[] array){
        return array[MiChaelCraft.RANDOM.nextInt(array.length)];
    }

    private static <T> T randomListElement(List<T> list) {
        return list.get(MiChaelCraft.RANDOM.nextInt(list.size()));
    }

    private static List<ServerPlayerEntity> getAllPlayers() {
        return server.getPlayerManager().getPlayerList();
    }

}

abstract class ScheduleBlock extends Block implements Scheduleable {

    public ScheduleBlock() {
        super(FabricBlockSettings.of(Material.AIR));
    }

}