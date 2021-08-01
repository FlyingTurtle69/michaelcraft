package com.airplanegaming.michaelcraft.client;

import com.airplanegaming.michaelcraft.MiChaelCraft;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;

public class RendererFactory {

    private final EntityModelLayer layer;
    private final String name;
    private final float shadowSize;

    public RendererFactory(EntityModelLayer layer, String name, float shadowSize) {
        this.layer = layer;
        this.name = name;
        this.shadowSize = shadowSize;
    }

    public MobEntityRenderer<MobEntity, CuboidModel> create(EntityRendererFactory.Context context) {
        return new MobEntityRenderer<>(context, new CuboidModel(context.getPart(layer)), shadowSize) {
            @Override
            public Identifier getTexture(MobEntity entity) {
                return new Identifier(MiChaelCraft.MOD_ID, "textures/entity/" +  name + ".png");
            }
        };
    }
}
