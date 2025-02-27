package net.joe.mayview.item.custom;

import net.joe.mayview.item.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CopperCoinItem extends Item {

    public CopperCoinItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!world.isClientSide()) {
            int coinCount = itemStack.getCount();

            if (coinCount < 8) return InteractionResultHolder.success(itemStack);

            if (coinCount % 8 == 0) {
                itemStack.consume(itemStack.getCount(), player);
                player.addItem(new ItemStack(ModItems.IRON_COIN.get(), coinCount / 8));

            } else {
                int fullCoins = coinCount / 8;
                int leftoverCount = coinCount % 8;

                itemStack.consume(fullCoins * 8, player);
                player.addItem(new ItemStack(ModItems.IRON_COIN.get(), fullCoins));

                itemStack.setCount(leftoverCount);
            }
        }
        return InteractionResultHolder.success(itemStack);
    }
}
