package net.joe.mayview.mixin;


import net.joe.mayview.item.ModItems;

import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.VillagerTrades;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Mixin(AbstractVillager.class)
public class ConvertVillagerTradesMixin {
    @Inject(method = "addOffersFromItemListings", at = @At("TAIL"))
    private void modifyMerchantOffers(MerchantOffers givenMerchantOffers, VillagerTrades.ItemListing[] newTrades, int maxNumbers, CallbackInfo ci) {
        List<MerchantOffer> modifiedOffers = new ArrayList<>();

        for (MerchantOffer offer : givenMerchantOffers) {
            ItemStack resultItem = offer.getResult();
            ItemCost baseCost = offer.getItemCostA();
            Optional<ItemCost> secondaryCost = offer.getItemCostB();

            if (resultItem.getItem() == Items.EMERALD) {
                resultItem = new ItemStack(ModItems.COPPER_COIN.get(), resultItem.getCount() * 4);
            }

            if (baseCost.itemStack().getItem() == Items.EMERALD) {
                int ironCoinCount = baseCost.count() * 4;
                baseCost = (ironCoinCount > 8)
                        ? new ItemCost(ModItems.GOLD_COIN.get(), (ironCoinCount + 7) / 8)
                        : new ItemCost(ModItems.IRON_COIN.get(), ironCoinCount);
            }

            MerchantOffer modifiedOffer = new MerchantOffer(
                    baseCost,
                    secondaryCost,
                    resultItem,
                    offer.getUses(),
                    offer.getMaxUses(),
                    offer.getXp(),
                    offer.getPriceMultiplier()
            );

            modifiedOffers.add(modifiedOffer);
        }

        givenMerchantOffers.clear();
        givenMerchantOffers.addAll(modifiedOffers);
    }
}
