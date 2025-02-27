package net.joe.mayview.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.joe.mayview.Mayview;
import net.joe.mayview.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;


public class AddCoinModifier extends LootModifier {

    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Mayview.MOD_ID);

    public static final Supplier<MapCodec<AddCoinModifier>> ADD_COIN_MODIFIER =
            GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("add_coin_modifier", () -> AddCoinModifier.CODEC);

    public static final MapCodec<AddCoinModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst)
                    .and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(e -> e.item))
                    .apply(inst, AddCoinModifier::new)
    );

    private final Item item;

    public AddCoinModifier(LootItemCondition[] conditions, Item item) {
        super(conditions);
        this.item = item;
    }

    @Override
    public @NotNull MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(@NotNull ObjectArrayList<ItemStack> generatedLoot, @NotNull LootContext context) {
        for (int i = 0; i < generatedLoot.size(); i++) {
            ItemStack stack = generatedLoot.get(i);

            if (stack.getItem() == Items.EMERALD) {
                int multiplier = ThreadLocalRandom.current().nextInt(2, 5);
                Item replacementItem = ThreadLocalRandom.current().nextDouble() < 0.25 ? ModItems.COIN_POUCH.get() : this.item;
                generatedLoot.set(i, new ItemStack(replacementItem, stack.getCount() * multiplier));
            }
        }

        return generatedLoot;
    }

}
