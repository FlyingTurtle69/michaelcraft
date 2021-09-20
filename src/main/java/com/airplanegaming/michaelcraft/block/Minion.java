package com.airplanegaming.michaelcraft.block;

import com.airplanegaming.michaelcraft.MiChaelCraft;
import com.airplanegaming.michaelcraft.MichaelEvent;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import scheduler.Scheduleable;
import scheduler.Scheduler;

// This is not actually obtainable it's just to make the scheduler work
public class Minion extends Block implements Scheduleable{

    private static final int ticks = 20 * 60 * 60; // every hour

    public Minion() {
        super(FabricBlockSettings.of(Material.SPONGE).strength(4f));
    }

    @Override
    public void onScheduleEnd(World world, BlockPos pos, int scheduleId, NbtCompound additionalData) {
        // 1/3 chance of michael event
        if (MiChaelCraft.RANDOM.nextInt(2) == 0) MichaelEvent.executeRandom();
        scheduleMichaelEvent(world);
    }

    public void scheduleMichaelEvent(World world) {
        Scheduler.Builder(this, world).schedule(ticks);
    }

}
