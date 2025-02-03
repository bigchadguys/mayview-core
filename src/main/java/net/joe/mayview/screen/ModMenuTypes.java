package net.joe.mayview.screen;

import net.joe.mayview.Mayview;
import net.joe.mayview.screen.custom.MorphiteSynthesizerMenu;
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

    public static final DeferredHolder<MenuType<?>, MenuType<MorphiteSynthesizerMenu>> MORPHITE_SYNTHESIZER_MENU =
            registerMenuType(MorphiteSynthesizerMenu::new);

    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(IContainerFactory<T> factory) {
        return MENUS.register("morphite_synthesizer_menu", () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
