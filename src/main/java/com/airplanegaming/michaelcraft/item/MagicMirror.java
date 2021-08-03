package com.airplanegaming.michaelcraft.item;

import com.airplanegaming.michaelcraft.MiChaelCraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import java.util.Optional;

// A lot taken from https://github.com/tired9494/Magic-Mirror/blob/1.17/src/main/java/github/tired9494/magic_mirror/MagicMirrorItem.java

public class MagicMirror extends Item {

    public MagicMirror() {
        super(MiChaelCraft.itemSettings().maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world.isClient()) return stack;

        MiChaelCraft.log(Level.INFO, "Magic Mirror used");

        ServerPlayerEntity player = (ServerPlayerEntity)user;

        // 1/20 chance of going to end
        if (MiChaelCraft.RANDOM.nextInt(20) == 0) {
            player.moveToWorld(player.server.getWorld(World.END));
            return stack;
        }

        BlockPos spawnpoint = player.getSpawnPointPosition();
        ServerWorld spawnWorld = player.server.getWorld(player.getSpawnPointDimension());
        float spawnAngle = player.getSpawnAngle();

        if (spawnWorld == null) {
            MiChaelCraft.log(Level.INFO, "spawnworld null");
            return stack;
        }

        if (spawnpoint != null) {
            Optional<Vec3d> spawnVec = PlayerEntity.findRespawnPosition(spawnWorld, spawnpoint, spawnAngle, false, false);
            spawnVec.ifPresent(vec -> player.teleport(spawnWorld, vec.getX(), vec.getY(), vec.getZ(), spawnAngle, 0.5f));
        } else { // World Spawn
            spawnpoint = spawnWorld.getSpawnPos();
            player.teleport(spawnWorld, spawnpoint.getX(), spawnpoint.getY(), spawnpoint.getZ(), spawnAngle, 0.5f);
        }

        spawnWorld.playSound(null, spawnpoint, SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.NEUTRAL, 1f, MiChaelCraft.RANDOM.nextFloat()*.4f + .9f);
        player.getItemCooldownManager().set(this, 2000);

        return stack;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.EAT;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 69;
    }

}
