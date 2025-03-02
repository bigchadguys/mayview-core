package net.joe.mayview.entity.custom;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import com.cobblemon.mod.common.api.events.battles.BattleVictoryEvent;
import com.cobblemon.mod.common.api.reactive.ObservableSubscription;
import com.cobblemon.mod.common.battles.BattleRegistry;
import com.gitlab.srcmc.rctapi.api.RCTApi;
import com.gitlab.srcmc.rctapi.api.ai.RCTBattleAI;
import com.gitlab.srcmc.rctapi.api.battle.BattleFormat;
import com.gitlab.srcmc.rctapi.api.battle.BattleRules;
import com.gitlab.srcmc.rctapi.api.models.PokemonModel;
import com.gitlab.srcmc.rctapi.api.models.TrainerModel;
import com.gitlab.srcmc.rctapi.api.trainer.Trainer;
import com.gitlab.srcmc.rctapi.api.trainer.TrainerNPC;
import com.gitlab.srcmc.rctapi.api.util.JTO;
import kotlin.Unit;
import net.joe.mayview.Mayview;
import net.joe.mayview.entity.goals.LookAtPlayerAndWaitGoal;
import net.joe.mayview.entity.goals.TrainerBattleGoal;
import net.joe.mayview.item.ModItems;
import net.joe.mayview.sound.ModSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RatTrainerEntity extends PathfinderMob implements Npc {
    private static final Random RANDOM = new Random();
    private final RCTApi RCT = RCTApi.getInstance(Mayview.MOD_ID);
    private final TrainerNPC ratTrainer;
    private final String trainerId;
    private int firstPokemonLevel;
    private int pokemonCount;
    private PokemonBattle battle;
    private ObservableSubscription<BattleVictoryEvent> battleVictorySubscription;

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState swimAnimationState = new AnimationState();
    private int swimAnimationTimeout = 0;

    public RatTrainerEntity(EntityType<? extends RatTrainerEntity> entityType, Level level) {
        super(entityType, level);
        this.trainerId = "rat_trainer_" + this.getUUID();
        TrainerModel model = createDefaultTrainerModel();
        this.ratTrainer = RCT.getTrainerRegistry().registerNPC(trainerId, model);
        this.ratTrainer.setEntity(this);
        this.setCustomName(Component.literal("Rat Trainer"));
        this.battleVictorySubscription = CobblemonEvents.BATTLE_VICTORY.subscribe(Priority.NORMAL, this::onBattleVictory);
    }

    private TrainerModel createDefaultTrainerModel() {
        int count = RANDOM.nextInt(6) + 1;
        this.pokemonCount = count;
        int firstLevel = RANDOM.nextInt(51) + 20;
        firstPokemonLevel = firstLevel;

        List<PokemonModel> pokemonList = new ArrayList<>(count);
        pokemonList.add(new PokemonModel("", "GENDERLESS", firstLevel, "", "", Set.of(), new PokemonModel.StatsModel(), new PokemonModel.StatsModel(), false, "", Set.of()));

        int lowerBound = Math.max(20, firstLevel - 10);
        int upperBound = Math.min(70, firstLevel + 10);

        for (int i = 1; i < count; i++) {
            int level = RANDOM.nextInt(upperBound - lowerBound + 1) + lowerBound;
            pokemonList.add(new PokemonModel("", "GENDERLESS", level, "", "", Set.of(), new PokemonModel.StatsModel(), new PokemonModel.StatsModel(), false, "", Set.of()));
        }

        return new TrainerModel(
                "Rat Trainer",
                JTO.of(RCTBattleAI::new),
                List.of(),
                pokemonList
        );
    }

    public boolean isInBattle() {
        return battle != null;
    }

    private void setBattle(PokemonBattle battle) {
        this.battle = battle;
    }

    @Override
    protected void registerGoals() {
        this.getNavigation().setCanFloat(true);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new TrainerBattleGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 0.5));
        this.goalSelector.addGoal(2, new LookAtPlayerAndWaitGoal(this, LivingEntity.class, 8.0F, 0.04F, 200, 400));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, LivingEntity.class, 8.0F));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.35));
    }

    public Unit onBattleVictory(BattleVictoryEvent event) {
        if (event.getLosers().stream().anyMatch(actor -> actor.getUuid().equals(this.getUUID()))) {
            this.die();
        }
        this.finishBattle();
        return Unit.INSTANCE;
    }

    public void die() {
        if (!this.level().isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) this.level();

            int coinCount = (int) Math.ceil((firstPokemonLevel * pokemonCount) / 4.0);
            ItemStack coins = new ItemStack(ModItems.COPPER_COIN.get(), coinCount);
            serverLevel.addFreshEntity(new ItemEntity(serverLevel, getX(), getY(), getZ(), coins));

            double x = getX(), y = getY(), z = getZ();
            for (int i = 0; i < 20; i++) {
                double offsetX = (RANDOM.nextDouble() - 0.5) * 1.5;
                double offsetY = RANDOM.nextDouble() * 1.5;
                double offsetZ = (RANDOM.nextDouble() - 0.5) * 1.5;
                serverLevel.sendParticles(ParticleTypes.LARGE_SMOKE, x + offsetX, y + offsetY, z + offsetZ, 1, 0, 0, 0, 0.1);
            }

            serverLevel.playSound(null, x, y, z, SoundEvents.GENERIC_EXPLODE, SoundSource.NEUTRAL, 0.6f, 2.0f);
            serverLevel.playSound(null, x, y, z, ModSounds.RAT_DEATH.get(), SoundSource.NEUTRAL, 1.0f, 1.0f);
            discard();
        }
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        return !isInBattle() && super.hurt(source, amount);
    }

    @Override
    public boolean isPushable() {
        return !isInBattle();
    }

    @Override
    public boolean isInvulnerableTo(@NotNull DamageSource source) {
        return isInBattle() || super.isInvulnerableTo(source);
    }

    public void startBattleWith(Player player) {
        Trainer playerTrainer = RCT.getTrainerRegistry().getById(player.getName().getString());
        RCT.getBattleManager().start(List.of(playerTrainer), List.of(ratTrainer), BattleFormat.GEN_9_SINGLES, new BattleRules());
        setBattle(BattleRegistry.INSTANCE.getBattleByParticipatingPlayerId(player.getUUID()));
    }

    public void finishBattle() {
        if (!this.level().isClientSide() && battle != null) {
            setTarget(null);
            RCT.getBattleManager().end(battle.getBattleId());
            battle = null;
            getNavigation().setSpeedModifier(0.35f);
            RCT.getTrainerRegistry().unregisterById(trainerId);
        }
    }

    @Override
    public void remove(@NotNull RemovalReason reason) {
        if (battleVictorySubscription != null) {
            battleVictorySubscription.unsubscribe();
            battleVictorySubscription = null;
        }
        RCT.getTrainerRegistry().unregisterById(trainerId);
        super.remove(reason);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.isInBattle() ? ModSounds.RAT_TRADE.get() : ModSounds.RAT_AMBIENT.get();
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
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        if (!this.level().isClientSide()) startBattleWith(player);
        return InteractionResult.sidedSuccess(false);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) updateAnimationStates();
    }

    private void updateAnimationStates() {
        if (--idleAnimationTimeout <= 0) {
            idleAnimationTimeout = 160;
            idleAnimationState.start(tickCount);
        }
        if (isInWaterOrBubble() && --swimAnimationTimeout <= 0) {
            swimAnimationTimeout = 20;
            swimAnimationState.start(tickCount);
        }
    }
}
