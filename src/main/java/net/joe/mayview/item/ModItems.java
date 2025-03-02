package net.joe.mayview.item;

import net.joe.mayview.Mayview;
import net.joe.mayview.entity.ModEntities;
import net.joe.mayview.item.custom.*;
import net.joe.mayview.item.custom.FishingPackage.CustomFishingRodItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Mayview.MOD_ID);

    public static final DeferredItem<Item> RAW_MORPHITE = ITEMS.registerSimpleItem("raw_morphite");
    public static final DeferredItem<Item> MORPHITE_INGOT = ITEMS.registerSimpleItem("morphite_ingot");
    public static final DeferredItem<Item> RAW_ENDERITE = ITEMS.registerSimpleItem("raw_enderite");
    public static final DeferredItem<Item> ENDERITE_INGOT = ITEMS.registerSimpleItem("enderite_ingot");

    public static final DeferredItem<Item> ECTOPLASM = ITEMS.registerSimpleItem("ectoplasm");
    public static final DeferredItem<Item> SPECTRITE_INGOT = ITEMS.registerSimpleItem("spectrite_ingot");

    public static final DeferredItem<Item> MUSH = ITEMS.registerSimpleItem("mush");
    public static final DeferredItem<Item> ECOLITE_INGOT = ITEMS.registerSimpleItem("ecolite_ingot");

    public static final DeferredItem<Item> MYSTERIOUS_BUBBLE = ITEMS.registerSimpleItem("mysterious_bubble");
    public static final DeferredItem<Item> HYDRITE_INGOT = ITEMS.registerSimpleItem("hydrite_ingot");

    public static final DeferredItem<Item> TECTONIC_SHARD = ITEMS.registerSimpleItem("tectonic_shard");
    public static final DeferredItem<Item> TECTRITE_INGOT = ITEMS.registerSimpleItem("tectrite_ingot");

    public static final DeferredItem<Item> EVEROAK = ITEMS.registerSimpleItem("everoak");
    public static final DeferredItem<Item> PETRAFITE_INGOT = ITEMS.registerSimpleItem("petrafite_ingot");

    public static final DeferredItem<Item> QUICKSILVER = ITEMS.registerSimpleItem("quicksilver");
    public static final DeferredItem<Item> SWIFTITE_INGOT = ITEMS.registerSimpleItem("swiftite_ingot");

    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.registerSimpleItem("steel_ingot");

    public static final DeferredItem<Item> BEGINNER_FISHING_ROD = ITEMS.registerItem(
            "beginner_fishing_rod",
            properties -> new CustomFishingRodItem(2, new Item.Properties())
    );

    public static final DeferredItem<Item> INTERMEDIATE_FISHING_ROD = ITEMS.registerItem(
            "intermediate_fishing_rod",
            properties -> new CustomFishingRodItem(3, new Item.Properties())
    );

    public static final DeferredItem<Item> ADVANCED_FISHING_ROD = ITEMS.registerItem(
            "advanced_fishing_rod",
            properties -> new CustomFishingRodItem(4, new Item.Properties())
    );

    public static final DeferredItem<Item> EXPERT_FISHING_ROD = ITEMS.registerItem(
            "expert_fishing_rod",
            properties -> new CustomFishingRodItem(6, new Item.Properties())
    );

    public static final DeferredItem<Item> PROFESSIONAL_FISHING_ROD = ITEMS.registerItem(
            "professional_fishing_rod",
            properties -> new CustomFishingRodItem(9, new Item.Properties())
    );

    public static final DeferredItem<Item> HYDRITE_FISHING_ROD = ITEMS.registerItem(
            "hydrite_fishing_rod",
            properties -> new CustomFishingRodItem(12, new Item.Properties())
    );

    public static final DeferredItem<Item> REINFORCED_HYDRITE_FISHING_ROD = ITEMS.registerItem(
            "reinforced_hydrite_fishing_rod",
            properties -> new CustomFishingRodItem(16, new Item.Properties())
    );


    public static final DeferredItem<Item> CUSTOM_BOW = ITEMS.registerItem("custom_bow", properties -> new CustomBowItem(new Item.Properties(), 20));

    public static final DeferredItem<Item> COPPER_COIN = ITEMS.registerItem("copper_coin", Item::new, new Item.Properties().stacksTo(99));
    public static final DeferredItem<Item> IRON_COIN = ITEMS.registerItem("iron_coin", Item::new, new Item.Properties().stacksTo(99));
    public static final DeferredItem<Item> GOLD_COIN = ITEMS.registerItem("gold_coin", Item::new, new Item.Properties().stacksTo(99));
    public static final DeferredItem<Item> DIAMOND_COIN = ITEMS.registerItem("diamond_coin", Item::new, new Item.Properties().stacksTo(99));
    public static final DeferredItem<Item> WORM = ITEMS.registerSimpleItem("worm");
    public static final DeferredItem<Item> GUMMY_WORM = ITEMS.registerSimpleItem("gummy_worm");

    public static final DeferredItem<Item> JOE_COIN = ITEMS.registerSimpleItem("joe_coin");

    public static final DeferredItem<Item> COIN_POUCH = ITEMS.registerItem("coin_pouch", CoinPouchItem::new, new Item.Properties());

    public static final DeferredItem<Item> PIGGY_BANK = ITEMS.registerItem("piggy_bank", PiggyBankItem::new, new Item.Properties());

    public static final DeferredItem<Item> HONEY_DIAMOND = ITEMS.registerItem("honey_diamond", Item::new, new Item.Properties().food(ModFoodProperties.HONEY_DIAMOND));

    public static final DeferredItem<Item> HONEY_DIAMOND_SWORD = ITEMS.register("honey_diamond_sword",
            () -> new ModEffectSwordItem(ModToolTiers.HONEY_DIAMOND,
                    new Item.Properties()
                            .attributes(SwordItem.createAttributes(ModToolTiers.HONEY_DIAMOND, 3.0f, -2.4f)),
                    MobEffects.MOVEMENT_SLOWDOWN) {
                @Override
                public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.mayview.honey_diamond_sword.tooltip.1"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            }
    );

    public static final DeferredItem<Item> RAT_TRADER_SPAWN_EGG = ITEMS.register("rat_trader_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.RAT_TRADER, 0xdebd47, 0xdebd47, new Item.Properties()));

    public static final DeferredItem<Item> RAT_TRAINER_SPAWN_EGG = ITEMS.register("rat_trainer_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.RAT_TRAINER, 0xdebd47, 0xdebd47, new Item.Properties()));

    public static final DeferredItem<Item> HONEY_DIAMOND_PICKAXE = ITEMS.register("honey_diamond_pickaxe",
            () -> new PickaxeItem(ModToolTiers.HONEY_DIAMOND, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.HONEY_DIAMOND, 1.0f, -2.8f))));
    public static final DeferredItem<Item> HONEY_DIAMOND_AXE = ITEMS.register("honey_diamond_axe",
            () -> new AxeItem(ModToolTiers.HONEY_DIAMOND, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.HONEY_DIAMOND, 5.0f, -3.0f))));
    public static final DeferredItem<Item> HONEY_DIAMOND_SHOVEL = ITEMS.register("honey_diamond_shovel",
            () -> new ShovelItem(ModToolTiers.HONEY_DIAMOND, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.HONEY_DIAMOND, 1.5f, -3.0f))));
    public static final DeferredItem<Item> HONEY_DIAMOND_HOE = ITEMS.register("honey_diamond_hoe",
            () -> new HoeItem(ModToolTiers.HONEY_DIAMOND, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.HONEY_DIAMOND, -3.0f, 0.0f))));

    public static final DeferredItem<Item> HONEY_DIAMOND_HELMET = ITEMS.register("honey_diamond_helmet",
            () -> new ModArmorItem(ModArmorMaterials.HONEY_DIAMOND, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredItem<Item> HONEY_DIAMOND_CHESTPLATE = ITEMS.register("honey_diamond_chestplate",
            () -> new ArmorItem(ModArmorMaterials.HONEY_DIAMOND, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredItem<Item> HONEY_DIAMOND_LEGGINGS = ITEMS.register("honey_diamond_leggings",
            () -> new ArmorItem(ModArmorMaterials.HONEY_DIAMOND, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredItem<Item> HONEY_DIAMOND_BOOTS = ITEMS.register("honey_diamond_boots",
            () -> new ArmorItem(ModArmorMaterials.HONEY_DIAMOND, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));

    public static final DeferredItem<Item> COPPER_SWORD = ITEMS.register("copper_sword",
            () -> new SwordItem(ModToolTiers.COPPER, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.COPPER, 1.0f, -2.8f))));
    public static final DeferredItem<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe",
            () -> new PickaxeItem(ModToolTiers.COPPER, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.COPPER, 1.0f, -2.8f))));
    public static final DeferredItem<Item> COPPER_AXE = ITEMS.register("copper_axe",
            () -> new AxeItem(ModToolTiers.COPPER, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.COPPER, 5.0f, -3.0f))));
    public static final DeferredItem<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel",
            () -> new ShovelItem(ModToolTiers.COPPER, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.COPPER, 1.5f, -3.0f))));
    public static final DeferredItem<Item> COPPER_HOE = ITEMS.register("copper_hoe",
            () -> new HoeItem(ModToolTiers.COPPER, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.COPPER, -3.0f, 0.0f))));

    public static final DeferredItem<Item> COPPER_HELMET = ITEMS.register("copper_helmet",
            () -> new ModArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredItem<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate",
            () -> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredItem<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings",
            () -> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredItem<Item> COPPER_BOOTS = ITEMS.register("copper_boots",
            () -> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));

    public static final DeferredItem<Item> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(ModToolTiers.STEEL, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.STEEL, 1.0f, -2.8f))));
    public static final DeferredItem<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(ModToolTiers.STEEL, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.STEEL, 1.0f, -2.8f))));
    public static final DeferredItem<Item> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(ModToolTiers.STEEL, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.STEEL, 5.0f, -3.0f))));
    public static final DeferredItem<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            () -> new ShovelItem(ModToolTiers.STEEL, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.STEEL, 1.5f, -3.0f))));
    public static final DeferredItem<Item> STEEL_HOE = ITEMS.register("steel_hoe",
            () -> new HoeItem(ModToolTiers.STEEL, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.STEEL, -3.0f, 0.0f))));

    public static final DeferredItem<Item> STEEL_HELMET = ITEMS.register("steel_helmet",
            () -> new ModArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredItem<Item> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
            () -> new ArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredItem<Item> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
            () -> new ArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredItem<Item> STEEL_BOOTS = ITEMS.register("steel_boots",
            () -> new ArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));


    public static final DeferredItem<Item> AMETHYST_SWORD = ITEMS.register("amethyst_sword",
            () -> new SwordItem(ModToolTiers.AMETHYST, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.AMETHYST, 1.0f, -2.8f))));
    public static final DeferredItem<Item> AMETHYST_PICKAXE = ITEMS.register("amethyst_pickaxe",
            () -> new PickaxeItem(ModToolTiers.AMETHYST, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.AMETHYST, 1.0f, -2.8f))));
    public static final DeferredItem<Item> AMETHYST_AXE = ITEMS.register("amethyst_axe",
            () -> new AxeItem(ModToolTiers.AMETHYST, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.AMETHYST, 5.0f, -3.0f))));
    public static final DeferredItem<Item> AMETHYST_SHOVEL = ITEMS.register("amethyst_shovel",
            () -> new ShovelItem(ModToolTiers.AMETHYST, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.AMETHYST, 1.5f, -3.0f))));
    public static final DeferredItem<Item> AMETHYST_HOE = ITEMS.register("amethyst_hoe",
            () -> new HoeItem(ModToolTiers.AMETHYST, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.AMETHYST, -3.0f, 0.0f))));

    public static final DeferredItem<Item> AMETHYST_HELMET = ITEMS.register("amethyst_helmet",
            () -> new ModArmorItem(ModArmorMaterials.AMETHYST, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredItem<Item> AMETHYST_CHESTPLATE = ITEMS.register("amethyst_chestplate",
            () -> new ArmorItem(ModArmorMaterials.AMETHYST, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredItem<Item> AMETHYST_LEGGINGS = ITEMS.register("amethyst_leggings",
            () -> new ArmorItem(ModArmorMaterials.AMETHYST, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredItem<Item> AMETHYST_BOOTS = ITEMS.register("amethyst_boots",
            () -> new ArmorItem(ModArmorMaterials.AMETHYST, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));

    public static final DeferredItem<Item> EMERALD_SWORD = ITEMS.register("emerald_sword",
            () -> new SwordItem(ModToolTiers.EMERALD, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.EMERALD, 1.0f, -2.8f))));
    public static final DeferredItem<Item> EMERALD_PICKAXE = ITEMS.register("emerald_pickaxe",
            () -> new PickaxeItem(ModToolTiers.EMERALD, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.EMERALD, 1.0f, -2.8f))));
    public static final DeferredItem<Item> EMERALD_AXE = ITEMS.register("emerald_axe",
            () -> new AxeItem(ModToolTiers.EMERALD, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.EMERALD, 5.0f, -3.0f))));
    public static final DeferredItem<Item> EMERALD_SHOVEL = ITEMS.register("emerald_shovel",
            () -> new ShovelItem(ModToolTiers.EMERALD, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.EMERALD, 1.5f, -3.0f))));
    public static final DeferredItem<Item> EMERALD_HOE = ITEMS.register("emerald_hoe",
            () -> new HoeItem(ModToolTiers.EMERALD, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.EMERALD, -3.0f, 0.0f))));

    public static final DeferredItem<Item> EMERALD_HELMET = ITEMS.register("emerald_helmet",
            () -> new ModArmorItem(ModArmorMaterials.EMERALD, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredItem<Item> EMERALD_CHESTPLATE = ITEMS.register("emerald_chestplate",
            () -> new ArmorItem(ModArmorMaterials.EMERALD, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredItem<Item> EMERALD_LEGGINGS = ITEMS.register("emerald_leggings",
            () -> new ArmorItem(ModArmorMaterials.EMERALD, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredItem<Item> EMERALD_BOOTS = ITEMS.register("emerald_boots",
            () -> new ArmorItem(ModArmorMaterials.EMERALD, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));
    public static final DeferredItem<Item> SPECTRITE_SWORD = ITEMS.register("spectrite_sword",
            () -> new SpectriteScytheItem(ModToolTiers.SPECTRITE,
                    new Item.Properties()
                            .attributes(SwordItem.createAttributes(ModToolTiers.SPECTRITE, 3.0f, -2.4f))));

    public static final DeferredItem<Item> SPECTRITE_HELMET = ITEMS.register("spectrite_helmet",
            () -> new ModArmorItem(ModArmorMaterials.SPECTRITE, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredItem<Item> SPECTRITE_CHESTPLATE = ITEMS.register("spectrite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.SPECTRITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredItem<Item> SPECTRITE_LEGGINGS = ITEMS.register("spectrite_leggings",
            () -> new ArmorItem(ModArmorMaterials.SPECTRITE, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredItem<Item> SPECTRITE_BOOTS = ITEMS.register("spectrite_boots",
            () -> new ArmorItem(ModArmorMaterials.SPECTRITE, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));

    public static final DeferredItem<Item> ECOLITE_HOE = ITEMS.register("ecolite_hoe",
            () -> new EcoliteHoeItem(ModToolTiers.ECOLITE, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.ECOLITE, -3.0f, 0.0f))));

    public static final DeferredItem<Item> ECOLITE_HELMET = ITEMS.register("ecolite_helmet",
            () -> new ModArmorItem(ModArmorMaterials.ECOLITE, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredItem<Item> ECOLITE_CHESTPLATE = ITEMS.register("ecolite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ECOLITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredItem<Item> ECOLITE_LEGGINGS = ITEMS.register("ecolite_leggings",
            () -> new ArmorItem(ModArmorMaterials.ECOLITE, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredItem<Item> ECOLITE_BOOTS = ITEMS.register("ecolite_boots",
            () -> new ArmorItem(ModArmorMaterials.ECOLITE, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));

    public static final DeferredItem<Item> TECTRITE_PICKAXE = ITEMS.register("tectrite_pickaxe",
            () -> new PickaxeItem(ModToolTiers.TECTRITE, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.TECTRITE, 1.0f, -2.8f))));

    public static final DeferredItem<Item> TECTRITE_HELMET = ITEMS.register("tectrite_helmet",
            () -> new ModArmorItem(ModArmorMaterials.TECTRITE, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredItem<Item> TECTRITE_CHESTPLATE = ITEMS.register("tectrite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.TECTRITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredItem<Item> TECTRITE_LEGGINGS = ITEMS.register("tectrite_leggings",
            () -> new ArmorItem(ModArmorMaterials.TECTRITE, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredItem<Item> TECTRITE_BOOTS = ITEMS.register("tectrite_boots",
            () -> new ArmorItem(ModArmorMaterials.TECTRITE, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));

    public static final DeferredItem<Item> HYDRITE_HELMET = ITEMS.register("hydrite_helmet",
            () -> new ModArmorItem(ModArmorMaterials.HYDRITE, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredItem<Item> HYDRITE_CHESTPLATE = ITEMS.register("hydrite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.HYDRITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredItem<Item> HYDRITE_LEGGINGS = ITEMS.register("hydrite_leggings",
            () -> new ArmorItem(ModArmorMaterials.HYDRITE, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredItem<Item> HYDRITE_BOOTS = ITEMS.register("hydrite_boots",
            () -> new ArmorItem(ModArmorMaterials.HYDRITE, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));

    public static final DeferredItem<Item> PETRAFITE_AXE = ITEMS.register("petrafite_axe",
            () -> new AxeItem(ModToolTiers.PETRAFITE, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.PETRAFITE, 5.0f, -3.0f))));

    public static final DeferredItem<Item> PETRAFITE_HELMET = ITEMS.register("petrafite_helmet",
            () -> new ModArmorItem(ModArmorMaterials.PETRAFITE, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredItem<Item> PETRAFITE_CHESTPLATE = ITEMS.register("petrafite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.PETRAFITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredItem<Item> PETRAFITE_LEGGINGS = ITEMS.register("petrafite_leggings",
            () -> new ArmorItem(ModArmorMaterials.PETRAFITE, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredItem<Item> PETRAFITE_BOOTS = ITEMS.register("petrafite_boots",
            () -> new ArmorItem(ModArmorMaterials.PETRAFITE, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));

    public static final DeferredItem<Item> SWIFTITE_SHOVEL = ITEMS.register("swiftite_shovel",
            () -> new ShovelItem(ModToolTiers.SWIFTITE, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.SWIFTITE, 1.5f, -3.0f))));

    public static final DeferredItem<Item> SWIFTITE_HELMET = ITEMS.register("swiftite_helmet",
            () -> new ModArmorItem(ModArmorMaterials.SWIFTITE, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredItem<Item> SWIFTITE_CHESTPLATE = ITEMS.register("swiftite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.SWIFTITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredItem<Item> SWIFTITE_LEGGINGS = ITEMS.register("swiftite_leggings",
            () -> new ArmorItem(ModArmorMaterials.SWIFTITE, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredItem<Item> SWIFTITE_BOOTS = ITEMS.register("swiftite_boots",
            () -> new ArmorItem(ModArmorMaterials.SWIFTITE, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));

    public static List<Item> getCustomFishingRods() {
        return List.of(
                ModItems.BEGINNER_FISHING_ROD.get(),
                ModItems.ADVANCED_FISHING_ROD.get(),
                ModItems.EXPERT_FISHING_ROD.get(),
                ModItems.INTERMEDIATE_FISHING_ROD.get(),
                ModItems.PROFESSIONAL_FISHING_ROD.get(),
                ModItems.HYDRITE_FISHING_ROD.get(),
                ModItems.REINFORCED_HYDRITE_FISHING_ROD.get()
        );
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
