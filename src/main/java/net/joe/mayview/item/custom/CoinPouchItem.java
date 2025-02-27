package net.joe.mayview.item.custom;

import net.joe.mayview.item.ModItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;


public class CoinPouchItem extends Item {

    public CoinPouchItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!world.isClientSide()) {
            itemStack.consume(1, player);
            player.addItem(new ItemStack(ModItems.COPPER_COIN.get(), RandomSource.create().nextInt(5) + 2));

        }
        return InteractionResultHolder.success(itemStack);
    }



}
