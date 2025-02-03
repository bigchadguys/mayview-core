package net.joe.mayview.datagen;

import net.joe.mayview.Mayview;
import net.joe.mayview.block.ModBlocks;
import net.joe.mayview.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput pRecipeOutput) {
        List<ItemLike> MORPHITE_SMELTABLES = List.of(ModItems.RAW_MORPHITE, ModBlocks.MORPHITE_ORE, ModBlocks.DEEPSLATE_MORPHITE_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HONEY_DIAMOND_SWORD.get(), 1)
                .pattern("#")
                .pattern("#")
                .pattern("S")
                .define('#', ModItems.HONEY_DIAMOND.get())
                .define('S', Items.STICK);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.HONEY_DIAMOND_PICKAXE.get(), 1)
                .pattern("###")
                .pattern(" S ")
                .pattern(" S ")
                .define('#', ModItems.HONEY_DIAMOND.get())
                .define('S', Items.STICK);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HONEY_DIAMOND_AXE.get(), 1)
                .pattern("##")
                .pattern("#S")
                .pattern(" S")
                .define('#', ModItems.HONEY_DIAMOND.get())
                .define('S', Items.STICK);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HONEY_DIAMOND_SHOVEL.get(), 1)
                .pattern("#")
                .pattern("S")
                .pattern("S")
                .define('#', ModItems.HONEY_DIAMOND.get())
                .define('S', Items.STICK);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HONEY_DIAMOND_HOE.get(), 1)
                .pattern("##")
                .pattern(" S")
                .pattern(" S")
                .define('#', ModItems.HONEY_DIAMOND.get())
                .define('S', Items.STICK);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HONEY_DIAMOND_HELMET.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .define('#', ModItems.HONEY_DIAMOND.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HONEY_DIAMOND_CHESTPLATE.get(), 1)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.HONEY_DIAMOND.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HONEY_DIAMOND_LEGGINGS.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.HONEY_DIAMOND.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HONEY_DIAMOND_BOOTS.get(), 1)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.HONEY_DIAMOND.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.SPECTRITE_HELMET.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .define('#', ModItems.SPECTRITE_INGOT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.SPECTRITE_CHESTPLATE.get(), 1)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.SPECTRITE_INGOT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.SPECTRITE_LEGGINGS.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.SPECTRITE_INGOT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.SPECTRITE_BOOTS.get(), 1)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.SPECTRITE_INGOT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.ECOLITE_HELMET.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .define('#', ModItems.ECOLITE_INGOT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.ECOLITE_CHESTPLATE.get(), 1)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.ECOLITE_INGOT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.ECOLITE_LEGGINGS.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.ECOLITE_INGOT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.ECOLITE_BOOTS.get(), 1)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.ECOLITE_INGOT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.TECTRITE_HELMET.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .define('#', ModItems.TECTRITE_INGOT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.TECTRITE_CHESTPLATE.get(), 1)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.TECTRITE_INGOT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.TECTRITE_LEGGINGS.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.TECTRITE_INGOT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.TECTRITE_BOOTS.get(), 1)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.TECTRITE_INGOT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HYDRITE_HELMET.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .define('#', ModItems.HYDRITE_INGOT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HYDRITE_CHESTPLATE.get(), 1)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.HYDRITE_INGOT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HYDRITE_LEGGINGS.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.HYDRITE_INGOT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HYDRITE_BOOTS.get(), 1)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.HYDRITE_INGOT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PETRAFITE_HELMET.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .define('#', ModItems.PETRAFITE_INGOT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PETRAFITE_CHESTPLATE.get(), 1)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.PETRAFITE_INGOT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PETRAFITE_LEGGINGS.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.PETRAFITE_INGOT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PETRAFITE_BOOTS.get(), 1)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.PETRAFITE_INGOT.get());

//        ShapedRecipeBuilder.shaped(RecipeCategory., ModItems..get(), 1)
//                .pattern("###")
//                .pattern("###")
//                .pattern("###")
//                .define('#', ModItems..get());
//        ShapedRecipeBuilder.shaped(RecipeCategory., ModItems..get(), 1)
//                .pattern("###")
//                .pattern("###")
//                .pattern("###")
//                .define('#', ModItems..get());
//        ShapedRecipeBuilder.shaped(RecipeCategory., ModItems..get(), 1)
//                .pattern("###")
//                .pattern("###")
//                .pattern("###")
//                .define('#', ModItems..get());
//        ShapedRecipeBuilder.shaped(RecipeCategory., ModItems..get(), 1)
//                .pattern("###")
//                .pattern("###")
//                .pattern("###")
//                .define('#', ModItems..get());
//        ShapedRecipeBuilder.shaped(RecipeCategory., ModItems..get(), 1)
//                .pattern("###")
//                .pattern("###")
//                .pattern("###")
//                .define('#', ModItems..get());


        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, ModItems.ECOLITE_LEGGINGS.get(), 1)
                .requires(ModBlocks.MORPHITE_ORE.get());

        oreSmelting(pRecipeOutput, MORPHITE_SMELTABLES, RecipeCategory.MISC, ModItems.RAW_MORPHITE.get(), 0.25f, 200, "morphite");
        oreBlasting(pRecipeOutput, MORPHITE_SMELTABLES, RecipeCategory.MISC, ModItems.RAW_MORPHITE.get(), 0.25f, 100, "morphite");
    }

    protected static void oreSmelting(@NotNull RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult,
                                      float pExperience, int pCookingTIme, @NotNull String pGroup) {
        Object SmeltingRecipe;
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }
    protected static void oreBlasting(@NotNull RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult,
                                      float pExperience, int pCookingTime, @NotNull String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }
    protected static <T extends AbstractCookingRecipe> void oreCooking(@NotNull RecipeOutput pRecipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.@NotNull Factory<T> factory,
                                                                       List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult, float pExperience, int pCookingTime, @NotNull String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, Mayview.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

}
