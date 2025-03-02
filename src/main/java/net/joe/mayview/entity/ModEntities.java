package net.joe.mayview.entity;

import net.joe.mayview.Mayview;
import net.joe.mayview.entity.custom.RatTraderEntity;
import net.joe.mayview.entity.custom.RatTrainerEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Mayview.MOD_ID);

    public static final Supplier<EntityType<RatTraderEntity>> RAT_TRADER =
            ENTITY_TYPES.register("rat_trader", () -> EntityType.Builder.of(RatTraderEntity::new, MobCategory.CREATURE)
                    .sized(0.5f, 1f).build("rat_trader"));

    public static final Supplier<EntityType<RatTrainerEntity>> RAT_TRAINER =
            ENTITY_TYPES.register("rat_trainer", () -> EntityType.Builder.of(RatTrainerEntity::new, MobCategory.CREATURE)
                    .sized(0.5f, 1f).build("rat_trainer"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
