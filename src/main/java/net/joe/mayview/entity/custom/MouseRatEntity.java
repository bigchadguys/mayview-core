package net.joe.mayview.entity.custom;

import net.joe.mayview.item.ModItems;
import net.joe.mayview.projectile.RatSpit;
import net.joe.mayview.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.flag.FeatureFlags;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;

public class MouseRatEntity extends AbstractVillager implements RangedAttackMob {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState swimAnimationState = new AnimationState();
    private int swimAnimationTimeout = 0;

    boolean didSpit;

    @Nullable
    private BlockPos wanderTarget;
    private int despawnDelay;

    public MouseRatEntity(EntityType<? extends MouseRatEntity> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AbstractVillager.createLivingAttributes()
                .add(Attributes.FOLLOW_RANGE, 16.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 0.35f, 15, 0.5F));
        this.goalSelector.addGoal(1, new LookAtTradingPlayerGoal(this));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.35));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, false, entity -> entity instanceof Enemy));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    @Override
    public void performRangedAttack(@NotNull LivingEntity target, float distanceFactor) {
        spit(target);
    }

    private void spit(LivingEntity target) {
        RatSpit spit = new RatSpit(EntityType.LLAMA_SPIT, this.level());
        spit.setOwner(this);

        final float rad = this.yBodyRot * ((float) Math.PI / 180.0F);
        final float halfWidth = (this.getBbWidth() + 0.8F) * 0.5F;
        spit.setPos(
                this.getX() - halfWidth * Mth.sin(rad),
                this.getEyeY() - 0.3F,
                this.getZ() + halfWidth * Mth.cos(rad)
        );

        final double dx = target.getX() - this.getX();
        final double dy = target.getY(0.3333333333333333) - spit.getY();
        final double dz = target.getZ() - this.getZ();
        final double d3 = Math.sqrt(dx * dx + dz * dz) * 0.2F;
        spit.shoot(dx, dy + d3, dz, 2.0F, 0.0F);

        if (!this.isSilent()) {
            this.level().playSound(
                    null,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    SoundEvents.LLAMA_SPIT,
                    this.getSoundSource(),
                    1.0F,
                    2.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F
            );
        }
        this.level().addFreshEntity(spit);
        this.didSpit = true;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob otherParent) {
        return null;
    }

    @Override
    public boolean showProgressBar() {
        return false;
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!itemStack.is(ModItems.MOUSERAT_SPAWN_EGG) && this.isAlive() && !this.isTrading() && !this.isBaby()) {
            if (hand == InteractionHand.MAIN_HAND) {
                player.awardStat(Stats.TALKED_TO_VILLAGER);
            }
            if (!this.level().isClientSide()) {
                if (this.getOffers().isEmpty()) {
                    return InteractionResult.CONSUME;
                }
                this.setTradingPlayer(player);
                this.openTradingScreen(player, Objects.requireNonNull(this.getDisplayName()), 1);
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        return super.mobInteract(player, hand);
    }

    @Override
    protected void updateTrades() {
        if (this.level().enabledFeatures().contains(FeatureFlags.TRADE_REBALANCE)) {
            experimentalUpdateTrades();
        } else {
            VillagerTrades.ItemListing[] tradesTier1 = VillagerTrades.WANDERING_TRADER_TRADES.get(1);
            VillagerTrades.ItemListing[] tradesTier2 = VillagerTrades.WANDERING_TRADER_TRADES.get(2);
            if (tradesTier1 != null && tradesTier2 != null) {
                MerchantOffers offers = this.getOffers();
                this.addOffersFromItemListings(offers, tradesTier1, 5);
                int randomIndex = this.random.nextInt(tradesTier2.length);
                VillagerTrades.ItemListing tradeListing = tradesTier2[randomIndex];
                MerchantOffer offer = tradeListing.getOffer(this, this.random);
                if (offer != null) {
                    offers.add(offer);
                }
            }
        }
    }

    private void experimentalUpdateTrades() {
        MerchantOffers offers = this.getOffers();
        for (Pair<VillagerTrades.ItemListing[], Integer> pair : VillagerTrades.EXPERIMENTAL_WANDERING_TRADER_TRADES) {
            VillagerTrades.ItemListing[] tradeListings = pair.getLeft();
            this.addOffersFromItemListings(offers, tradeListings, pair.getRight());
        }
    }

    @Override
    protected void rewardTradeXp(MerchantOffer offer) {
        if (offer.shouldRewardExp()) {
            int exp = 3 + this.random.nextInt(4);
            this.level().addFreshEntity(new ExperienceOrb(this.level(), this.getX(), this.getY() + 0.5, this.getZ(), exp));
        }
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("DespawnDelay", this.despawnDelay);
        if (this.wanderTarget != null) {
            compound.put("wander_target", NbtUtils.writeBlockPos(this.wanderTarget));
        }
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("DespawnDelay", 99)) {
            this.despawnDelay = compound.getInt("DespawnDelay");
        }
        NbtUtils.readBlockPos(compound, "wander_target").ifPresent(target -> this.wanderTarget = target);
        this.setAge(Math.max(0, this.getAge()));
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    public void setDespawnDelay(int despawnDelay) {
        this.despawnDelay = despawnDelay;
    }

    public int getDespawnDelay() {
        return this.despawnDelay;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide) {
            maybeDespawn();
        }
    }

    private void maybeDespawn() {
        if (this.despawnDelay > 0 && !this.isTrading() && --this.despawnDelay == 0) {
            this.discard();
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.isTrading() ? ModSounds.RAT_TRADE.get() : ModSounds.RAT_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
        return ModSounds.RAT_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.RAT_DEATH.get();
    }

    @Override
    protected @NotNull SoundEvent getDrinkingSound(ItemStack stack) {
        return stack.is(Items.MILK_BUCKET)
                ? SoundEvents.WANDERING_TRADER_DRINK_MILK
                : SoundEvents.WANDERING_TRADER_DRINK_POTION;
    }

    @Override
    protected @NotNull SoundEvent getTradeUpdatedSound(boolean yesSound) {
        return yesSound ? ModSounds.RAT_YES.get() : ModSounds.RAT_NO.get();
    }

    @Override
    public @NotNull SoundEvent getNotifyTradeSound() {
        return ModSounds.RAT_YES.get();
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 160;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
        if (this.isInWaterOrBubble()) {
            if (this.swimAnimationTimeout <= 0) {
                this.swimAnimationTimeout = 20;
                this.swimAnimationState.start(this.tickCount);
            } else {
                --this.swimAnimationTimeout;
            }
        }
    }
}
