package net.joe.mayview.event;

import net.joe.mayview.Mayview;
import net.joe.mayview.entity.ModEntities;
import net.joe.mayview.entity.client.ModModelLayers;
import net.joe.mayview.entity.client.RatTraderModel;
import net.joe.mayview.entity.client.RatTrainerModel;
import net.joe.mayview.entity.custom.RatTraderEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = Mayview.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.MOUSERAT, RatTraderModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.RAT_TRAINER, RatTrainerModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.RAT_TRADER.get(), RatTraderEntity.createAttributes().build());
        event.put(ModEntities.RAT_TRAINER.get(), RatTraderEntity.createAttributes().build());
    }
}
