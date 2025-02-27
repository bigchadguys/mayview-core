package net.joe.mayview.block;

import net.joe.mayview.Mayview;
import net.joe.mayview.block.custom.FishTrapBlock;
import net.joe.mayview.block.custom.MorphiteSynthesizerBlock;
import net.joe.mayview.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Mayview.MOD_ID);
    public static final DeferredBlock<Block> MORPHITE_ORE = registerBlock("morphite_ore", () -> new Block(BlockBehaviour.Properties.of().strength(4.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_MORPHITE_ORE = registerBlock("deepslate_morphite_ore", () -> new Block(BlockBehaviour.Properties.of().strength(4.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> TECTONIC_ORE = registerBlock("tectonic_ore", () -> new Block(BlockBehaviour.Properties.of().strength(4.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_TECTONIC_ORE = registerBlock("deepslate_tectonic_ore", () -> new Block(BlockBehaviour.Properties.of().strength(4.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> MORPHITE_SYNTHESIZER = registerBlock("morphite_synthesizer",
            () -> new MorphiteSynthesizerBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> FISH_TRAP = registerBlock("fish_trap",
            () -> new FishTrapBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
