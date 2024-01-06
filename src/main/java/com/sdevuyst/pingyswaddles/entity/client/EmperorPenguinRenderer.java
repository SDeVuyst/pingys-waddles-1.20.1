package com.sdevuyst.pingyswaddles.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sdevuyst.pingyswaddles.PingysWaddles;
import com.sdevuyst.pingyswaddles.entity.custom.EmperorPenguinEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Pufferfish;

public class EmperorPenguinRenderer extends MobRenderer<EmperorPenguinEntity, EntityModel<EmperorPenguinEntity>> {

    private final EntityModel<EmperorPenguinEntity> baby;
    private final EntityModel<EmperorPenguinEntity> adult = this.getModel();

    public EmperorPenguinRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new EmperorPenguinModel<>(pContext.bakeLayer(ModModelLayers.EMPEROR_PENGUIN_LAYER)), 0.25f);
        this.baby = new BabyEmperorPenguinModel<>(pContext.bakeLayer(ModModelLayers.BABY_EMPEROR_PENGUIN_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(EmperorPenguinEntity emperorPenguinEntity) {
        if (emperorPenguinEntity.isBaby()) {
            return new ResourceLocation(PingysWaddles.MOD_ID, "textures/entity/baby_emperor_penguin.png");
        }
        return new ResourceLocation(PingysWaddles.MOD_ID, "textures/entity/emperor_penguin.png");
    }

    @Override
    public void render(EmperorPenguinEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        if(pEntity.isBaby()) {
            this.model = this.baby;
        } else {
            this.model = this.adult;
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }


}
