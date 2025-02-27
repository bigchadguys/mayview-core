package net.joe.mayview.datagen;

import net.joe.mayview.Mayview;
import net.joe.mayview.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Mayview.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.MORPHITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_MORPHITE_ORE);
        blockWithItem(ModBlocks.TECTONIC_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_TECTONIC_ORE);
    }

    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
