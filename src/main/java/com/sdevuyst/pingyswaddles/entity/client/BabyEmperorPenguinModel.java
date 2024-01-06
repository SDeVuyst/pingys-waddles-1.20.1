package com.sdevuyst.pingyswaddles.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sdevuyst.pingyswaddles.animations.EmperorPenguinAnimationDefinitions;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class BabyEmperorPenguinModel<T extends Entity> extends HierarchicalModel<T> {

    private final ModelPart root;
    private final ModelPart head;

    public BabyEmperorPenguinModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("bone").getChild("top");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 135F, 0.0F));

        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition top = bone.addOrReplaceChild("top", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = top.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 18).addBox(-3.0F, -14.0F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition beak = top.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -11.5F, 2.3F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition wings = bone.addOrReplaceChild("wings", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rightwing = wings.addOrReplaceChild("rightwing", CubeListBuilder.create(), PartPose.offset(4.0F, -9.0F, 0.0F));

        PartDefinition cube_r1 = rightwing.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 27).addBox(0.5F, 0.75F, -1.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.042F, -1.1456F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition leftwing = wings.addOrReplaceChild("leftwing", CubeListBuilder.create(), PartPose.offset(-4.2558F, -9.3605F, 0.0F));

        PartDefinition cube_r2 = leftwing.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 27).mirror().addBox(-1.5F, 0.75F, -1.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.2978F, -0.7851F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition feet = bone.addOrReplaceChild("feet", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rightfoot = feet.addOrReplaceChild("rightfoot", CubeListBuilder.create().texOffs(0, 18).addBox(1.0F, -1.0F, 3.425F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leftfoot = feet.addOrReplaceChild("leftfoot", CubeListBuilder.create().texOffs(0, 3).addBox(-2.0F, -1.0F, 3.425F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tail = bone.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition part1 = tail.addOrReplaceChild("part1", CubeListBuilder.create().texOffs(18, 18).addBox(-0.5F, -1.0F, -5.075F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(entity, netHeadYaw, headPitch, ageInTicks);
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
