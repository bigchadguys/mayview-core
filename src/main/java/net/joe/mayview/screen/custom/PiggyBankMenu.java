package net.joe.mayview.screen.custom;

import net.joe.mayview.data.ModDataComponents;
import net.joe.mayview.item.ModItems;
import net.joe.mayview.screen.ModMenuTypes;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.SimpleContainer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.ContainerHelper;
import org.jetbrains.annotations.NotNull;

public class PiggyBankMenu extends AbstractContainerMenu {
    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private static final int TE_INVENTORY_SLOT_COUNT = 4;

    private final SimpleContainer coinContainer;
    private final ItemStack piggyBankStack;

    public PiggyBankMenu(int containerId, Inventory playerInventory, FriendlyByteBuf buf) {
        this(containerId, playerInventory, new SimpleContainer(4), ItemStack.EMPTY);
    }

    public PiggyBankMenu(int containerId, Inventory playerInventory, SimpleContainer inventory, ItemStack piggyBankStack) {
        super(ModMenuTypes.PIGGY_BANK_MENU.get(), containerId);
        this.coinContainer = inventory;
        this.piggyBankStack = piggyBankStack;

        addPlayerHotbar(playerInventory);
        addPlayerInventory(playerInventory);

        this.addSlot(new DynamicRestrictedSlot(inventory, 0, 53, 12, ModItems.COPPER_COIN.get(), 99));
        this.addSlot(new DynamicRestrictedSlot(inventory, 1, 71, 12, ModItems.IRON_COIN.get(), 99));
        this.addSlot(new DynamicRestrictedSlot(inventory, 2, 89, 12, ModItems.GOLD_COIN.get(), 99));
        this.addSlot(new DynamicRestrictedSlot(inventory, 3, 107, 12, ModItems.DIAMOND_COIN.get(), 99));
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;

        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (pIndex < VANILLA_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            return ItemStack.EMPTY;
        }

        if (sourceStack.isEmpty()) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);

        compressCoins();
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int row = 0; row < PLAYER_INVENTORY_ROW_COUNT; ++row) {
            for (int col = 0; col < PLAYER_INVENTORY_COLUMN_COUNT; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * PLAYER_INVENTORY_COLUMN_COUNT + HOTBAR_SLOT_COUNT, 8 + col * 18, 40 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int col = 0; col < HOTBAR_SLOT_COUNT; ++col) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 98));
        }
    }

    private void compressCoins() {
        final int max = 99;
        int[] counts = new int[4];

        for (int i = 0; i < coinContainer.getContainerSize(); i++) {
            ItemStack stack = coinContainer.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() == ModItems.COPPER_COIN.get()) {
                    counts[0] += stack.getCount();
                } else if (stack.getItem() == ModItems.IRON_COIN.get()) {
                    counts[1] += stack.getCount();
                } else if (stack.getItem() == ModItems.GOLD_COIN.get()) {
                    counts[2] += stack.getCount();
                } else if (stack.getItem() == ModItems.DIAMOND_COIN.get()) {
                    counts[3] += stack.getCount();
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            int conversion = counts[i] / 8;
            int availableSpace = max - counts[i + 1];
            int toConvert = Math.min(conversion, availableSpace);
            counts[i] -= toConvert * 8;
            counts[i + 1] += toConvert;
        }

        for (int i = 0; i < coinContainer.getContainerSize(); i++) {
            coinContainer.setItem(i, ItemStack.EMPTY);
        }

        if (counts[0] > 0) coinContainer.setItem(0, new ItemStack(ModItems.COPPER_COIN.get(), counts[0]));
        if (counts[1] > 0) coinContainer.setItem(1, new ItemStack(ModItems.IRON_COIN.get(), counts[1]));
        if (counts[2] > 0) coinContainer.setItem(2, new ItemStack(ModItems.GOLD_COIN.get(), counts[2]));
        if (counts[3] > 0) coinContainer.setItem(3, new ItemStack(ModItems.DIAMOND_COIN.get(), counts[3]));
    }

    @Override
    public void removed(@NotNull Player playerIn) {
        super.removed(playerIn);
        if (!piggyBankStack.isEmpty() && coinContainer.getContainerSize() > 0) {
            CompoundTag coinData = new CompoundTag();
            ContainerHelper.saveAllItems(coinData, coinContainer.getItems(), playerIn.level().registryAccess());

            DataComponentPatch patch = DataComponentPatch.builder()
                    .set(ModDataComponents.COIN_CONTAINER.get(), coinData)
                    .build();

            piggyBankStack.applyComponentsAndValidate(patch);
        }
    }
}
