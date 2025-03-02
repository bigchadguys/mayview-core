package net.joe.mayview.item;

import net.joe.mayview.Mayview;
import net.joe.mayview.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Mayview.MOD_ID);

    public static final Supplier<CreativeModeTab> MAYVIEW =
            CREATIVE_MODE_TABS.register("mayview_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.mayview.mayview_tab"))
                    .icon(() -> new ItemStack(ModItems.RAW_MORPHITE.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.COPPER_COIN);
                        output.accept(ModItems.IRON_COIN);
                        output.accept(ModItems.GOLD_COIN);
                        output.accept(ModItems.DIAMOND_COIN);
                        output.accept(ModItems.JOE_COIN);
                        output.accept(ModItems.COIN_POUCH);
                        output.accept(ModItems.PIGGY_BANK);

                        output.accept(ModItems.HONEY_DIAMOND);
                        output.accept(ModItems.RAW_MORPHITE);
                        output.accept(ModItems.RAW_ENDERITE);
                        output.accept(ModItems.ENDERITE_INGOT);
                        output.accept(ModBlocks.MORPHITE_ORE);
                        output.accept(ModBlocks.DEEPSLATE_MORPHITE_ORE);
                        output.accept(ModBlocks.TECTONIC_ORE);
                        output.accept(ModBlocks.DEEPSLATE_TECTONIC_ORE);
                        output.accept(ModItems.ECTOPLASM);
                        output.accept(ModItems.MUSH);
                        output.accept(ModItems.MYSTERIOUS_BUBBLE);
                        output.accept(ModItems.TECTONIC_SHARD);
                        output.accept(ModItems.EVEROAK);
                        output.accept(ModItems.QUICKSILVER);

                        output.accept(ModBlocks.MORPHITE_SYNTHESIZER);
                        output.accept(ModItems.MORPHITE_INGOT);
                        output.accept(ModItems.SPECTRITE_INGOT);
                        output.accept(ModItems.ECOLITE_INGOT);
                        output.accept(ModItems.HYDRITE_INGOT);
                        output.accept(ModItems.TECTRITE_INGOT);
                        output.accept(ModItems.PETRAFITE_INGOT);
                        output.accept(ModItems.SWIFTITE_INGOT);

                        output.accept(ModItems.WORM);
                        output.accept(ModItems.GUMMY_WORM);
                        output.accept(ModBlocks.FISH_TRAP);
                        output.accept(ModItems.BEGINNER_FISHING_ROD);
                        output.accept(ModItems.INTERMEDIATE_FISHING_ROD);
                        output.accept(ModItems.ADVANCED_FISHING_ROD);
                        output.accept(ModItems.EXPERT_FISHING_ROD);
                        output.accept(ModItems.PROFESSIONAL_FISHING_ROD);
                        output.accept(ModItems.HYDRITE_FISHING_ROD);
                        output.accept(ModItems.REINFORCED_HYDRITE_FISHING_ROD);

                        output.accept(ModItems.CUSTOM_BOW);

                        output.accept(ModItems.COPPER_SWORD);
                        output.accept(ModItems.COPPER_PICKAXE);
                        output.accept(ModItems.COPPER_AXE);
                        output.accept(ModItems.COPPER_SHOVEL);
                        output.accept(ModItems.COPPER_HOE);
                        output.accept(ModItems.HONEY_DIAMOND_SWORD);
                        output.accept(ModItems.HONEY_DIAMOND_PICKAXE);
                        output.accept(ModItems.HONEY_DIAMOND_AXE);
                        output.accept(ModItems.HONEY_DIAMOND_SHOVEL);
                        output.accept(ModItems.HONEY_DIAMOND_HOE);
                        output.accept(ModItems.AMETHYST_SWORD);
                        output.accept(ModItems.AMETHYST_PICKAXE);
                        output.accept(ModItems.AMETHYST_AXE);
                        output.accept(ModItems.AMETHYST_SHOVEL);
                        output.accept(ModItems.AMETHYST_HOE);
                        output.accept(ModItems.EMERALD_SWORD);
                        output.accept(ModItems.EMERALD_PICKAXE);
                        output.accept(ModItems.EMERALD_AXE);
                        output.accept(ModItems.EMERALD_SHOVEL);
                        output.accept(ModItems.EMERALD_HOE);
                        output.accept(ModItems.SPECTRITE_SWORD);
                        output.accept(ModItems.SWIFTITE_SHOVEL);
                        output.accept(ModItems.TECTRITE_PICKAXE);
                        output.accept(ModItems.PETRAFITE_AXE);
                        output.accept(ModItems.ECOLITE_HOE);
                        output.accept(ModItems.RAT_TRADER_SPAWN_EGG);
                        output.accept(ModItems.RAT_TRAINER_SPAWN_EGG);
                        output.accept(ModItems.HONEY_DIAMOND_HELMET);
                        output.accept(ModItems.HONEY_DIAMOND_CHESTPLATE);
                        output.accept(ModItems.HONEY_DIAMOND_LEGGINGS);
                        output.accept(ModItems.HONEY_DIAMOND_BOOTS);
                        output.accept(ModItems.COPPER_HELMET);
                        output.accept(ModItems.COPPER_CHESTPLATE);
                        output.accept(ModItems.COPPER_LEGGINGS);
                        output.accept(ModItems.COPPER_BOOTS);
                        output.accept(ModItems.STEEL_SWORD);
                        output.accept(ModItems.STEEL_PICKAXE);
                        output.accept(ModItems.STEEL_AXE);
                        output.accept(ModItems.STEEL_SHOVEL);
                        output.accept(ModItems.STEEL_HOE);
                        output.accept(ModItems.STEEL_HELMET);
                        output.accept(ModItems.STEEL_CHESTPLATE);
                        output.accept(ModItems.STEEL_LEGGINGS);
                        output.accept(ModItems.STEEL_BOOTS);
                        output.accept(ModItems.STEEL_INGOT);
                        output.accept(ModItems.AMETHYST_HELMET);
                        output.accept(ModItems.AMETHYST_CHESTPLATE);
                        output.accept(ModItems.AMETHYST_LEGGINGS);
                        output.accept(ModItems.AMETHYST_BOOTS);
                        output.accept(ModItems.EMERALD_HELMET);
                        output.accept(ModItems.EMERALD_CHESTPLATE);
                        output.accept(ModItems.EMERALD_LEGGINGS);
                        output.accept(ModItems.EMERALD_BOOTS);
                        output.accept(ModItems.ECOLITE_HELMET);
                        output.accept(ModItems.ECOLITE_CHESTPLATE);
                        output.accept(ModItems.ECOLITE_LEGGINGS);
                        output.accept(ModItems.ECOLITE_BOOTS);
                        output.accept(ModItems.HYDRITE_HELMET);
                        output.accept(ModItems.HYDRITE_CHESTPLATE);
                        output.accept(ModItems.HYDRITE_LEGGINGS);
                        output.accept(ModItems.HYDRITE_BOOTS);
                        output.accept(ModItems.PETRAFITE_HELMET);
                        output.accept(ModItems.PETRAFITE_CHESTPLATE);
                        output.accept(ModItems.PETRAFITE_LEGGINGS);
                        output.accept(ModItems.PETRAFITE_BOOTS);
                        output.accept(ModItems.SPECTRITE_HELMET);
                        output.accept(ModItems.SPECTRITE_CHESTPLATE);
                        output.accept(ModItems.SPECTRITE_LEGGINGS);
                        output.accept(ModItems.SPECTRITE_BOOTS);
                        output.accept(ModItems.SWIFTITE_HELMET);
                        output.accept(ModItems.SWIFTITE_CHESTPLATE);
                        output.accept(ModItems.SWIFTITE_LEGGINGS);
                        output.accept(ModItems.SWIFTITE_BOOTS);
                        output.accept(ModItems.TECTRITE_HELMET);
                        output.accept(ModItems.TECTRITE_CHESTPLATE);
                        output.accept(ModItems.TECTRITE_LEGGINGS);
                        output.accept(ModItems.TECTRITE_BOOTS);
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
