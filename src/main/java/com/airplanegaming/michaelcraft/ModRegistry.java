package com.airplanegaming.michaelcraft;

import com.airplanegaming.michaelcraft.effect.SimpEffect;
import com.airplanegaming.michaelcraft.entity.*;
import com.airplanegaming.michaelcraft.item.EmergencyMeeting;
import com.airplanegaming.michaelcraft.item.MagicMirror;
import com.airplanegaming.michaelcraft.item.Pokimaine;
import com.airplanegaming.michaelcraft.mixin.BrewingRecipeRegistryAccessor;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.server.command.CommandManager;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import net.minecraft.entity.attribute.DefaultAttributeContainer.Builder;

public class ModRegistry {

    // Items
    public static final Item POKIMAINE = new Pokimaine();
    public static final Item FOOT = new Item(MiChaelCraft.itemSettings().food(new FoodComponent.Builder().alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 20), 1f).build()
    ));
    public static final Item MAGIC_MIRROR = new MagicMirror();
    public static final Item EMERGENCY_MEETING = new EmergencyMeeting();

    // Entities
    public static final EntityType<Toby> TOBY = createEntity("toby", FabricEntityTypeBuilder.create(
            SpawnGroup.CREATURE, Toby::new).dimensions(EntityDimensions.fixed(4.3f, 4.3f)).build(),
            Toby.createMobAttributes()
    );
    public static final EntityType<Borg> BORG = createEntity("borg", FabricEntityTypeBuilder.create(
            SpawnGroup.CREATURE, Borg::new).dimensions(EntityDimensions.fixed(4.3f, 4.3f)).build(),
            Borg.createMobAttributes()
            );
    public static final EntityType<Millager> MILLAGER = createEntity("millager", FabricEntityTypeBuilder.create(
            SpawnGroup.CREATURE, Millager::new).dimensions(EntityDimensions.fixed(0.6f, 1.99f)).build(),
            Millager.createVillagerAttributes()
    );
    public static final EntityType<Kai> KAI = createEntity("kai", FabricEntityTypeBuilder.create(
            SpawnGroup.MONSTER, Kai::new).dimensions(EntityDimensions.fixed(0.069f, 0.069f)).build(),
            Kai.createSilverfishAttributes()
    );
    public static final EntityType<GiantChicken> GIANT_CHICKEN = createEntity(
            "giant_chicken", GiantChicken.createEntityType(), GiantChicken.createAttributes()
    );

    // Effect
    public static final StatusEffect SIMP_EFFECT = new SimpEffect();

    // Sounds
    public static final SoundEvent TOBY_TARKOV_SOUND = createSound("toby_tarkov");
    public static final SoundEvent BANANA_SOUND = createSound("banana");
    public static final SoundEvent BORG_AMBIENT_SOUND = createSound("borg_ambient");
    public static final SoundEvent BORG_HURT_SOUND = createSound("borg_hurt");
    public static final SoundEvent BORG_DEATH_SOUND = createSound("borg_death");
    public static final SoundEvent BORG_STEP_SOUND = createSound("borg_step");
    public static final SoundEvent SUS_ALARM_SOUND = createSound("sus_alarm");
    public static final SoundEvent SUS_DING_SOUND = createSound("sus_ding");

    public static void registerThings() {
        // Items
        registerItem("pokimaine", POKIMAINE);
        registerItem("foot", FOOT);
        registerItem("magic_mirror", MAGIC_MIRROR);
        registerItem("emergency_meeting", EMERGENCY_MEETING);

        // Effect
        Registry.register(Registry.STATUS_EFFECT, new Identifier(MiChaelCraft.MOD_ID, "simp_effect"), SIMP_EFFECT);

        // Potions
        createPotion(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 20, 5),
                "instant_die", Potions.STRONG_HARMING, FOOT);
        createPotion(new StatusEffectInstance(SIMP_EFFECT, 60 * 20), "simping", Potions.WEAKNESS, POKIMAINE);

        // Dimension
        CustomPortalBuilder.beginPortal().frameBlock(Blocks.GLOWSTONE)
                .destDimID(new Identifier(MiChaelCraft.MOD_ID, "aether")).lightWithWater()
                .tintColor(31, 98, 255).onlyLightInOverworld().registerPortal();

        // Command
        CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated) -> dispatcher.register(
                CommandManager.literal("good").requires(source -> source.hasPermissionLevel(2))
                .executes(context -> {
                    MiChaelCraft.log(Level.INFO, "Good command executed");
                    var world = context.getSource().getWorld();
                    world.setWeather(1000000, 0, false, false);
                    world.setTimeOfDay(1000);
                    return 1;
                })
        )));

        MiChaelCraft.log(Level.INFO, "Registered everything");
    }

    private static <T extends MobEntity> EntityType<T> createEntity(String id, EntityType<T> entity, Builder attributes) {
        EntityType<T> rEntity =  Registry.register(Registry.ENTITY_TYPE, new Identifier(MiChaelCraft.MOD_ID, id), entity);
        FabricDefaultAttributeRegistry.register(rEntity, attributes);
        // Colour is red and yellow
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

    private static void createPotion(StatusEffectInstance effect, String name, Potion base, Item ingredient) {
        final Potion potion = new Potion(effect);
        Registry.register(Registry.POTION, new Identifier(MiChaelCraft.MOD_ID, name), potion);
        BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(base, ingredient, potion);
    }

    private static void registerItem(String name, Item item) {
        Registry.register(Registry.ITEM, new Identifier(MiChaelCraft.MOD_ID, name), item);
    }

}
