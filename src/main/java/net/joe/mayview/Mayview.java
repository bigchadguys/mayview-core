package net.joe.mayview;

import net.joe.mayview.block.entity.ModBlockEntities;
import net.joe.mayview.item.ModArmorMaterials;
import net.joe.mayview.block.ModBlocks;
import net.joe.mayview.item.ModCreativeModeTabs;
import net.joe.mayview.item.ModItems;
import net.joe.mayview.recipe.ModRecipes;
import net.joe.mayview.screen.ModMenuTypes;
import net.joe.mayview.screen.custom.MorphiteSynthesizerScreen;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@Mod(Mayview.MOD_ID)
public class Mayview {
    public static final String MOD_ID = "mayview";

    public Mayview(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModArmorMaterials.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @EventBusSubscriber(modid = MOD_ID)
    public static class ZombieDropHandler {
        @SubscribeEvent
        public static void onLivingDrop(LivingDropsEvent event) {
            LivingEntity entity = event.getEntity();

            if (entity instanceof Zombie) {
                ItemEntity drop = new ItemEntity(
                        entity.level(),
                        entity.getX(),
                        entity.getY(),
                        entity.getZ(),
                        new ItemStack(ModItems.ECTOPLASM.get())
                );

                event.getDrops().add(drop);
            }
        }
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.MORPHITE_SYNTHESIZER_MENU.get(), MorphiteSynthesizerScreen::new);
        }
    }
}
