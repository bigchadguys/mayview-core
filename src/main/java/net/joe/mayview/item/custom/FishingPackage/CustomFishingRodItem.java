package net.joe.mayview.item.custom.FishingPackage;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomFishingRodItem extends FishingRodItem {
    private final int rows;
    private final int cols;

    public CustomFishingRodItem(int totalHooks, Properties properties) {
        super(properties);
        int sqrtValue = (int) Math.sqrt(totalHooks);

        int bestRows = 1;
        int bestCols = totalHooks;
        for (int i = sqrtValue; i > 0; i--) {
            if (totalHooks % i == 0) {
                bestRows = i;
                bestCols = totalHooks / i;
                break;
            }
        }

        this.rows = bestRows;
        this.cols = bestCols;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        List<CustomFishingHook> validHooks = FishingHookRegistry.getValidHooks(player);
        if (!validHooks.isEmpty()) {
            if (!level.isClientSide) {
                int totalDamage = FishingHookRegistry.retrieveAllHooks(player, itemstack);
                ItemStack original = itemstack.copy();
                itemstack.hurtAndBreak(totalDamage, player, LivingEntity.getSlotForHand(hand));
                if (itemstack.isEmpty()) {
                    net.neoforged.neoforge.event.EventHooks.onPlayerDestroyItem(player, original, hand);
                }
                List<CustomFishingHook> remainingHooks = FishingHookRegistry.getValidHooks(player);
                player.fishing = remainingHooks.isEmpty() ? null : remainingHooks.getFirst();
            }
            level.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.FISHING_BOBBER_RETRIEVE,
                    SoundSource.NEUTRAL,
                    1.0F,
                    0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
            );
            player.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
        } else {
            FishingHookRegistry.clearHooks(player);
            level.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.FISHING_BOBBER_THROW,
                    SoundSource.NEUTRAL,
                    0.5F,
                    0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
            );
            if (level instanceof ServerLevel serverLevel) {
                int j = (int) (EnchantmentHelper.getFishingTimeReduction(serverLevel, itemstack, player) * 20.0F);
                int k = EnchantmentHelper.getFishingLuckBonus(serverLevel, itemstack, player);
                double spacing = 0.25;
                double totalWidth = (cols - 1) * spacing;
                double totalHeight = (rows - 1) * spacing;
                float yawRad = player.getYRot() * ((float) Math.PI / 180.0F);

                CustomFishingHook firstHook = null;
                for (int r = 0; r < rows; r++) {
                    for (int c = 0; c < cols; c++) {
                        double localX = c * spacing - totalWidth / 2.0;
                        double localZ = r * spacing - totalHeight / 2.0;
                        double offsetX = localX * Math.cos(yawRad) - localZ * Math.sin(yawRad);
                        double offsetZ = localX * Math.sin(yawRad) + localZ * Math.cos(yawRad);
                        CustomFishingHook hook = new CustomFishingHook(player, level, k, j, offsetX, offsetZ);
                        serverLevel.addFreshEntity(hook);
                        if (firstHook == null) {
                            firstHook = hook;
                        }
                    }
                }
                player.fishing = firstHook;
            }
            player.gameEvent(GameEvent.ITEM_INTERACT_START);
        }
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}
