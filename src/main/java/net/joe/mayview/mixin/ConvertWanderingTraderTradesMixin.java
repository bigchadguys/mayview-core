package net.joe.mayview.mixin;

import net.joe.mayview.item.ModItems;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mixin(WanderingTrader.class)
public class ConvertWanderingTraderTradesMixin {

    @Inject(method = "updateTrades", at = @At("TAIL"))
    private void modifyWanderingTraderTrades(CallbackInfo ci) {
        WanderingTrader trader = (WanderingTrader) (Object) this;
        MerchantOffers offers = trader.getOffers();
        mayview_core$transformOffers(offers);
    }

    @Unique
    private void mayview_core$transformOffers(MerchantOffers offers) {
        List<MerchantOffer> modifiedOffers = new ArrayList<>();
        for (MerchantOffer offer : offers) {
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
        offers.clear();
        offers.addAll(modifiedOffers);
    }
}
