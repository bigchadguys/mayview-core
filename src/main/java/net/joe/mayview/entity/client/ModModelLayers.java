package net.joe.mayview.entity.client;

import net.joe.mayview.Mayview;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation MOUSERAT = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(Mayview.MOD_ID, "mouserat"), "main");
}
