package net.joe.mayview.datagen;

import net.joe.mayview.Mayview;
import net.joe.mayview.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Mayview.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.COPPER_COIN.get());
        basicItem(ModItems.IRON_COIN.get());
        basicItem(ModItems.GOLD_COIN.get());
        basicItem(ModItems.DIAMOND_COIN.get());
        basicItem(ModItems.JOE_COIN.get());

        basicItem(ModItems.RAW_MORPHITE.get());
        basicItem(ModItems.MORPHITE_INGOT.get());

        basicItem(ModItems.EVEROAK.get());
        basicItem(ModItems.PETRAFITE_INGOT.get());

        basicItem(ModItems.MUSH.get());
        basicItem(ModItems.ECOLITE_INGOT.get());

        basicItem(ModItems.MYSTERIOUS_BUBBLE.get());
        basicItem(ModItems.HYDRITE_INGOT.get());

        basicItem(ModItems.ECTOPLASM.get());
        basicItem(ModItems.SPECTRITE_INGOT.get());

        basicItem(ModItems.TECTONIC_SHARD.get());
        basicItem(ModItems.TECTRITE_INGOT.get());

        basicItem(ModItems.HONEY_DIAMOND.get());

        handheldItem(ModItems.HONEY_DIAMOND_SWORD.get());
        handheldItem(ModItems.HONEY_DIAMOND_PICKAXE.get());
        handheldItem(ModItems.HONEY_DIAMOND_AXE.get());
        handheldItem(ModItems.HONEY_DIAMOND_SHOVEL.get());
        handheldItem(ModItems.HONEY_DIAMOND_HOE.get());

        basicItem(ModItems.HONEY_DIAMOND_HELMET.get());
        basicItem(ModItems.HONEY_DIAMOND_CHESTPLATE.get());
        basicItem(ModItems.HONEY_DIAMOND_LEGGINGS.get());
        basicItem(ModItems.HONEY_DIAMOND_BOOTS.get());

//        handheldItem(ModItems.SPECTRITE_SWORD.get());
//        handheldItem(ModItems.TECTRITE_PICKAXE.get());
//        handheldItem(ModItems.PETRIFITE_AXE.get());
//        handheldItem(ModItems.ECOLITE_HOE.get());
    }
}
