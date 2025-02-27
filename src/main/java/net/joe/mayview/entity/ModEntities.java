package net.joe.mayview.entity;

import net.joe.mayview.Mayview;
import net.joe.mayview.entity.custom.MouseRatEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Mayview.MOD_ID);

    public static final Supplier<EntityType<MouseRatEntity>> MOUSERAT =
            ENTITY_TYPES.register("mouserat", () -> EntityType.Builder.of(MouseRatEntity::new, MobCategory.CREATURE)
                    .sized(0.5f, 1f).build("mouserat"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
