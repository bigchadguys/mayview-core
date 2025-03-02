package net.joe.mayview.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.joe.mayview.entity.custom.RatTrainerEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class RatTrainerModel extends HierarchicalModel<RatTrainerEntity> {
    private final ModelPart mouserat;
    private final ModelPart head;

    public RatTrainerModel(ModelPart root) {
        this.mouserat = root.getChild("mouserat");
        this.head = this.mouserat.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition mouserat = partdefinition.addOrReplaceChild("mouserat", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = mouserat.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -6.5F, 2.5F));

        PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 60).addBox(-2.0F, -0.5F, -3.5F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.4F, 8.9F, 0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 56).addBox(-3.0F, -2.5F, -4.5F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 7.9F, 0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -2.5F, -4.5F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-3.5F, -1.4982F, -3.995F));

        PartDefinition cube_r4 = right_arm.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(14, 39).addBox(-1.0F, -2.015F, -0.4974F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0132F, -1.0076F, -0.4974F, 0.0F, 0.0F));

        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(3.5F, -1.4982F, -3.995F));

        PartDefinition cube_r5 = left_arm.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(14, 39).addBox(-1.0F, -2.015F, -0.4974F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0132F, -1.0076F, -0.3491F, 0.0F, 0.0F));

        PartDefinition floatie = body.addOrReplaceChild("floatie", CubeListBuilder.create().texOffs(0, 54).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -0.5F));

        PartDefinition head = mouserat.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 15).addBox(-3.0F, -2.9893F, -4.8F, 6.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(26, 55).addBox(-3.0F, -4.9893F, -4.8F, 6.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 45).addBox(-3.0F, -3.9893F, -6.8F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(32, 12).addBox(-1.0F, 1.0107F, -7.2F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.9153F, 1.845F));

        PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create(), PartPose.offset(-2.2878F, -3.5256F, -0.2173F));

        PartDefinition cube_r6 = right_ear.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(18, 27).addBox(-0.2479F, -3.5059F, -2.5323F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9101F, -0.4505F, 1.5222F, 0.1052F, -0.6968F, -0.0342F));

        PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create(), PartPose.offset(2.2878F, -3.5256F, -0.2173F));

        PartDefinition cube_r7 = left_ear.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(32, 0).addBox(-0.7521F, -3.5059F, -2.5323F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.9101F, -0.4505F, 1.5222F, 0.1052F, 0.6968F, 0.0342F));

        PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 39).addBox(-2.0F, -2.5F, -1.3F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5107F, -6.5F));

        PartDefinition nose_tip = nose.addOrReplaceChild("nose_tip", CubeListBuilder.create().texOffs(3, 41).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, -1.8F));

        PartDefinition left_leg = mouserat.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 27).addBox(-16.75F, 5.1502F, -6.1244F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(19.25F, -6.1502F, 3.1244F));

        PartDefinition cube_r8 = left_leg.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(26, 15).addBox(-1.5F, -3.0F, -3.3F, 3.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.75F, 2.3502F, -0.6244F, -0.0873F, 0.0F, 0.0F));

        PartDefinition right_leg = mouserat.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 34).addBox(-6.25F, 5.1502F, -6.1244F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(1.75F, -6.1502F, 3.1244F));

        PartDefinition cube_r9 = right_leg.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 27).addBox(-1.5F, -3.0F, -3.3F, 3.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.25F, 2.3502F, -0.6244F, -0.0873F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }


    @Override
    public void setupAnim(RatTrainerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);
        if (entity.isInWaterOrBubble()) {
            this.animate(entity.swimAnimationState, RatTrainerAnimations.swim, ageInTicks, 1f);
        } else {
            this.animateWalk(RatTrainerAnimations.walk, limbSwing, limbSwingAmount, 6f, 2.5f);
            this.animate(entity.idleAnimationState, RatTrainerAnimations.idle, ageInTicks, 1f);
        }
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45f);

        this.head.yRot = headYaw * ((float)Math.PI / 180f);
        this.head.xRot = headPitch * ((float)Math.PI / 180f);

    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        mouserat.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public @NotNull ModelPart root() {
        return mouserat;
    }
}
