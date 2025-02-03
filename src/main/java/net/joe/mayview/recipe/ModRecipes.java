package net.joe.mayview.recipe;

import net.joe.mayview.Mayview;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Mayview.MOD_ID);

    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, Mayview.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<MorphiteSynthesizerRecipe>> MORPHITE_SYNTHESIZER_SERIALIZER =
            SERIALIZERS.register("morphite_synthesizing", MorphiteSynthesizerRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<MorphiteSynthesizerRecipe>> MORPHITE_SYNTHESIZER_TYPE =
            TYPES.register("morphite_synthesizing", () -> new RecipeType<MorphiteSynthesizerRecipe>() {
        @Override
        public String toString() {
            return "morphite_synthesizing";
        }
    });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
