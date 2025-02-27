package net.joe.mayview.item;

import net.joe.mayview.Mayview;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

public class ModArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, Mayview.MOD_ID);
    public static final Holder<ArmorMaterial> COPPER =
            ARMOR_MATERIALS.register("copper", () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.HELMET, 3);
                        map.put(ArmorItem.Type.CHESTPLATE, 8);
                        map.put(ArmorItem.Type.LEGGINGS, 6);
                        map.put(ArmorItem.Type.BOOTS, 3);
                        map.put(ArmorItem.Type.BODY, 11);
                    }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(Items.COPPER_INGOT),
                    List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Mayview.MOD_ID, "copper"))),
                    2.0f, 0));

    public static final Holder<ArmorMaterial> HONEY_DIAMOND =
            ARMOR_MATERIALS.register("honey_diamond", () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.HELMET, 3);
                        map.put(ArmorItem.Type.CHESTPLATE, 8);
                        map.put(ArmorItem.Type.LEGGINGS, 6);
                        map.put(ArmorItem.Type.BOOTS, 3);
                        map.put(ArmorItem.Type.BODY, 11);
                    }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(ModItems.HONEY_DIAMOND.get()),
                    List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Mayview.MOD_ID, "honey_diamond"))),
                    2.0f, 0));

    public static final Holder<ArmorMaterial> STEEL =
            ARMOR_MATERIALS.register("steel", () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.HELMET, 3);
                        map.put(ArmorItem.Type.CHESTPLATE, 8);
                        map.put(ArmorItem.Type.LEGGINGS, 6);
                        map.put(ArmorItem.Type.BOOTS, 3);
                        map.put(ArmorItem.Type.BODY, 11);
                    }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(ModItems.STEEL_INGOT.get()),
                    List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Mayview.MOD_ID, "steel"))),
                    2.0f, 0));

    public static final Holder<ArmorMaterial> AMETHYST =
            ARMOR_MATERIALS.register("amethyst", () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.HELMET, 3);
                        map.put(ArmorItem.Type.CHESTPLATE, 8);
                        map.put(ArmorItem.Type.LEGGINGS, 6);
                        map.put(ArmorItem.Type.BOOTS, 3);
                        map.put(ArmorItem.Type.BODY, 11);
                    }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(Items.AMETHYST_SHARD),
                    List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Mayview.MOD_ID, "amethyst"))),
                    2.0f, 0));

    public static final Holder<ArmorMaterial> EMERALD =
            ARMOR_MATERIALS.register("emerald", () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.HELMET, 3);
                        map.put(ArmorItem.Type.CHESTPLATE, 8);
                        map.put(ArmorItem.Type.LEGGINGS, 6);
                        map.put(ArmorItem.Type.BOOTS, 3);
                        map.put(ArmorItem.Type.BODY, 11);
                    }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(Items.EMERALD),
                    List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Mayview.MOD_ID, "emerald"))),
                    2.0f, 0));

    public static final Holder<ArmorMaterial> SPECTRITE =
            ARMOR_MATERIALS.register("spectrite", () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.HELMET, 3);
                        map.put(ArmorItem.Type.CHESTPLATE, 8);
                        map.put(ArmorItem.Type.LEGGINGS, 6);
                        map.put(ArmorItem.Type.BOOTS, 3);
                        map.put(ArmorItem.Type.BODY, 11);
                    }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(ModItems.SPECTRITE_INGOT.get()),
                    List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Mayview.MOD_ID, "spectrite"))),
                    2.0f, 0));

    public static final Holder<ArmorMaterial> ECOLITE =
            ARMOR_MATERIALS.register("ecolite", () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.HELMET, 3);
                        map.put(ArmorItem.Type.CHESTPLATE, 8);
                        map.put(ArmorItem.Type.LEGGINGS, 6);
                        map.put(ArmorItem.Type.BOOTS, 3);
                        map.put(ArmorItem.Type.BODY, 11);
                    }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(ModItems.ECOLITE_INGOT.get()),
                    List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Mayview.MOD_ID, "ecolite"))),
                    2.0f, 0));

    public static final Holder<ArmorMaterial> TECTRITE =
            ARMOR_MATERIALS.register("tectrite", () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.HELMET, 3);
                        map.put(ArmorItem.Type.CHESTPLATE, 8);
                        map.put(ArmorItem.Type.LEGGINGS, 6);
                        map.put(ArmorItem.Type.BOOTS, 3);
                        map.put(ArmorItem.Type.BODY, 11);
                    }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(ModItems.TECTRITE_INGOT.get()),
                    List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Mayview.MOD_ID, "tectrite"))),
                    2.0f, 0));

    public static final Holder<ArmorMaterial> HYDRITE =
            ARMOR_MATERIALS.register("hydrite", () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.HELMET, 3);
                        map.put(ArmorItem.Type.CHESTPLATE, 8);
                        map.put(ArmorItem.Type.LEGGINGS, 6);
                        map.put(ArmorItem.Type.BOOTS, 3);
                        map.put(ArmorItem.Type.BODY, 11);
                    }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(ModItems.HYDRITE_INGOT.get()),
                    List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Mayview.MOD_ID, "hydrite"))),
                    2.0f, 0));

    public static final Holder<ArmorMaterial> PETRAFITE =
            ARMOR_MATERIALS.register("petrafite", () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.HELMET, 3);
                        map.put(ArmorItem.Type.CHESTPLATE, 8);
                        map.put(ArmorItem.Type.LEGGINGS, 6);
                        map.put(ArmorItem.Type.BOOTS, 3);
                        map.put(ArmorItem.Type.BODY, 11);
                    }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(ModItems.PETRAFITE_INGOT.get()),
                    List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Mayview.MOD_ID, "petrafite"))),
                    2.0f, 0));

    public static final Holder<ArmorMaterial> SWIFTITE =
            ARMOR_MATERIALS.register("swiftite", () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.HELMET, 3);
                        map.put(ArmorItem.Type.CHESTPLATE, 8);
                        map.put(ArmorItem.Type.LEGGINGS, 6);
                        map.put(ArmorItem.Type.BOOTS, 3);
                        map.put(ArmorItem.Type.BODY, 11);
                    }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(ModItems.SWIFTITE_INGOT.get()),
                    List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Mayview.MOD_ID, "swiftite"))),
                    2.0f, 0));

    public static void register(IEventBus eventBus) {
        ARMOR_MATERIALS.register(eventBus);
    }
}
