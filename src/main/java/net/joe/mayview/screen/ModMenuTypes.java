package net.joe.mayview.screen;

import net.joe.mayview.Mayview;
import net.joe.mayview.screen.custom.FishTrapMenu;
import net.joe.mayview.screen.custom.MorphiteSynthesizerMenu;
import net.joe.mayview.screen.custom.PiggyBankMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, Mayview.MOD_ID);

    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static final DeferredHolder<MenuType<?>, MenuType<MorphiteSynthesizerMenu>> MORPHITE_SYNTHESIZER_MENU =
            registerMenuType("morphite_synthesizer_menu", MorphiteSynthesizerMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<FishTrapMenu>> FISH_TRAP_MENU =
            registerMenuType("fish_trap_menu", FishTrapMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<PiggyBankMenu>> PIGGY_BANK_MENU =
            registerMenuType("piggy_bank_menu", PiggyBankMenu::new);

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
