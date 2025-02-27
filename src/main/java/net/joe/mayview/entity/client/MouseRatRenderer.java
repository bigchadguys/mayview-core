package net.joe.mayview.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.joe.mayview.Mayview;
import net.joe.mayview.entity.custom.MouseRatEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class MouseRatRenderer extends MobRenderer<MouseRatEntity, MouseRatModel> {

    public MouseRatRenderer(EntityRendererProvider.Context context) {
        super(context, new MouseRatModel(context.bakeLayer(ModModelLayers.MOUSERAT)), 0.45f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull MouseRatEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(Mayview.MOD_ID, "textures/entity/mouserat/mouserat.png");
    }

    @Override
    public void render(@NotNull MouseRatEntity entity, float entityYaw, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
