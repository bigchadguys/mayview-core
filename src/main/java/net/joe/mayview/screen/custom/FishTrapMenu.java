package net.joe.mayview.screen.custom;

import net.joe.mayview.block.ModBlocks;
import net.joe.mayview.block.entity.custom.FishTrapBlockEntity;
import net.joe.mayview.screen.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class FishTrapMenu extends AbstractContainerMenu {
    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private static final int TE_INVENTORY_SLOT_COUNT = 10;
    public final FishTrapBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;
    public FishTrapMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }
    public FishTrapMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.FISH_TRAP_MENU.get(), pContainerId);
        blockEntity = ((FishTrapBlockEntity) entity);
        this.level = inv.player.level();
        this.data = data;

        addPlayerHotbar(inv);
        addPlayerInventory(inv);

        this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 0, 80, 51));
        this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 1, 8, 19));
        this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 2, 26, 19));
        this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 3, 44, 19));
        this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 4, 62, 19));
        this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 5, 80, 19));
        this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 6, 98, 19));
        this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 7, 116, 19));
        this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 8, 134, 19));
        this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 9, 152, 19));

        addDataSlots(data);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            return ItemStack.EMPTY;
        }
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.FISH_TRAP.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}