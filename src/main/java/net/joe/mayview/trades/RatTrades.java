package net.joe.mayview.trades;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.joe.mayview.item.ModItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class RatTrades {
    public static final Int2ObjectMap<RatTrades.ItemListing[]> RAT_TRADES = toIntMap(
            ImmutableMap.of(
                    1,
                    new RatTrades.ItemListing[]{
                            new RatTrades.CoinsForItems(Items.WHEAT, 20, 16, 2),
                            new RatTrades.ItemsForCoins(new ItemStack(ModItems.HONEY_DIAMOND.get()), 20, 10, 16, 2, 0.05F),

                    }
            )
    );
    private static Int2ObjectMap<RatTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, RatTrades.ItemListing[]> map) {
        return new Int2ObjectOpenHashMap<>(map);
    }

    public interface ItemListing {
        @Nullable
        MerchantOffer getOffer(Entity trader, RandomSource random);
    }

    static class CoinsForItems implements RatTrades.ItemListing {
        private final ItemCost itemStack;
        private final int maxUses;
        private final int villagerXp;
        private final int coinAmount;
        private final float priceMultiplier;

        public CoinsForItems(ItemLike item, int cost, int maxUses, int villagerXp) {
            this(item, cost, maxUses, villagerXp, 1);
        }

        public CoinsForItems(ItemLike item, int cost, int maxUses, int villagerXp, int coinAmount) {
            this(new ItemCost(item.asItem(), cost), maxUses, villagerXp, coinAmount);
        }

        public CoinsForItems(ItemCost itemStack, int maxUses, int villagerXp, int coinAmount) {
            this.itemStack = itemStack;
            this.maxUses = maxUses;
            this.villagerXp = villagerXp;
            this.coinAmount = coinAmount;
            this.priceMultiplier = 0.05F;
        }

        @Override
        public MerchantOffer getOffer(Entity trader, RandomSource random) {
            return new MerchantOffer(this.itemStack, new ItemStack(ModItems.COPPER_COIN.get(), this.coinAmount), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class ItemsForCoins  implements RatTrades.ItemListing {
        private final ItemStack itemStack;
        private final int coinCost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForCoins (ItemStack itemStack, int coinCost, int numberOfItems, int maxUses, int villagerXp) {
            this(itemStack, coinCost, numberOfItems, maxUses, villagerXp, 0.05F);
        }

        public ItemsForCoins (
                ItemStack itemStack,
                int coinCost,
                int numberOfItems,
                int maxUses,
                int villagerXp,
                float priceMultiplier
        ) {
            this.itemStack = itemStack;
            this.coinCost = coinCost;
            this.itemStack.setCount(numberOfItems);
            this.maxUses = maxUses;
            this.villagerXp = villagerXp;
            this.priceMultiplier = priceMultiplier;
        }

        @Override
        public MerchantOffer getOffer(Entity trader, RandomSource random) {
            ItemStack itemstack = this.itemStack.copy();
            Level level = trader.level();
            return new MerchantOffer(new ItemCost(ModItems.COPPER_COIN, this.coinCost), itemstack, this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }
}
