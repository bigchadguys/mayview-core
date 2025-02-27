package net.joe.mayview.datagen;

import net.joe.mayview.Mayview;
import net.joe.mayview.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Mayview.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.MORPHITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_MORPHITE_ORE.get())
                .add(ModBlocks.TECTONIC_ORE.get())
                .add(ModBlocks.DEEPSLATE_TECTONIC_ORE.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.MORPHITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_MORPHITE_ORE.get())
                .add(ModBlocks.TECTONIC_ORE.get())
                .add(ModBlocks.DEEPSLATE_TECTONIC_ORE.get());
    }
}
