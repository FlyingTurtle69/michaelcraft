package com.airplanegaming.michaelcraft;

import com.airplanegaming.michaelcraft.client.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

// I might need this idk
//@Environment(EnvType.CLIENT)
public class MiChaelCraftClient implements ClientModInitializer {

    public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier(MiChaelCraft.MOD_ID, "cube"), "main");
    public static final EntityModelLayer MODEL_TOBY_LAYER = createJpegModel("toby");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(ModRegistry.CUBE, CubeEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_CUBE_LAYER, CubeEntityModel::getTexturedModelData);
        EntityRendererRegistry.INSTANCE.register(ModRegistry.TOBY, TobyRenderer::new);
    }

    private static EntityModelLayer createJpegModel(String name) {
        EntityModelLayer LAYER = new EntityModelLayer(new Identifier(MiChaelCraft.MOD_ID, name), "main");
        EntityModelLayerRegistry.registerModelLayer(LAYER, JpegModel::getTexturedModelData);
        return LAYER;
    }

}
