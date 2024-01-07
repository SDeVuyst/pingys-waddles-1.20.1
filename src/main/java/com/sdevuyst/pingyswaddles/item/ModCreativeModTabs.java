package com.sdevuyst.pingyswaddles.item;

import com.sdevuyst.pingyswaddles.PingysWaddles;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PingysWaddles.MOD_ID);

    public static final RegistryObject<CreativeModeTab> PINGYS_WADDLES_TAB = CREATIVE_MODE_TABS.register("diabolicahh_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.EMPEROR_PENGUIN_SPAWN_EGG.get()))
                    .title(Component.translatable("creativetab.pingys_waddles_tab"))
                    .displayItems((pParameters, pOutput) -> {

                        pOutput.accept(ModItems.EMPEROR_PENGUIN_SPAWN_EGG.get());
                        pOutput.accept(ModItems.OAK_SURFBOARD.get());

                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
