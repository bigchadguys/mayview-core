package net.joe.mayview.event;

import net.joe.mayview.Mayview;
import net.joe.mayview.entity.ModEntities;
import net.joe.mayview.entity.client.ModModelLayers;
import net.joe.mayview.entity.client.MouseRatModel;
import net.joe.mayview.entity.custom.MouseRatEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = Mayview.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.MOUSERAT, MouseRatModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MOUSERAT.get(), MouseRatEntity.createAttributes().build());
    }
}
