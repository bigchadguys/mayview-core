package net.joe.mayview;

import com.gitlab.srcmc.rctapi.api.RCTApi;

import dev.architectury.event.events.common.PlayerEvent;
import net.joe.mayview.block.ModBlocks;
import net.joe.mayview.block.entity.ModBlockEntities;
import net.joe.mayview.data.ModDataComponents;
import net.joe.mayview.entity.ModEntities;
import net.joe.mayview.entity.client.RatTraderRenderer;
import net.joe.mayview.entity.client.RatTrainerRenderer;
import net.joe.mayview.item.ModArmorMaterials;
import net.joe.mayview.item.ModCreativeModeTabs;
import net.joe.mayview.item.ModItems;
import net.joe.mayview.item.custom.EcoliteHoeEffect;
import net.joe.mayview.item.custom.FishingPackage.FishingHookRegistry;
import net.joe.mayview.loot.AddCoinModifier;
import net.joe.mayview.loot.AddItemModifier;
import net.joe.mayview.recipe.ModRecipes;
import net.joe.mayview.screen.ModMenuTypes;
import net.joe.mayview.screen.custom.FishTrapScreen;
import net.joe.mayview.screen.custom.MorphiteSynthesizerScreen;
import net.joe.mayview.screen.custom.PiggyBankScreen;
import net.joe.mayview.sound.ModSounds;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
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

    private static final RCTApi RCT = RCTApi.initInstance(MOD_ID);

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
        PlayerEvent.PLAYER_JOIN.register(Mayview::onPlayerJoin);
        PlayerEvent.PLAYER_QUIT.register(Mayview::onPlayerQuit);
        EcoliteHoeEffect.Actions.get().setup();
    }

    public static void onPlayerJoin(ServerPlayer player) {
        RCT.getTrainerRegistry().registerPlayer(player.getName().getString(), player);
    }

    static void onPlayerQuit(Player player) {
        RCT.getTrainerRegistry().unregisterById(player.getName().getString());
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        MinecraftServer server = event.getServer();
        var trainerRegistry = RCT.getTrainerRegistry();
        trainerRegistry.init(server);
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
            EntityRenderers.register(ModEntities.RAT_TRADER.get(), RatTraderRenderer::new);
            EntityRenderers.register(ModEntities.RAT_TRAINER.get(), RatTrainerRenderer::new);
            for (Item rod : ModItems.getCustomFishingRods()) {
                ItemProperties.register(rod,
                        ResourceLocation.fromNamespaceAndPath("minecraft", "cast"),
                        (stack, level, entity, seed) -> {
                            if (entity instanceof Player player) {
                                return (player.getMainHandItem() == stack || player.getOffhandItem() == stack) &&
                                        !FishingHookRegistry.getValidHooks(player).isEmpty() ? 1.0F : 0.0F;
                            }
                            return 0.0F;
                        }
                );
            }
        }
    }
}