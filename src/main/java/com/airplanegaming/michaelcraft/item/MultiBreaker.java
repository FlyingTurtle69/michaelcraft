package com.airplanegaming.michaelcraft.item;

import com.airplanegaming.michaelcraft.MiChaelCraft;
import draylar.magna.api.BlockProcessor;
import draylar.magna.api.MagnaTool;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.List;

// Tool that can break any block and multiple blocks at once
public class MultiBreaker extends MiningToolItem implements MagnaTool {

    private final int breakRadius;

    public MultiBreaker(int radius, int durability, int miningLevel) {
        super(1f, -3.3f, new ToolMaterial() {
            @Override
            public int getDurability() {
                return durability;
            }
            @Override
            public float getMiningSpeedMultiplier() {
                // I wasn't able to get this to actually make it slow
                return 0.01f;
            }
            @Override
            public float getAttackDamage() {
                return 2.0f;
            }
            @Override
            public int getMiningLevel() {
                return miningLevel;
            }
            @Override
            public int getEnchantability() {
                return 12;
            }
            @Override
            public Ingredient getRepairIngredient() {
                return null;
            }
        }, new Tag<>() {
            @Override
            public boolean contains(Block entry) {
                return true;
            }
            @Override
            public List<Block> values() {
                return null;
            }
        }, MiChaelCraft.itemSettings());
        this.breakRadius = radius;
    }

    @Override
    public int getRadius(ItemStack stack) {
        return breakRadius;
    }

    @Override
    public boolean playBreakEffects() {
        return false;
    }

    // If the block is a building block, don't drop it
    @Override
    public BlockProcessor getProcessor(World world, PlayerEntity player, BlockPos pos, ItemStack heldStack) {
        return (tool, input) -> {
            if (input.getItem().getGroup() == ItemGroup.BUILDING_BLOCKS) input.setCount(0);
            return input;
        };
    }

}
