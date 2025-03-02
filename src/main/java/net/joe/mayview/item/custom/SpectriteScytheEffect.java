package net.joe.mayview.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.Vec3;
import java.util.HashSet;
import java.util.Set;

public class SpectriteScytheEffect {
    private static final float DAMAGE_AMOUNT = 0.20F;
    private static final int PARTICLE_COUNT = 10;
    private static final double OFFSET_RANGE = 0.5;
    private static final float PARTICLE_SPEED = 5.0f;
    private static final double EFFECT_RADIUS = 16.0;
    private static final int WEAKNESS_DURATION = 100;

    public static void spreadAttack(LivingEntity initialTarget, ServerPlayer attacker) {
        if (!(attacker.level() instanceof ServerLevel serverWorld)) return;

        Holder<net.minecraft.world.damagesource.DamageType> noKnockbackDamage = attacker.level().registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(DamageTypes.CACTUS);
        DamageSource source = new DamageSource(noKnockbackDamage, attacker);

        Set<LivingEntity> hitTargets = new HashSet<>();

        AABB searchArea = initialTarget.getBoundingBox().inflate(EFFECT_RADIUS, EFFECT_RADIUS, EFFECT_RADIUS);
        for (LivingEntity target : serverWorld.getEntitiesOfClass(LivingEntity.class, searchArea)) {
            if (target != attacker && target.isAlive() && isHostile(target) && attacker.hasLineOfSight(target)) {
                hitTargets.add(target);
            }
        }

        float totalDamageDealt = 0f;
        for (LivingEntity target : hitTargets) {
            if (target.hurt(source, DAMAGE_AMOUNT)) {
                totalDamageDealt += DAMAGE_AMOUNT;
                target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, WEAKNESS_DURATION, 0, false, true));
                spawnDrainParticles(serverWorld, target);
                spawnSpreadTrail(serverWorld, initialTarget, target);
            }
        }

        if (totalDamageDealt > 0) {
            attacker.heal(totalDamageDealt);
        }
    }

    private static boolean isHostile(LivingEntity entity) {
        return entity.getType().getCategory() == MobCategory.MONSTER;
    }

    private static void spawnDrainParticles(ServerLevel world, LivingEntity target) {
        Vec3 targetCenter = target.position().add(0, target.getBbHeight() / 2, 0);
        var random = world.getRandom();
        for (int i = 0; i < PARTICLE_COUNT; i++) {
            double offsetX = (random.nextDouble() * 2 - 1) * OFFSET_RANGE;
            double offsetY = (random.nextDouble() * 2 - 1) * OFFSET_RANGE;
            double offsetZ = (random.nextDouble() * 2 - 1) * OFFSET_RANGE;
            Vec3 particlePos = targetCenter.add(offsetX, offsetY, offsetZ);
            world.sendParticles(ParticleTypes.SCULK_SOUL, particlePos.x, particlePos.y, particlePos.z,
                    0, 0, 0, 0, PARTICLE_SPEED);
        }
    }

    private static void spawnSpreadTrail(ServerLevel world, LivingEntity from, LivingEntity to) {
        Vec3 start = from.position().add(0, from.getBbHeight() / 2, 0);
        Vec3 end = to.position().add(0, to.getBbHeight() / 2, 0);
        int segments = 16;

        for (int i = 0; i <= segments; i++) {
            double t = (double) i / segments;
            double x = start.x + (end.x - start.x) * t;
            double y = start.y + (end.y - start.y) * t;
            double z = start.z + (end.z - start.z) * t;
            world.sendParticles(ParticleTypes.SCULK_CHARGE_POP, x, y, z, 1, 0, 0, 0, 0);
        }
    }
}
