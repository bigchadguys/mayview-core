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
        basicItem(ModItems.WORM.get());
        basicItem(ModItems.GUMMY_WORM.get());
        basicItem(ModItems.JOE_COIN.get());
        basicItem(ModItems.PIGGY_BANK.get());
        basicItem(ModItems.RAW_MORPHITE.get());
        basicItem(ModItems.MORPHITE_INGOT.get());
        basicItem(ModItems.RAW_ENDERITE.get());
        basicItem(ModItems.ENDERITE_INGOT.get());
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
        basicItem(ModItems.QUICKSILVER.get());
        basicItem(ModItems.SWIFTITE_INGOT.get());
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
        handheldItem(ModItems.COPPER_SWORD.get());
        handheldItem(ModItems.COPPER_PICKAXE.get());
        handheldItem(ModItems.COPPER_AXE.get());
        handheldItem(ModItems.COPPER_SHOVEL.get());
        handheldItem(ModItems.COPPER_HOE.get());
        handheldItem(ModItems.STEEL_SWORD.get());
        handheldItem(ModItems.STEEL_PICKAXE.get());
        handheldItem(ModItems.STEEL_AXE.get());
        handheldItem(ModItems.STEEL_SHOVEL.get());
        handheldItem(ModItems.STEEL_HOE.get());
        basicItem(ModItems.STEEL_INGOT.get());
        basicItem(ModItems.COPPER_HELMET.get());
        basicItem(ModItems.COPPER_CHESTPLATE.get());
        basicItem(ModItems.COPPER_LEGGINGS.get());
        basicItem(ModItems.COPPER_BOOTS.get());
        withExistingParent(ModItems.RAT_TRADER_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.RAT_TRAINER_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        handheldItem(ModItems.EMERALD_SWORD.get());
        handheldItem(ModItems.EMERALD_PICKAXE.get());
        handheldItem(ModItems.EMERALD_AXE.get());
        handheldItem(ModItems.EMERALD_SHOVEL.get());
        handheldItem(ModItems.EMERALD_HOE.get());
        basicItem(ModItems.STEEL_HELMET.get());
        basicItem(ModItems.STEEL_CHESTPLATE.get());
        basicItem(ModItems.STEEL_LEGGINGS.get());
        basicItem(ModItems.STEEL_BOOTS.get());
        basicItem(ModItems.EMERALD_HELMET.get());
        basicItem(ModItems.EMERALD_CHESTPLATE.get());
        basicItem(ModItems.EMERALD_LEGGINGS.get());
        basicItem(ModItems.EMERALD_BOOTS.get());
        handheldItem(ModItems.AMETHYST_SWORD.get());
        handheldItem(ModItems.AMETHYST_PICKAXE.get());
        handheldItem(ModItems.AMETHYST_AXE.get());
        handheldItem(ModItems.AMETHYST_SHOVEL.get());
        handheldItem(ModItems.AMETHYST_HOE.get());
        basicItem(ModItems.AMETHYST_HELMET.get());
        basicItem(ModItems.AMETHYST_CHESTPLATE.get());
        basicItem(ModItems.AMETHYST_LEGGINGS.get());
        basicItem(ModItems.AMETHYST_BOOTS.get());
        handheldItem(ModItems.SWIFTITE_SHOVEL.get());
        handheldItem(ModItems.SPECTRITE_SWORD.get());
        handheldItem(ModItems.TECTRITE_PICKAXE.get());
        handheldItem(ModItems.PETRAFITE_AXE.get());
        handheldItem(ModItems.ECOLITE_HOE.get());
        basicItem(ModItems.COIN_POUCH.get());
    }
}
