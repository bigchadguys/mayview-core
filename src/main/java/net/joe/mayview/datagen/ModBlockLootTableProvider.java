package net.joe.mayview.datagen;

import net.joe.mayview.block.ModBlocks;
import net.joe.mayview.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        this.add(ModBlocks.MORPHITE_ORE.get(), block -> createOreDrop(ModBlocks.MORPHITE_ORE.get(), ModItems.RAW_MORPHITE.get()));
        this.add(ModBlocks.DEEPSLATE_MORPHITE_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_MORPHITE_ORE.get(), ModItems.RAW_MORPHITE.get()));

        dropSelf(ModBlocks.MORPHITE_SYNTHESIZER.get());

    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
