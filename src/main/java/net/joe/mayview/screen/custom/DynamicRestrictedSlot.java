package net.joe.mayview.screen.custom;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class DynamicRestrictedSlot extends Slot {
    private final Predicate<ItemStack> allowedPredicate;
    private final int maxStackSize;

    public DynamicRestrictedSlot(net.minecraft.world.Container container, int index, int x, int y,
                                 Predicate<ItemStack> allowedPredicate, int maxStackSize) {
        super(container, index, x, y);
        this.allowedPredicate = allowedPredicate;
        this.maxStackSize = maxStackSize;
    }

    public DynamicRestrictedSlot(net.minecraft.world.Container container, int index, int x, int y,
                                 Item allowedItem, int maxStackSize) {
        this(container, index, x, y, stack -> stack.getItem() == allowedItem, maxStackSize);
    }

    public DynamicRestrictedSlot(net.minecraft.world.Container container, int index, int x, int y, Item allowedItem) {
        this(container, index, x, y, allowedItem, 64);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return allowedPredicate.test(stack);
    }

    @Override
    public int getMaxStackSize() {
        return maxStackSize;
    }
}
