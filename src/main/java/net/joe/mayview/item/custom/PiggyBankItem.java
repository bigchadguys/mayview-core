package net.joe.mayview.item.custom;

import net.joe.mayview.data.ModDataComponents;
import net.joe.mayview.screen.custom.PiggyBankMenu;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PiggyBankItem extends Item {
    public PiggyBankItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.PIG_AMBIENT, SoundSource.PLAYERS, 1.0F, 1.8F);

            SimpleContainer coinContainer = new SimpleContainer(4);

            CompoundTag coinData = itemStack.getComponents().get(ModDataComponents.COIN_CONTAINER.get());
            if (coinData != null && coinData.contains(ContainerHelper.TAG_ITEMS, Tag.TAG_LIST)) {
                ContainerHelper.loadAllItems(coinData, coinContainer.getItems(), level.registryAccess());
            }

            player.openMenu(new SimpleMenuProvider(
                    (id, inventory, p) -> new PiggyBankMenu(id, inventory, coinContainer, itemStack),
                    Component.translatable("container.piggy_bank")));
        }
        return InteractionResultHolder.success(itemStack);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context,
                                @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        CompoundTag coinData = stack.getComponents().get(ModDataComponents.COIN_CONTAINER.get());

        if (coinData != null && coinData.contains("Items", Tag.TAG_LIST)) {
            ListTag items = coinData.getList("Items", Tag.TAG_COMPOUND);
            int copper = 0, iron = 0, gold = 0, diamond = 0;

            for (Tag tag : items) {
                CompoundTag itemTag = (CompoundTag) tag;
                String itemId = itemTag.getString("id");
                int count = itemTag.getInt("count");

                switch (itemId) {
                    case "mayview:copper_coin" -> copper += count;
                    case "mayview:iron_coin"   -> iron += count;
                    case "mayview:gold_coin"   -> gold += count;
                    case "mayview:diamond_coin"-> diamond += count;
                }
            }
            if (diamond > 0)
                tooltipComponents.add(
                        Component.translatable("item.mayview.piggy_bank.tooltip.diamond", diamond)
                                .setStyle(Style.EMPTY.withColor(0x1f99ca))
                );
            if (gold > 0)
                tooltipComponents.add(
                        Component.translatable("item.mayview.piggy_bank.tooltip.gold", gold)
                                .setStyle(Style.EMPTY.withColor(0xd19944))
                );
            if (iron > 0)
                tooltipComponents.add(
                        Component.translatable("item.mayview.piggy_bank.tooltip.iron", iron)
                                .setStyle(Style.EMPTY.withColor(0x849599))
                );
            if (copper > 0)
                tooltipComponents.add(
                        Component.translatable("item.mayview.piggy_bank.tooltip.copper", copper)
                                .setStyle(Style.EMPTY.withColor(0xad5635))
                );
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
