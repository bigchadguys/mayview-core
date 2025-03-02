package net.joe.mayview.mixin;

import net.joe.mayview.api.IFishingHook;
import net.minecraft.world.entity.projectile.FishingHook;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FishingHook.class)
public abstract class FishingHookMixin implements IFishingHook {
    @Shadow private boolean biting;
    @Final
    @Shadow private int luck;

    @Override
    public boolean mayview$getBitingState() {
        return this.biting;
    }

    @Override
    public int mayview$getLuckValue() {
        return this.luck;
    }
}
