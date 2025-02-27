package net.joe.mayview.block.entity.custom;

import net.joe.mayview.block.custom.MorphiteSynthesizerBlock;
import net.joe.mayview.block.entity.ModBlockEntities;
import net.joe.mayview.recipe.ModRecipes;
import net.joe.mayview.recipe.MorphiteSynthesizerRecipe;
import net.joe.mayview.recipe.MorphiteSynthesizerRecipeInput;
import net.joe.mayview.screen.custom.MorphiteSynthesizerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class MorphiteSynthesizerBlockEntity extends BlockEntity implements MenuProvider {

    private static final int CATALYST_SLOT = 0;
    private static final int MORPHITE_INGOT_SLOT = 1;
    private static final int UPGRADE_INGOT_SLOT = 2;
    private static final int OUTPUT_SLOT = 3;

    public final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            assert level != null;
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;

    public MorphiteSynthesizerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.MORPHITE_SYNTHESIZER_BLOCK_ENTITY.get(), pPos, pBlockState);
        this.data = createContainerData();
    }

    private ContainerData createContainerData() {
        return new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> progress;
                    case 1 -> maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> progress = pValue;
                    case 1 -> maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("blockentity.mayview.morphite_synthesizer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory playerInventory, @NotNull Player player) {
        return new MorphiteSynthesizerMenu(containerId, playerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        tag.putInt("morphite_synthesizer.progress", progress);
        tag.putInt("morphite_synthesizer.max_progress", maxProgress);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(tag, registries);
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("morphite_synthesizer.progress");
        maxProgress = tag.getInt("morphite_synthesizer.max_progress");
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }

        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    public void tick(Level level, BlockPos pPos, BlockState pState) {
        if (hasRecipe() && isOutputSlotEmptyOrReceivable()) {
            increaseCraftingProgress();
            level.setBlockAndUpdate(pPos, pState.setValue(MorphiteSynthesizerBlock.LIT, true));
            setChanged(level, pPos, pState);

            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }

        } else {
            resetProgress();
            level.setBlockAndUpdate(pPos, pState.setValue(MorphiteSynthesizerBlock.LIT, false));
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 400;
    }

    private void craftItem() {
        Optional<RecipeHolder<MorphiteSynthesizerRecipe>> recipe = getCurrentRecipe();
        if (recipe.isPresent()) {
            ItemStack output = recipe.get().value().output();
            itemHandler.extractItem(CATALYST_SLOT, 1, false);
            itemHandler.extractItem(MORPHITE_INGOT_SLOT, 1, false);
            itemHandler.extractItem(UPGRADE_INGOT_SLOT, 1, false);
            itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(output.getItem(),
                    itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + output.getCount()));
        }
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() < this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<MorphiteSynthesizerRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().getResultItem(null);
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeHolder<MorphiteSynthesizerRecipe>> getCurrentRecipe() {
        List<ItemStack> itemList = List.of(itemHandler.getStackInSlot(CATALYST_SLOT), itemHandler.getStackInSlot(MORPHITE_INGOT_SLOT), itemHandler.getStackInSlot(UPGRADE_INGOT_SLOT));

        assert this.level != null;
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.MORPHITE_SYNTHESIZER_TYPE.get(),
                        new MorphiteSynthesizerRecipeInput(itemList), level);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                itemHandler.getStackInSlot(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ? 64 : itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = itemHandler.getStackInSlot(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider registries) {
        return saveWithoutMetadata(registries);
    }
}
