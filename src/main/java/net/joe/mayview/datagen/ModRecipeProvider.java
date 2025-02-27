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

    protected static void oreSmelting(@NotNull RecipeOutput recipeOutput, List<ItemLike> ingredients, @NotNull RecipeCategory category, @NotNull ItemLike result,
                                      float experience, int cookingTime, @NotNull String group) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, ingredients, category, result,
                experience, cookingTime, group, "_from_smelting");
    }

    protected static void oreBlasting(@NotNull RecipeOutput recipeOutput, List<ItemLike> ingredients, @NotNull RecipeCategory category, @NotNull ItemLike result,
                                      float experience, int cookingTime, @NotNull String group) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, ingredients, category, result,
                experience, cookingTime, group, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(@NotNull RecipeOutput recipeOutput, RecipeSerializer<T> cookingSerializer, AbstractCookingRecipe.@NotNull Factory<T> factory,
                                                                       List<ItemLike> ingredients, @NotNull RecipeCategory category, @NotNull ItemLike result, float experience, int cookingTime, @NotNull String group, String recipeName) {
        for (ItemLike itemlike : ingredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), category, result, experience, cookingTime, cookingSerializer, factory).group(group).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, Mayview.MOD_ID + ":" + getItemName(result) + recipeName + "_" + getItemName(itemlike));
        }
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        List<ItemLike> MORPHITE_SMELTABLES = List.of(ModItems.RAW_MORPHITE, ModBlocks.MORPHITE_ORE, ModBlocks.DEEPSLATE_MORPHITE_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HONEY_DIAMOND_SWORD.get(), 1)
                .pattern("#")
                .pattern("#")
                .pattern("S")
                .define('#', ModItems.HONEY_DIAMOND.get())
                .define('S', Items.STICK)
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.HONEY_DIAMOND_PICKAXE.get(), 1)
                .pattern("###")
                .pattern(" S ")
                .pattern(" S ")
                .define('#', ModItems.HONEY_DIAMOND.get())
                .define('S', Items.STICK)
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HONEY_DIAMOND_AXE.get(), 1)
                .pattern("##")
                .pattern("#S")
                .pattern(" S")
                .define('#', ModItems.HONEY_DIAMOND.get())
                .define('S', Items.STICK)
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HONEY_DIAMOND_SHOVEL.get(), 1)
                .pattern("#")
                .pattern("S")
                .pattern("S")
                .define('#', ModItems.HONEY_DIAMOND.get())
                .define('S', Items.STICK)
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HONEY_DIAMOND_HOE.get(), 1)
                .pattern("##")
                .pattern(" S")
                .pattern(" S")
                .define('#', ModItems.HONEY_DIAMOND.get())
                .define('S', Items.STICK)
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HONEY_DIAMOND_HELMET.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .define('#', ModItems.HONEY_DIAMOND.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HONEY_DIAMOND_CHESTPLATE.get(), 1)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.HONEY_DIAMOND.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HONEY_DIAMOND_LEGGINGS.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.HONEY_DIAMOND.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HONEY_DIAMOND_BOOTS.get(), 1)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.HONEY_DIAMOND.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.SPECTRITE_HELMET.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .define('#', ModItems.SPECTRITE_INGOT.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.SPECTRITE_CHESTPLATE.get(), 1)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.SPECTRITE_INGOT.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.SPECTRITE_LEGGINGS.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.SPECTRITE_INGOT.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.SPECTRITE_BOOTS.get(), 1)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.SPECTRITE_INGOT.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.ECOLITE_HELMET.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .define('#', ModItems.ECOLITE_INGOT.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.ECOLITE_CHESTPLATE.get(), 1)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.ECOLITE_INGOT.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.ECOLITE_LEGGINGS.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.ECOLITE_INGOT.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.ECOLITE_BOOTS.get(), 1)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.ECOLITE_INGOT.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.TECTRITE_HELMET.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .define('#', ModItems.TECTRITE_INGOT.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.TECTRITE_CHESTPLATE.get(), 1)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.TECTRITE_INGOT.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.TECTRITE_LEGGINGS.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.TECTRITE_INGOT.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.TECTRITE_BOOTS.get(), 1)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.TECTRITE_INGOT.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HYDRITE_HELMET.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .define('#', ModItems.HYDRITE_INGOT.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HYDRITE_CHESTPLATE.get(), 1)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.HYDRITE_INGOT.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HYDRITE_LEGGINGS.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.HYDRITE_INGOT.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HYDRITE_BOOTS.get(), 1)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.HYDRITE_INGOT.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PETRAFITE_HELMET.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .define('#', ModItems.PETRAFITE_INGOT.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PETRAFITE_CHESTPLATE.get(), 1)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.PETRAFITE_INGOT.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PETRAFITE_LEGGINGS.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.PETRAFITE_INGOT.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PETRAFITE_BOOTS.get(), 1)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItems.PETRAFITE_INGOT.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.COPPER_COIN.get(), 8)
                .requires(ModItems.IRON_COIN.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.IRON_COIN.get(), 8)
                .requires(ModItems.GOLD_COIN.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput, "iron_coin_from_gold_coin");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GOLD_COIN.get(), 8)
                .requires(ModItems.DIAMOND_COIN.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput, "gold_coin_from_diamond_coin");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.IRON_COIN.get(), 1)
                .requires(ModItems.COPPER_COIN.get())
                .requires(ModItems.COPPER_COIN.get())
                .requires(ModItems.COPPER_COIN.get())
                .requires(ModItems.COPPER_COIN.get())
                .requires(ModItems.COPPER_COIN.get())
                .requires(ModItems.COPPER_COIN.get())
                .requires(ModItems.COPPER_COIN.get())
                .requires(ModItems.COPPER_COIN.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GOLD_COIN.get(), 1)
                .requires(ModItems.IRON_COIN.get())
                .requires(ModItems.IRON_COIN.get())
                .requires(ModItems.IRON_COIN.get())
                .requires(ModItems.IRON_COIN.get())
                .requires(ModItems.IRON_COIN.get())
                .requires(ModItems.IRON_COIN.get())
                .requires(ModItems.IRON_COIN.get())
                .requires(ModItems.IRON_COIN.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DIAMOND_COIN.get(), 1)
                .requires(ModItems.GOLD_COIN.get())
                .requires(ModItems.GOLD_COIN.get())
                .requires(ModItems.GOLD_COIN.get())
                .requires(ModItems.GOLD_COIN.get())
                .requires(ModItems.GOLD_COIN.get())
                .requires(ModItems.GOLD_COIN.get())
                .requires(ModItems.GOLD_COIN.get())
                .requires(ModItems.GOLD_COIN.get())
                .unlockedBy("has_honey_diamond", has(ModItems.HONEY_DIAMOND.get()))
                .save(recipeOutput);

        oreSmelting(recipeOutput, MORPHITE_SMELTABLES, RecipeCategory.MISC, ModItems.RAW_MORPHITE.get(), 0.25f, 200, "morphite");
        oreBlasting(recipeOutput, MORPHITE_SMELTABLES, RecipeCategory.MISC, ModItems.RAW_MORPHITE.get(), 0.25f, 100, "morphite");
    }
}
