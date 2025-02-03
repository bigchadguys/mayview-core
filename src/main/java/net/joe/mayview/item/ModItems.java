package net.joe.mayview.item;

import net.joe.mayview.Mayview;
import net.joe.mayview.item.custom.ModArmorItem;
import net.joe.mayview.item.custom.ModEffectSwordItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Mayview.MOD_ID);

    public static final DeferredItem<Item> RAW_MORPHITE = ITEMS.registerSimpleItem("raw_morphite");
    public static final DeferredItem<Item> MORPHITE_INGOT = ITEMS.registerSimpleItem("morphite_ingot");

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

    public static final DeferredItem<Item> COPPER_COIN = ITEMS.registerSimpleItem("copper_coin");
    public static final DeferredItem<Item> IRON_COIN = ITEMS.registerSimpleItem("iron_coin");
    public static final DeferredItem<Item> GOLD_COIN = ITEMS.registerSimpleItem("gold_coin");
    public static final DeferredItem<Item> DIAMOND_COIN = ITEMS.registerSimpleItem("diamond_coin");
    public static final DeferredItem<Item> JOE_COIN = ITEMS.registerSimpleItem("joe_coin");

    public static final DeferredItem<Item> HONEY_DIAMOND = ITEMS.registerItem("honey_diamond", properties -> new Item(properties) {
        @Override
        public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
            tooltipComponents.add(Component.translatable(  "item.mayview.honey_diamond.tooltip.1"));
            super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        }
    }, new Item.Properties().food(ModFoodProperties.HONEY_DIAMOND));

    public static final DeferredItem<Item> HONEY_DIAMOND_SWORD = ITEMS.register("honey_diamond_sword",
            () -> new ModEffectSwordItem(ModToolTiers.HONEY_DIAMOND,
                    new Item.Properties()
                            .attributes(SwordItem.createAttributes(ModToolTiers.HONEY_DIAMOND, 3.0f, -2.4f)),
                    MobEffects.MOVEMENT_SLOWDOWN) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.mayview.honey_diamond_sword.tooltip.1"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            }
    );

    public static final DeferredItem<Item> HONEY_DIAMOND_PICKAXE = ITEMS.register("honey_diamond_pickaxe",
            () -> new PickaxeItem(ModToolTiers.HONEY_DIAMOND, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.HONEY_DIAMOND,1.0f, -2.8f ))));
    public static final DeferredItem<Item> HONEY_DIAMOND_AXE = ITEMS.register("honey_diamond_axe",
            () -> new AxeItem(ModToolTiers.HONEY_DIAMOND, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.HONEY_DIAMOND,5.0f, -3.0f ))));
    public static final DeferredItem<Item> HONEY_DIAMOND_SHOVEL = ITEMS.register("honey_diamond_shovel",
            () -> new ShovelItem(ModToolTiers.HONEY_DIAMOND, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.HONEY_DIAMOND,1.5f, -3.0f ))));
    public static final DeferredItem<Item> HONEY_DIAMOND_HOE = ITEMS.register("honey_diamond_hoe",
            () -> new HoeItem(ModToolTiers.HONEY_DIAMOND, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.HONEY_DIAMOND,-3.0f, 0.0f ))));

    public static final DeferredItem<Item> HONEY_DIAMOND_HELMET = ITEMS.register("honey_diamond_helmet",
            () -> new ModArmorItem(ModArmorMaterials.HONEY_DIAMOND, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredItem<Item> HONEY_DIAMOND_CHESTPLATE = ITEMS.register("honey_diamond_chestplate",
            () -> new ArmorItem(ModArmorMaterials.HONEY_DIAMOND, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredItem<Item> HONEY_DIAMOND_LEGGINGS = ITEMS.register("honey_diamond_leggings",
            () -> new ArmorItem(ModArmorMaterials.HONEY_DIAMOND, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredItem<Item> HONEY_DIAMOND_BOOTS = ITEMS.register("honey_diamond_boots",
            () -> new ArmorItem(ModArmorMaterials.HONEY_DIAMOND, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));

    public static final DeferredItem<Item> SPECTRITE_SWORD = ITEMS.register("spectrite_sword",
            () -> new ModEffectSwordItem(ModToolTiers.SPECTRITE,
                    new Item.Properties()
                            .attributes(SwordItem.createAttributes(ModToolTiers.SPECTRITE, 3.0f, -2.4f)),
                    MobEffects.MOVEMENT_SLOWDOWN) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("item.mayview.spectrite_sword.tooltip.1"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            }
    );

    public static final DeferredItem<Item> SPECTRITE_HELMET = ITEMS.register("spectrite_helmet",
            () -> new ModArmorItem(ModArmorMaterials.SPECTRITE, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredItem<Item> SPECTRITE_CHESTPLATE = ITEMS.register("spectrite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.SPECTRITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredItem<Item> SPECTRITE_LEGGINGS = ITEMS.register("spectrite_leggings",
            () -> new ArmorItem(ModArmorMaterials.SPECTRITE, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredItem<Item> SPECTRITE_BOOTS = ITEMS.register("spectrite_boots",
            () -> new ArmorItem(ModArmorMaterials.SPECTRITE, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));

    public static final DeferredItem<Item> ECOLITE_HOE = ITEMS.register("ecolite_hoe",
            () -> new HoeItem(ModToolTiers.ECOLITE, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.ECOLITE,-3.0f, 0.0f ))));

    public static final DeferredItem<Item> ECOLITE_HELMET = ITEMS.register("ecolite_helmet",
            () -> new ModArmorItem(ModArmorMaterials.ECOLITE, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredItem<Item> ECOLITE_CHESTPLATE = ITEMS.register("ecolite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ECOLITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredItem<Item> ECOLITE_LEGGINGS = ITEMS.register("ecolite_leggings",
            () -> new ArmorItem(ModArmorMaterials.ECOLITE, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredItem<Item> ECOLITE_BOOTS = ITEMS.register("ecolite_boots",
            () -> new ArmorItem(ModArmorMaterials.ECOLITE, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));

    public static final DeferredItem<Item> TECTRITE_PICKAXE = ITEMS.register("tectrite_pickaxe",
            () -> new PickaxeItem(ModToolTiers.TECTRITE, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.TECTRITE,1.0f, -2.8f ))));

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
            () -> new AxeItem(ModToolTiers.PETRAFITE, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.PETRAFITE,5.0f, -3.0f ))));

    public static final DeferredItem<Item> PETRAFITE_HELMET = ITEMS.register("petrafite_helmet",
            () -> new ModArmorItem(ModArmorMaterials.PETRAFITE, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredItem<Item> PETRAFITE_CHESTPLATE = ITEMS.register("petrafite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.PETRAFITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredItem<Item> PETRAFITE_LEGGINGS = ITEMS.register("petrafite_leggings",
            () -> new ArmorItem(ModArmorMaterials.PETRAFITE, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredItem<Item> PETRAFITE_BOOTS = ITEMS.register("petrafite_boots",
            () -> new ArmorItem(ModArmorMaterials.PETRAFITE, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
