package net.joe.mayview.item.custom.FishingPackage;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import java.util.*;

public class FishingHookRegistry {
    private static final Map<Player, List<CustomFishingHook>> HOOKS =
            Collections.synchronizedMap(new WeakHashMap<>());

    public static void addHook(Player player, CustomFishingHook hook) {
        HOOKS.computeIfAbsent(player, p -> Collections.synchronizedList(new ArrayList<>())).add(hook);
    }

    public static List<CustomFishingHook> getValidHooks(Player player) {
        List<CustomFishingHook> hooks = HOOKS.get(player);
        if (hooks == null || hooks.isEmpty()) {
            HOOKS.remove(player);
            return Collections.emptyList();
        }
        synchronized (hooks) {
            hooks.removeIf(hook -> hook == null || hook.isRemoved());
            if (hooks.isEmpty()) {
                HOOKS.remove(player);
                return Collections.emptyList();
            }
            return new ArrayList<>(hooks);
        }
    }

    public static void removeHook(Player player, CustomFishingHook hook) {
        List<CustomFishingHook> hooks = HOOKS.get(player);
        if (hooks != null) {
            synchronized (hooks) {
                hooks.remove(hook);
                if (hooks.isEmpty()) {
                    HOOKS.remove(player);
                }
            }
        }
    }

    public static int retrieveAllHooks(Player player, ItemStack stack) {
        List<CustomFishingHook> hooks = getValidHooks(player);
        if (hooks.isEmpty()) return 0;

        int totalDamage = 0;
        List<CustomFishingHook> toRemove = new ArrayList<>();

        for (CustomFishingHook hook : hooks) {
            totalDamage += hook.retrieve(stack);
            toRemove.add(hook);
        }

        List<CustomFishingHook> originalHooks = HOOKS.get(player);
        if (originalHooks != null) {
            synchronized (originalHooks) {
                originalHooks.removeAll(toRemove);
                if (originalHooks.isEmpty()) {
                    HOOKS.remove(player);
                }
            }
        }
        return totalDamage;
    }

    public static void clearHooks(Player player) {
        HOOKS.remove(player);
    }
}
