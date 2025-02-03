package net.joe.mayview.block.entity;

import net.joe.mayview.Mayview;
import net.joe.mayview.block.ModBlocks;
import net.joe.mayview.block.entity.custom.MorphiteSynthesizerBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Mayview.MOD_ID);

    public static final Supplier<BlockEntityType<MorphiteSynthesizerBlockEntity>> MORPHITE_SYNTHESIZER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("morphite_synthesizer_block_entity", () -> BlockEntityType.Builder.of(
                    MorphiteSynthesizerBlockEntity::new, ModBlocks.MORPHITE_SYNTHESIZER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
