package com.airplanegaming.michaelcraft;

import com.airplanegaming.michaelcraft.client.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.VillagerEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;

// I might need this idk
@Environment(EnvType.CLIENT)
public class MiChaelCraftClient implements ClientModInitializer {

    public static final EntityModelLayer MODEL_GIANT_CHICKEN_LAYER = new EntityModelLayer(new Identifier(MiChaelCraft.MOD_ID, "giant_chicken"), "main");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(ModRegistry.MILLAGER, VillagerEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(ModRegistry.GIANT_CHICKEN, GiantChickenRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_GIANT_CHICKEN_LAYER, () -> {
            // Copied from net.minecraft.client.render.entity.model.ChickenEntityModel except size is different
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            final int SCALE = 15;
            final int YTRANS = 337;
            modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F * SCALE, -6.0F * SCALE - YTRANS, -2.0F * SCALE, 4.0F * SCALE, 6.0F * SCALE, 3.0F * SCALE), ModelTransform.pivot(0.0F, 15.0F * SCALE, -4.0F * SCALE));
            modelPartData.addChild("beak", ModelPartBuilder.create().uv(14 * SCALE, 0).cuboid(-2.0F * SCALE, -4.0F * SCALE - YTRANS, -4.0F * SCALE, 4.0F * SCALE, 2.0F  * SCALE, 2.0F * SCALE), ModelTransform.pivot(0.0F, 15.0F * SCALE, -4.0F * SCALE));
            modelPartData.addChild("red_thing", ModelPartBuilder.create().uv(14 * SCALE, 4 * SCALE).cuboid(-1.0F * SCALE, -2.0F * SCALE - YTRANS, -3.0F * SCALE, 2.0F * SCALE, 2.0F * SCALE, 2.0F * SCALE), ModelTransform.pivot(0.0F, 15.0F * SCALE, -4.0F * SCALE));
            modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 9 * SCALE).cuboid(-3.0F * SCALE, -4.0F * SCALE, -3.0F * SCALE, 6.0F * SCALE, 8.0F * SCALE, 6.0F * SCALE), ModelTransform.of(0.0F, 16.0F * SCALE - YTRANS, 0.0F, 1.5707964F, 0.0F, 0.0F));
            ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(26 * SCALE, 0).cuboid(-1.0F * SCALE, 0.0F, -3.0F * SCALE, 3.0F * SCALE, 5.0F * SCALE, 3.0F * SCALE);
            modelPartData.addChild("right_leg", modelPartBuilder, ModelTransform.pivot(-2.0F * SCALE, 19.0F * SCALE - YTRANS, 1.0F * SCALE));
            modelPartData.addChild("left_leg", modelPartBuilder, ModelTransform.pivot(1.0F * SCALE, 19.0F * SCALE - YTRANS, 1.0F *  SCALE));
            modelPartData.addChild("right_wing", ModelPartBuilder.create().uv(24 * SCALE, 13 * SCALE).cuboid(0.0F, 0.0F, -3.0F * SCALE, SCALE, 4.0F * SCALE, 6.0F * SCALE), ModelTransform.pivot(-4.0F * SCALE, 13.0F * SCALE - YTRANS, 0.0F));
            modelPartData.addChild("left_wing", ModelPartBuilder.create().uv(24 * SCALE, 13 * SCALE).cuboid(-1.0F * SCALE, 0.0F, -3.0F * SCALE, SCALE, 4.0F * SCALE, 6.0F * SCALE), ModelTransform.pivot(4.0F * SCALE, 13.0F * SCALE - YTRANS, 0.0F));
            return TexturedModelData.of(modelData, 64 * SCALE, 32 * SCALE);
        });

        createJpegModel("toby", ModRegistry.TOBY);
        createJpegModel("borg", ModRegistry.BORG);
        createModel("kai", ModRegistry.KAI,0.069f, -0.5f, 23f, -0.5f, 1f, 1f, 1f, 1, 1);
    }

    private static <T extends MobEntity> void createModel(String name, EntityType<T> entity, float shadowSize, float offsetX, float offsetY, float offsetZ, float sizeX, float sizeY, float sizeZ, int textureW, int textureH) {
        EntityModelLayer layer = new EntityModelLayer(new Identifier(MiChaelCraft.MOD_ID, name), "main");
        var renderer = new RendererFactory(layer, name, shadowSize);
        EntityRendererRegistry.INSTANCE.register(entity, renderer::create);
        EntityModelLayerRegistry.registerModelLayer(layer, () -> getTexturedModelData(offsetX, offsetY, offsetZ, sizeX, sizeY, sizeZ, textureW, textureH));
    }

    private static <T extends MobEntity> void createJpegModel(String name, EntityType<T> entity) {
        createModel(name, entity, 3.2f, -34.5f, -45f, 0f, 69f, 69f, 0.5f, 69, 69);
    }

    private static TexturedModelData getTexturedModelData(float offsetX, float offsetY, float offsetZ, float sizeX, float sizeY, float sizeZ, int textureW, int textureH) {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(EntityModelPartNames.CUBE, ModelPartBuilder.create().uv(1, 0)
                .cuboid(offsetX, offsetY, offsetZ, sizeX, sizeY, sizeZ),
                ModelTransform.pivot(0f, 0f, 0f)
        );
        return TexturedModelData.of(modelData, textureW, textureH);
    }

}
