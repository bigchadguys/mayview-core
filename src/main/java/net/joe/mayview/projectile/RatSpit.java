package net.joe.mayview.projectile;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.LlamaSpit;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class RatSpit extends LlamaSpit {
    public RatSpit(EntityType<? extends LlamaSpit> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        super.onHitEntity(result);
        if (this.getOwner() instanceof LivingEntity livingentity) {
            Entity entity = result.getEntity();
            DamageSource damagesource = this.damageSources().spit(this, livingentity);
            if (entity.hurt(damagesource, 3.0F) && this.level() instanceof ServerLevel serverlevel) {
                EnchantmentHelper.doPostAttackEffects(serverlevel, entity, damagesource);
            }
        }
    }

}
