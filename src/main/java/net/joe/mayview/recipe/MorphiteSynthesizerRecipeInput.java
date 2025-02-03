package net.joe.mayview.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record MorphiteSynthesizerRecipeInput(List<ItemStack> input) implements RecipeInput {
    @Override
    public @NotNull ItemStack getItem(int pIndex) {
        return input.get(pIndex);
    }

    @Override
    public int size() {
        return 3;
    }
}
