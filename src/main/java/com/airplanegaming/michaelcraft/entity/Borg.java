package com.airplanegaming.michaelcraft.entity;

import com.airplanegaming.michaelcraft.MiChaelCraft;
import com.airplanegaming.michaelcraft.ModRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.Util;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.Nullable;

public class Borg extends CowEntity {

    public Borg(EntityType<? extends CowEntity> entityType, World world) {
        super(entityType, world);
    }

    private final short SONG_LENGTH = 20 * 125; // song is 2:05 -> 125 seconds; 20 ticks in a second
    private short singingTicks = SONG_LENGTH;

    @Override
    public void baseTick() {
        if (!this.world.isClient()) {
            Vec3d pos = this.getPos();
            var box = new Box(pos.add(10, 10, 10), pos.subtract(10, 10, 10));
            var tobys = this.world.getEntitiesByType(TypeFilter.instanceOf(Toby.class), box, toby -> true);
            if (!tobys.isEmpty()) {
                if (singingTicks == SONG_LENGTH) {
                    MiChaelCraft.log(Level.INFO, "DAYLIGHT COME AND ME WAN GO HOME");
                    this.playSound(ModRegistry.BANANA_SOUND, 1f, 1f);
                    singingTicks = 0;
                } else {
                    singingTicks++;
                }

            }
        }
        super.baseTick();
    }

    @Nullable
    @Override
    public CowEntity createChild(ServerWorld world, PassiveEntity entity) {
        if (!world.isClient()) {
            var player = world.getClosestPlayer(entity, 10);
            if (player != null) player.sendSystemMessage(new LiteralText("I play league"), Util.NIL_UUID);
        }
        return null;
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 2.5f;
    }

}
