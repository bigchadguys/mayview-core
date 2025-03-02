package net.joe.mayview.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.joe.mayview.Mayview;
import net.joe.mayview.entity.custom.RatTrainerEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class RatTrainerRenderer extends MobRenderer<RatTrainerEntity, RatTrainerModel> {

    public RatTrainerRenderer(EntityRendererProvider.Context context) {
        super(context, new RatTrainerModel(context.bakeLayer(ModModelLayers.RAT_TRAINER)), 0.45f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull RatTrainerEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(Mayview.MOD_ID, "textures/entity/rat_trainer/rat_trainer.png");
    }

    @Override
    public void render(@NotNull RatTrainerEntity entity, float entityYaw, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
