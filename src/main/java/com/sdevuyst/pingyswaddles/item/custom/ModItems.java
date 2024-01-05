package com.sdevuyst.pingyswaddles.item.custom;

import com.sdevuyst.pingyswaddles.PingysWaddles;
import com.sdevuyst.pingyswaddles.entity.ModEntities;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PingysWaddles.MOD_ID);

    public static final RegistryObject<Item> EMPEROR_PENGUIN_SPAWN_EGG = ITEMS.register("emperor_penguin_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.EMPEROR_PENGUIN, 0xf9f6ee, 0xf0b722, new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}