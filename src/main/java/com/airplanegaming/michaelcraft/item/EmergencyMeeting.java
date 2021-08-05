package com.airplanegaming.michaelcraft.item;

import com.airplanegaming.michaelcraft.MiChaelCraft;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

import java.util.List;

public class EmergencyMeeting extends Item {

    public EmergencyMeeting() {
        super(MiChaelCraft.itemSettings().maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world.isClient() || !(user instanceof PlayerEntity)) return stack;

        MiChaelCraft.log(Level.INFO, "DING DING DING DING DING DING DING");
        var players = (List<ServerPlayerEntity>) world.getPlayers();
        for (var player : players) ServerPlayNetworking.send(player, MiChaelCraft.SUS_PACKET_ID, PacketByteBufs.empty());

        MiChaelCraft.susPart2 = new SusPart2(players, user, (ServerWorld) world);
        stack.decrement(1);

        return stack;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 20;
    }

    // Used as a callback in MiChaelCraft.java
    public static class SusPart2 {
        private final List<ServerPlayerEntity> players;
        private final LivingEntity caller;
        private final ServerWorld world;
        public short ticks = 0;

        public SusPart2(List<ServerPlayerEntity> players, LivingEntity caller, ServerWorld world) {
            this.players = players;
            this.caller =  caller;
            this.world = world;
        }

        public void teleportPlayers() {
            for (var player : players) {
                if (player != caller) player.teleport(world, caller.getX(), caller.getY(), caller.getZ(), caller.getYaw(), 1f);
                ServerPlayNetworking.send(player, MiChaelCraft.SUS_PACKET_ID2, PacketByteBufs.empty());
            }
        }
    }

}
