package net.joe.mayview.entity.goals;

import net.joe.mayview.entity.custom.RatTrainerEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import java.util.EnumSet;

public class TrainerBattleGoal extends LookAtPlayerGoal {
    private final RatTrainerEntity ratTrainer;
    private boolean lookAtPlayer;

    public TrainerBattleGoal(RatTrainerEntity ratTrainer) {
        super(ratTrainer, Mob.class, 8.0f, 1F, false);
        this.ratTrainer = ratTrainer;
        this.setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (ratTrainer.isInBattle()) {
            if (lookAtPlayer) {
                this.lookAt = this.mob.level().getNearestPlayer(this.lookAtContext, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
            } else {
                this.lookAt = this.mob.level().getNearestEntity(
                        this.mob.level().getEntitiesOfClass(this.lookAtType, this.mob.getBoundingBox().inflate(this.lookDistance, 3.0, this.lookDistance), e -> true),
                        this.lookAtContext, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
            }
            if (this.lookAt == null || this.mob.getRandom().nextFloat() < 0.02f) {
                this.lookAtPlayer = !this.lookAtPlayer;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return this.canUse();
    }

    @Override
    public void start() {
        this.ratTrainer.getNavigation().setSpeedModifier(0.0f);
    }

    @Override
    public void tick() {
        if (this.lookAt != null && this.lookAt.isAlive()) {
            this.mob.getLookControl().setLookAt(this.lookAt.getX(), this.lookAt.getEyeY(), this.lookAt.getZ());
        }
    }
}
