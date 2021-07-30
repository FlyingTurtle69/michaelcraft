package com.airplanegaming.michaelcraft.client;

import com.airplanegaming.michaelcraft.MiChaelCraft;
import com.airplanegaming.michaelcraft.MiChaelCraftClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;

public class TobyRenderer extends MobEntityRenderer<MobEntity, JpegModel> {

    public TobyRenderer(EntityRendererFactory.Context context) {
        // f is shadowRadius
        super(context, new JpegModel(context.getPart(MiChaelCraftClient.MODEL_TOBY_LAYER)), 4.3f);
    }

    @Override
    public Identifier getTexture(MobEntity entity) {
        return new Identifier(MiChaelCraft.MOD_ID, "textures/entity/toby.png");
    }

}
