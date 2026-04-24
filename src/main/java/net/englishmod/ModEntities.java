package net.englishmod;

import net.englishmod.entity.MobyDickEntity;
import net.englishmod.entity.RavenEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;

public class ModEntities {

    public static EntityType<RavenEntity> RAVEN;
    public static EntityType<MobyDickEntity> MOBY_DICK;

    static {
        ResourceKey<EntityType<?>> ravenKey = ResourceKey.create(
                Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath("englishmod", "raven"));
        RAVEN = (EntityType<RavenEntity>) Registry.register(
                BuiltInRegistries.ENTITY_TYPE, ravenKey,
                EntityType.Builder.<RavenEntity>of(RavenEntity::new, MobCategory.CREATURE)
                        .sized(0.4f, 0.5f)
                        .build(ravenKey));

        ResourceKey<EntityType<?>> mobyDickKey = ResourceKey.create(
                Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath("englishmod", "moby_dick"));
        MOBY_DICK = (EntityType<MobyDickEntity>) Registry.register(
                BuiltInRegistries.ENTITY_TYPE, mobyDickKey,
                EntityType.Builder.<MobyDickEntity>of(MobyDickEntity::new, MobCategory.WATER_CREATURE)
                        .sized(3.0f, 1.5f)
                        .build(mobyDickKey));
    }

    public static void register() {
        FabricDefaultAttributeRegistry.register(RAVEN, RavenEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(MOBY_DICK, MobyDickEntity.createAttributes());

        BiomeModifications.addSpawn(
                ctx -> ctx.getBiomeKey().equals(Biomes.DARK_FOREST)
                        || ctx.getBiomeKey().equals(Biomes.FOREST)
                        || ctx.getBiomeKey().equals(Biomes.TAIGA)
                        || ctx.getBiomeKey().equals(Biomes.OLD_GROWTH_PINE_TAIGA),
                MobCategory.CREATURE, RAVEN, 8, 1, 3);

        BiomeModifications.addSpawn(
                ctx -> ctx.getBiomeKey().equals(Biomes.OCEAN)
                        || ctx.getBiomeKey().equals(Biomes.DEEP_OCEAN)
                        || ctx.getBiomeKey().equals(Biomes.COLD_OCEAN)
                        || ctx.getBiomeKey().equals(Biomes.DEEP_COLD_OCEAN),
                MobCategory.WATER_CREATURE, MOBY_DICK, 2, 1, 2);
    }
}
