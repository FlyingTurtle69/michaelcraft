package com.airplanegaming.michaelcraft.client;

import com.airplanegaming.michaelcraft.MiChaelCraftClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

// Mostly copied from  net.minecraft.client.render.entity.ChickenEntityRenderer
public class GiantChickenRenderer extends MobEntityRenderer<ChickenEntity, ChickenEntityModel<ChickenEntity>> {

    private static final Identifier TEXTURE = new Identifier("textures/entity/chicken.png");

    public GiantChickenRenderer(EntityRendererFactory.Context context) {
        super(context, new ChickenEntityModel<>(context.getPart(MiChaelCraftClient.MODEL_GIANT_CHICKEN_LAYER)), 5f);
    }

    public Identifier getTexture(ChickenEntity chickenEntity) {
        return TEXTURE;
    }

    protected float getAnimationProgress(ChickenEntity chickenEntity, float f) {
        float g = MathHelper.lerp(f, chickenEntity.prevFlapProgress, chickenEntity.flapProgress);
        float h = MathHelper.lerp(f, chickenEntity.prevMaxWingDeviation, chickenEntity.maxWingDeviation);
        return (MathHelper.sin(g) + 1.0F) * h;
    }
}
