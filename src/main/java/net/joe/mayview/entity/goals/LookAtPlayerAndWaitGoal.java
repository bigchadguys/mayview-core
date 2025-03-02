package net.joe.mayview.entity.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;

import java.util.EnumSet;

public class LookAtPlayerAndWaitGoal extends LookAtPlayerGoal {
    private final int minLookTime;
    private final int maxLookTime;
    private int lookTime;

    public LookAtPlayerAndWaitGoal(Mob mob, Class<? extends LivingEntity> targetClass, float distance, float probability, int minLookTime, int maxLookTime) {
        super(mob, targetClass, distance, probability);
        this.setFlags(EnumSet.of(Goal.Flag.LOOK, Goal.Flag.MOVE));
        this.minLookTime = minLookTime;
        this.maxLookTime = maxLookTime;
    }

    @Override
    public void start() {
        lookTime = adjustedTickDelay(minLookTime + mob.getRandom().nextInt(maxLookTime - minLookTime));
        mob.getNavigation().setSpeedModifier(0.0f);
        super.start();
    }

    @Override
    public boolean canContinueToUse() {
        if (lookAt == null || !lookAt.isAlive()) {
            return false;
        }
        return mob.distanceToSqr(lookAt) <= 2 * (lookDistance * lookDistance) && lookTime > 0;
    }

    @Override
    public void tick() {
        if (lookAt != null && lookAt.isAlive()) {
            double eyeY = lookAt.getEyeY();
            mob.getLookControl().setLookAt(lookAt.getX(), eyeY, lookAt.getZ());
            lookTime--;
        }
    }
}
