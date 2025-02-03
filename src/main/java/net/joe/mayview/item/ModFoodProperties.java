package net.joe.mayview.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties HONEY_DIAMOND = new FoodProperties.Builder().nutrition(10).saturationModifier(1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST), 0.65f).build();
}
