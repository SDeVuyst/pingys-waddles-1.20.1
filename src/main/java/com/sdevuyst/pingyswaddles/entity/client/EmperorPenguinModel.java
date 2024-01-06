package com.sdevuyst.pingyswaddles.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sdevuyst.pingyswaddles.animations.EmperorPenguinAnimationDefinitions;
import com.sdevuyst.pingyswaddles.entity.custom.EmperorPenguinEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class EmperorPenguinModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart root;
    private final ModelPart head;

    public EmperorPenguinModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("emperor_penguin").getChild("top");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition emperor_penguin = partdefinition.addOrReplaceChild("emperor_penguin", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 135F, 0.0F));

        PartDefinition top = emperor_penguin.addOrReplaceChild("top", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, -11.5F, 0.0F, -0.0349F, 0.0F, 0.0F));

        PartDefinition head = top.addOrReplaceChild("head", CubeListBuilder.create().texOffs(2, 38).addBox(-7.0F, -18.5009F, -6.9477F, 14.0F, 10.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition beak = top.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(50, 2).addBox(-1.0F, -12.5009F, 6.0523F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = emperor_penguin.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -20.0F, -8.0F, 16.0F, 20.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 0.0F));

        PartDefinition wings = emperor_penguin.addOrReplaceChild("wings", CubeListBuilder.create(), PartPose.offset(2.0F, -1.5F, 0.0F));

        PartDefinition rightwing = wings.addOrReplaceChild("rightwing", CubeListBuilder.create(), PartPose.offset(7.0F, -17.0F, 0.0F));

        PartDefinition cube_r1 = rightwing.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(60, 58).addBox(15.0F, -14.0F, 11.0F, 2.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.7747F, 14.9515F, -13.0F, 0.0F, 0.0F, -0.1309F));

        PartDefinition leftwing = wings.addOrReplaceChild("leftwing", CubeListBuilder.create(), PartPose.offset(-7.0F, -17.0F, 0.0F));

        PartDefinition cube_r2 = leftwing.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(60, 32).addBox(-17.0F, -14.0F, 11.0F, 2.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.2192F, 15.1698F, -13.0F, 0.0F, 0.0F, 0.1309F));

        PartDefinition feet = emperor_penguin.addOrReplaceChild("feet", CubeListBuilder.create(), PartPose.offset(2.0F, -2.0F, 0.0F));

        PartDefinition rightfoot = feet.addOrReplaceChild("rightfoot", CubeListBuilder.create().texOffs(0, 7).addBox(2.0F, 0.0F, 6.0F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leftfoot = feet.addOrReplaceChild("leftfoot", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, 0.0F, 6.0F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tail = emperor_penguin.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(8.25F, -1.75F, 0.75F));

        PartDefinition part1 = tail.addOrReplaceChild("part1", CubeListBuilder.create().texOffs(0, 36).addBox(-7.25F, 0.5F, -10.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition part2 = tail.addOrReplaceChild("part2", CubeListBuilder.create(), PartPose.offset(-6.25F, 0.25F, -0.75F));

        PartDefinition cube_r3 = part2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(0.5F, -1.0F, -13.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.25F, 2.75F, 0.0349F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(entity, netHeadYaw, headPitch, ageInTicks);

        this.animateWalk(EmperorPenguinAnimationDefinitions.WALKING, limbSwing, limbSwingAmount, 2f, 2.5f);

        this.animate(((EmperorPenguinEntity) entity).idleAnimationState, EmperorPenguinAnimationDefinitions.WINGING, ageInTicks, 1f);
        this.animate(((EmperorPenguinEntity) entity).fallingAnimationState, EmperorPenguinAnimationDefinitions.FALLING, ageInTicks, 1f);

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void applyHeadRotation(T pEntity, float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -25.0F, 25.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -5.0F, 15.0F);

        this.head.yRot = pNetHeadYaw * 0.017453292F;
        this.head.xRot = pHeadPitch * -0.017453292F;
    }

    @Override
    public ModelPart root() {
        return root;
    }
}
