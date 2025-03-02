package net.joe.mayview.item.custom.FishingPackage;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.joe.mayview.api.IFishingHook;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CustomFishingHook extends FishingHook {
    private boolean autoReeled = false;

    public CustomFishingHook(Player player, Level level, int luck, int lureSpeed, double offsetX, double offsetZ) {
        super(player, level, luck, lureSpeed);
        if (player.fishing != null) {
            FishingHookRegistry.removeHook(player, (CustomFishingHook) player.fishing);
        }
        player.fishing = this;
        FishingHookRegistry.addHook(player, this);
        this.moveTo(this.getX() + offsetX, this.getY(), this.getZ() + offsetZ, this.getYRot(), this.getXRot());
        this.setDeltaMovement(this.getDeltaMovement().add(offsetX * 0.2, 0, offsetZ * 0.2));
    }

    @Override
    public void setOwner(@Nullable Entity owner) {
        super.setOwner(owner);
        if (owner instanceof Player player) {
            FishingHookRegistry.removeHook(player, this);
            FishingHookRegistry.addHook(player, this);
        }
    }

    @Override
    public int retrieve(@NotNull ItemStack stack) {
        int result = super.retrieve(stack);
        Player owner = this.getPlayerOwner();
        if (owner != null) {
            FishingHookRegistry.removeHook(owner, this);
        }
        return result;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide && !this.autoReeled && isBiting()) {
            Player owner = this.getPlayerOwner();
            if (owner != null) {
                this.retrieve(owner.getMainHandItem());
                this.autoReeled = true;
            }
        }
    }

    private boolean isBiting() {
        return ((IFishingHook) this).mayview$getBitingState();
    }

}