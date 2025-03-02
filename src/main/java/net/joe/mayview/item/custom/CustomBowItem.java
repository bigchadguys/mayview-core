package net.joe.mayview.item.custom;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CustomBowItem extends BowItem {
    private final int arrowCount;
    private static final ResourceKey<Registry<Enchantment>> ENCHANTMENT_REGISTRY_KEY =
            ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath("minecraft", "enchantment"));

    public CustomBowItem(Properties properties, int arrowCount) {
        super(properties);
        this.arrowCount = Math.max(1, arrowCount);
    }

    @Override
    public void releaseUsing(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity shooter, int timeLeft) {
        if (!(shooter instanceof Player player) || !(level instanceof ServerLevel serverLevel)) return;

        int charge = getUseDuration(stack, shooter) - timeLeft;
        float power = getPowerForTime(charge);
        if (power < 0.1F) return;

        ItemStack mainHandItem = player.getMainHandItem();

        Registry<Enchantment> reg = shooter.level().registryAccess().registryOrThrow(ENCHANTMENT_REGISTRY_KEY);
        Holder<Enchantment> infinityHolder = Holder.direct(Objects.requireNonNull(reg.get(Enchantments.INFINITY.location())));
        boolean infinity = EnchantmentHelper.getTagEnchantmentLevel(infinityHolder, mainHandItem) > 0;

        float totalSpread = 15.0F;
        float angleStep = (arrowCount > 1) ? totalSpread / (arrowCount - 1) : 0F;
        float startAngle = -totalSpread / 2;

        double shooterX = shooter.getX();
        double shooterY = shooter.getEyeY() - 0.1;
        double shooterZ = shooter.getZ();
        float shooterXRot = shooter.getXRot();
        float shooterYRot = shooter.getYRot();
        float arrowSpeed = power * 3.0F; // Cached arrow speed factor

        for (int i = 0; i < arrowCount; i++) {
            float spread = startAngle + (i * angleStep);
            AbstractArrow arrow = createArrow(serverLevel, shooter);
            arrow.setPos(shooterX, shooterY, shooterZ);
            arrow.shootFromRotation(shooter, shooterXRot, shooterYRot + spread, 0.0F, arrowSpeed, 1.0F);
            arrow.setCritArrow(power == 1.0F);
            arrow.pickup = infinity ? AbstractArrow.Pickup.DISALLOWED : (i == 0 ? AbstractArrow.Pickup.ALLOWED : AbstractArrow.Pickup.DISALLOWED);
            serverLevel.addFreshEntity(arrow);
        }
        level.playSound(null, shooterX, shooterY, shooterZ,
                net.minecraft.sounds.SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);
    }

    private AbstractArrow createArrow(ServerLevel level, LivingEntity shooter) {
        Arrow newArrow = new Arrow(EntityType.ARROW, level);
        newArrow.setOwner(shooter);
        newArrow.pickup = AbstractArrow.Pickup.DISALLOWED;
        return newArrow;
    }
}
