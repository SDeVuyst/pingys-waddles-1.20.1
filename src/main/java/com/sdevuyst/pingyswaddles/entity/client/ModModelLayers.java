package com.sdevuyst.pingyswaddles.entity.client;

import com.sdevuyst.pingyswaddles.PingysWaddles;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers
{
    public static final ModelLayerLocation EMPEROR_PENGUIN_LAYER = new ModelLayerLocation(
            new ResourceLocation(PingysWaddles.MOD_ID, "emperor_penguin_layer"), "main"
    );

    public static final ModelLayerLocation BABY_EMPEROR_PENGUIN_LAYER = new ModelLayerLocation(
            new ResourceLocation(PingysWaddles.MOD_ID, "baby_emperor_penguin_layer"), "main"
    );

    public static final ModelLayerLocation OAK_SURFBOARD_LAYER = new ModelLayerLocation(
            new ResourceLocation(PingysWaddles.MOD_ID, "surfboard_layer"), "main"
    );
}
