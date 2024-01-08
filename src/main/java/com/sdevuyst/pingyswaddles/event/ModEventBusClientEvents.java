package com.sdevuyst.pingyswaddles.event;

import com.sdevuyst.pingyswaddles.PingysWaddles;
import com.sdevuyst.pingyswaddles.entity.client.BabyEmperorPenguinModel;
import com.sdevuyst.pingyswaddles.entity.client.EmperorPenguinModel;
import com.sdevuyst.pingyswaddles.entity.client.ModModelLayers;
import com.sdevuyst.pingyswaddles.entity.client.SurfboardModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PingysWaddles.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents
{
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(ModModelLayers.EMPEROR_PENGUIN_LAYER, EmperorPenguinModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.BABY_EMPEROR_PENGUIN_LAYER, BabyEmperorPenguinModel::createBodyLayer);

        event.registerLayerDefinition(ModModelLayers.OAK_SURFBOARD_LAYER, SurfboardModel::createBodyLayer);
    }

}
