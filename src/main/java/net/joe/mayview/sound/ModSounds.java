package net.joe.mayview.sound;

import net.joe.mayview.Mayview;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Mayview.MOD_ID);

    public static final Supplier<SoundEvent> RAT_DEATH = registerSoundEvent("rat_death");
    public static final Supplier<SoundEvent> RAT_AMBIENT = registerSoundEvent("rat_ambient");
    public static final Supplier<SoundEvent> RAT_HURT = registerSoundEvent("rat_hurt");
    public static final Supplier<SoundEvent> RAT_YES = registerSoundEvent("rat_yes");
    public static final Supplier<SoundEvent> RAT_NO = registerSoundEvent("rat_no");
    public static final Supplier<SoundEvent> RAT_TRADE = registerSoundEvent("rat_trade");
    public static final Supplier<SoundEvent> RAT_APPEAR = registerSoundEvent("rat_appear");
    public static final Supplier<SoundEvent> RAT_DISAPPEAR = registerSoundEvent("rat_disappear");


    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Mayview.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
