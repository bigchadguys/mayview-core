package net.joe.mayview.item.custom;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import org.jetbrains.annotations.NotNull;

public class ModEffectSwordItem extends SwordItem {
    private final Holder<MobEffect> effect;

    public ModEffectSwordItem(Tier pTier, Properties pProperties, Holder<MobEffect> effect) {
        super(pTier, pProperties);
        this.effect = effect;
    }

    @Override
    public boolean onLeftClickEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(effect, 400), player);
        }
        return super.onLeftClickEntity(stack, player, entity);
    }
}