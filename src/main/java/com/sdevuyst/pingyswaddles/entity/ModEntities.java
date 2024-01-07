package com.sdevuyst.pingyswaddles.entity;
import com.sdevuyst.pingyswaddles.PingysWaddles;
import com.sdevuyst.pingyswaddles.entity.custom.EmperorPenguinEntity;
import com.sdevuyst.pingyswaddles.entity.custom.SurfboardEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PingysWaddles.MOD_ID);

    public static final RegistryObject<EntityType<EmperorPenguinEntity>> EMPEROR_PENGUIN =
            ENTITY_TYPES.register("emperor_penguin", () -> EntityType.Builder.of(EmperorPenguinEntity::new, MobCategory.CREATURE)
                    .sized(2, 2).build("emperor_penguin"));

    public static final RegistryObject<EntityType<Entity>> SURFBOARD =
            ENTITY_TYPES.register("surfboard", () -> EntityType.Builder.of(SurfboardEntity::new, MobCategory.MISC)
                    .sized(2f, 0.05f).build("surfboard"));



    public static void register(IEventBus eventBus)
    {
        ENTITY_TYPES.register(eventBus);
    }
}