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
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;

// I might need this idk
@Environment(EnvType.CLIENT)
public class MiChaelCraftClient implements ClientModInitializer {

    public static final EntityModelLayer MODEL_MILLAGER_LAYER = new EntityModelLayer(new Identifier(MiChaelCraft.MOD_ID, "millager"), "main");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(ModRegistry.MILLAGER, VillagerEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_MILLAGER_LAYER, () -> TexturedModelData.of(VillagerResemblingModel.getModelData(), 64, 64));

        createJpegModel("toby", ModRegistry.TOBY);
        createModel("kai", ModRegistry.KAI,0.1f, -0.5f, 23f, -0.5f, 1f, 1f, 1f, 1, 1);
    }

    private static <T extends MobEntity> void createModel(String name, EntityType<T> entity, float shadowSize, float offsetX, float offsetY, float offsetZ, float sizeX, float sizeY, float sizeZ, int textureW, int textureH) {
        EntityModelLayer layer = new EntityModelLayer(new Identifier(MiChaelCraft.MOD_ID, name), "main");
        var renderer = new RendererFactory(layer, name, shadowSize);
        EntityRendererRegistry.INSTANCE.register(entity, renderer::create);
        EntityModelLayerRegistry.registerModelLayer(layer, () -> getTexturedModelData(offsetX, offsetY, offsetZ, sizeX, sizeY, sizeZ, textureW, textureH));
    }

    private static <T extends MobEntity> void createJpegModel(String name, EntityType<T> entity) {
        createModel(name, entity, 4.3f, -34.5f, -45f, 0f, 69f, 69f, 0.5f, 69, 69);
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
