package com.sdevuyst.pingyswaddles.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sdevuyst.pingyswaddles.PingysWaddles;
import com.sdevuyst.pingyswaddles.entity.custom.EmperorPenguinEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EmperorPenguinRenderer extends MobRenderer<EmperorPenguinEntity, EmperorPenguinModel<EmperorPenguinEntity>> {
    public EmperorPenguinRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new EmperorPenguinModel<>(pContext.bakeLayer(ModModelLayers.EMPEROR_PENGUIN_LAYER)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(EmperorPenguinEntity emperorPenguinEntity) {
        return new ResourceLocation(PingysWaddles.MOD_ID, "textures/entity/emperor_penguin.png");
    }

    @Override
    public void render(EmperorPenguinEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pPoseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }


}
