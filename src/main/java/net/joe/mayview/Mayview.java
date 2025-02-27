package net.joe.mayview;

import net.joe.mayview.block.ModBlocks;
import net.joe.mayview.block.entity.ModBlockEntities;
import net.joe.mayview.data.ModDataComponents;
import net.joe.mayview.entity.ModEntities;
import net.joe.mayview.entity.client.MouseRatRenderer;
import net.joe.mayview.item.ModArmorMaterials;
import net.joe.mayview.item.ModCreativeModeTabs;
import net.joe.mayview.item.ModItems;
import net.joe.mayview.loot.AddCoinModifier;
import net.joe.mayview.loot.AddItemModifier;
import net.joe.mayview.recipe.ModRecipes;
import net.joe.mayview.screen.ModMenuTypes;
import net.joe.mayview.screen.custom.FishTrapScreen;
import net.joe.mayview.screen.custom.MorphiteSynthesizerScreen;
import net.joe.mayview.screen.custom.PiggyBankScreen;
import net.joe.mayview.sound.ModSounds;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(Mayview.MOD_ID)
public class Mayview {
    public static final String MOD_ID = "mayview";

    public Mayview(IEventBus modEventBus) {
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModArmorMaterials.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModEntities.register(modEventBus);
        ModSounds.register(modEventBus);
        AddItemModifier.GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        AddCoinModifier.GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        ModDataComponents.register(modEventBus);
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.MORPHITE_SYNTHESIZER_MENU.get(), MorphiteSynthesizerScreen::new);
            event.register(ModMenuTypes.FISH_TRAP_MENU.get(), FishTrapScreen::new);
            event.register(ModMenuTypes.PIGGY_BANK_MENU.get(), PiggyBankScreen::new);

        }

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.MOUSERAT.get(), MouseRatRenderer::new);
        }
    }
}
