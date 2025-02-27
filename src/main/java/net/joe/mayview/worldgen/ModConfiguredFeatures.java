package net.joe.mayview.worldgen;

import net.joe.mayview.Mayview;
import net.joe.mayview.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_MORPHITE_KEY = registerKey("morphite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TECTONIC_KEY = registerKey("tectonic_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldMorphiteOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.MORPHITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_MORPHITE_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldTectonicOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.TECTONIC_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_TECTONIC_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_MORPHITE_KEY, Feature.ORE, new OreConfiguration(overworldMorphiteOres, 3));
        register(context, OVERWORLD_TECTONIC_KEY, Feature.ORE, new OreConfiguration(overworldTectonicOres, 3));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Mayview.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}