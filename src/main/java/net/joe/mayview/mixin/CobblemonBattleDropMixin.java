package net.joe.mayview.mixin;

import com.cobblemon.mod.common.api.drop.DropEntry;
import com.cobblemon.mod.common.api.drop.ItemDropEntry;
import com.cobblemon.mod.common.api.drop.DropTable;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.joe.mayview.Mayview;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import kotlin.ranges.IntRange;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mixin(DropTable.class)
public class CobblemonBattleDropMixin {
    @Inject(method = "getDrops", at = @At("RETURN"), cancellable = true)
    private void addCoinDrops(IntRange amount, Pokemon pokemon, CallbackInfoReturnable<List<DropEntry>> cir) {
        List<DropEntry> drops = new ArrayList<>(cir.getReturnValue());

        double roll = Math.random();
        if (roll < 0.0025) {
            drops.add(mayview_core$createDrop("gold_coin", 1));
        } else if (roll < 0.0475) {
            drops.add(mayview_core$createDrop("iron_coin", new Random().nextInt(2) + 1));
        } else {
            drops.add(mayview_core$createDrop("copper_coin", new Random().nextInt(3) + 1));
        }

        cir.setReturnValue(drops);
    }

    @Unique
    private static ItemDropEntry mayview_core$createDrop(String itemId, int quantity) {
        ItemDropEntry drop = new ItemDropEntry();
        drop.setItem(ResourceLocation.fromNamespaceAndPath(Mayview.MOD_ID, itemId));
        drop.setPercentage(100f);
        drop.setQuantity(quantity);
        return drop;
    }
}
