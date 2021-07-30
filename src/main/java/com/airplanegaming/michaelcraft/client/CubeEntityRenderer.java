package com.airplanegaming.michaelcraft.client;

import com.airplanegaming.michaelcraft.MiChaelCraft;
import com.airplanegaming.michaelcraft.MiChaelCraftClient;
import com.airplanegaming.michaelcraft.entity.CubeEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class CubeEntityRenderer extends MobEntityRenderer<CubeEntity, CubeEntityModel> {

    public CubeEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CubeEntityModel(context.getPart(MiChaelCraftClient.MODEL_CUBE_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(CubeEntity entity) {
        return new Identifier(MiChaelCraft.MOD_ID, "textures/entity/cube/cube.png");
    }

}
