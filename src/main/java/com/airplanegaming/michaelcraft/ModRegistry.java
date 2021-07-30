package com.airplanegaming.michaelcraft;

import com.airplanegaming.michaelcraft.entity.CubeEntity;
import com.airplanegaming.michaelcraft.entity.Toby;
import com.airplanegaming.michaelcraft.item.Pokimaine;
import com.airplanegaming.michaelcraft.mixin.BrewingRecipeRegistryAccessor;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;

public class ModRegistry {

    // Items
    public static final Item POKIMAINE = new Pokimaine();
    public static final Item FOOT = new Item(new Item.Settings().group(MiChaelCraft.ITEM_GROUP).food(new FoodComponent
            .Builder().alwaysEdible().statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 20), 1f).build()
    ));

    // Entities
    public static final EntityType<CubeEntity> CUBE = createEntity("cube", FabricEntityTypeBuilder.create(
            SpawnGroup.CREATURE, CubeEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );
    public static final EntityType<Toby> TOBY = createEntity("toby", FabricEntityTypeBuilder.create(
            SpawnGroup.CREATURE, Toby::new).dimensions(EntityDimensions.fixed(4.3f, 4.3f)).build()
    );

    public static final Potion INSTANT_DIE = new Potion(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 20, 5));

    public static final SoundEvent TOBY_TARKOV_SOUND = createSound("toby_tarkov");

    public static void registerThings() {
        // Items
        Registry.register(Registry.ITEM, new Identifier(MiChaelCraft.MOD_ID, "pokimaine"), POKIMAINE);
        Registry.register(Registry.ITEM, new Identifier(MiChaelCraft.MOD_ID, "foot"), FOOT);

        // Potion
        Registry.register(Registry.POTION, new Identifier(MiChaelCraft.MOD_ID, "instant_die"), INSTANT_DIE);
        BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(Potions.STRONG_HARMING, FOOT, INSTANT_DIE);

        // Dimension
        CustomPortalBuilder.beginPortal().frameBlock(Blocks.GLOWSTONE)
                .destDimID(new Identifier(MiChaelCraft.MOD_ID, "aether")).lightWithWater()
                .tintColor(31, 98, 255).onlyLightInOverworld().registerPortal();

        MiChaelCraft.log(Level.INFO, "Registered everything");
    }

    private static <T extends MobEntity> EntityType<T> createEntity(String id, EntityType<T> entity) {
        EntityType<T> rEntity =  Registry.register(Registry.ENTITY_TYPE, new Identifier(MiChaelCraft.MOD_ID, id), entity);
        FabricDefaultAttributeRegistry.register(rEntity, T.createMobAttributes());
        Registry.register(Registry.ITEM, new Identifier(MiChaelCraft.MOD_ID, id + "_spawn_egg"), new SpawnEggItem(
                rEntity, 16711680, 16055299, new Item.Settings().group(MiChaelCraft.ITEM_GROUP)
        ));
        return rEntity;
    }

    private static SoundEvent createSound(String name) {
        var soundId = new Identifier("michael:" + name);
        var sound = new SoundEvent(soundId);
        Registry.register(Registry.SOUND_EVENT, soundId, sound);
        return sound;
    }

}
