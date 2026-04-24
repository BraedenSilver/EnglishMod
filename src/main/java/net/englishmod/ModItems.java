package net.englishmod;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;

import java.util.Map;

public class ModItems {
    private static final ResourceKey<Item> AMONTILLADO_WINE_KEY = ResourceKey.create(
            Registries.ITEM, Identifier.fromNamespaceAndPath("englishmod", "amontillado_wine"));

    private static final ResourceKey<Item> BLACK_VEIL_KEY = ResourceKey.create(
            Registries.ITEM, Identifier.fromNamespaceAndPath("englishmod", "black_veil"));

    private static final ResourceKey<Item> RAVEN_SPAWN_EGG_KEY = ResourceKey.create(
            Registries.ITEM, Identifier.fromNamespaceAndPath("englishmod", "raven_spawn_egg"));

    private static final ResourceKey<Item> MOBY_DICK_SPAWN_EGG_KEY = ResourceKey.create(
            Registries.ITEM, Identifier.fromNamespaceAndPath("englishmod", "moby_dick_spawn_egg"));

    public static final Item AMONTILLADO_WINE = new AmontilladoWine(new Item.Properties()
            .setId(AMONTILLADO_WINE_KEY)
            .stacksTo(16)
            .food(new FoodProperties.Builder().alwaysEdible().nutrition(0).saturationModifier(0.0f).build()));

    private static final ResourceKey<EquipmentAsset> BLACK_VEIL_ASSET = ResourceKey.create(
            EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath("englishmod", "black_veil"));

    private static final ArmorMaterial BLACK_VEIL_MATERIAL = new ArmorMaterial(
            0, Map.of(ArmorType.HELMET, 0), 1,
            SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F,
            ItemTags.REPAIRS_LEATHER_ARMOR, BLACK_VEIL_ASSET);

    public static final Item BLACK_VEIL = new Item(new Item.Properties()
            .setId(BLACK_VEIL_KEY)
            .stacksTo(1)
            .humanoidArmor(BLACK_VEIL_MATERIAL, ArmorType.HELMET));

    public static final Item RAVEN_SPAWN_EGG = new SpawnEggItem(new Item.Properties()
            .setId(RAVEN_SPAWN_EGG_KEY)
            .stacksTo(64)
            .spawnEgg(ModEntities.RAVEN));

    public static final Item MOBY_DICK_SPAWN_EGG = new SpawnEggItem(new Item.Properties()
            .setId(MOBY_DICK_SPAWN_EGG_KEY)
            .stacksTo(64)
            .spawnEgg(ModEntities.MOBY_DICK));

    public static void register() {
        Registry.register(BuiltInRegistries.ITEM, AMONTILLADO_WINE_KEY, AMONTILLADO_WINE);
        Registry.register(BuiltInRegistries.ITEM, BLACK_VEIL_KEY, BLACK_VEIL);
        Registry.register(BuiltInRegistries.ITEM, RAVEN_SPAWN_EGG_KEY, RAVEN_SPAWN_EGG);
        Registry.register(BuiltInRegistries.ITEM, MOBY_DICK_SPAWN_EGG_KEY, MOBY_DICK_SPAWN_EGG);
    }

    private static class AmontilladoWine extends Item {
        public AmontilladoWine(Item.Properties settings) {
            super(settings);
        }

        @Override
        public ItemUseAnimation getUseAnimation(ItemStack stack) {
            return ItemUseAnimation.DRINK;
        }

        @Override
        public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
            if (!world.isClientSide() && user instanceof ServerPlayer serverPlayer) {
                BlockPos center = serverPlayer.blockPosition();
                buildBrickRoom(world, center);
                serverPlayer.teleportTo(center.getX() + 0.5, center.getY() + 1.0, center.getZ() + 0.5);
            }
            return super.finishUsingItem(stack, world, user);
        }

        private void buildBrickRoom(Level world, BlockPos center) {
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    for (int y = -1; y <= 2; y++) {
                        BlockPos pos = center.offset(x, y, z);
                        if (x == 0 && z == 0 && y == 0) {
                            world.setBlock(pos, Blocks.GLOW_LICHEN.defaultBlockState()
                                    .setValue(MultifaceBlock.getFaceProperty(Direction.DOWN), true), 3);
                        } else if (x == 0 && z == 0 && y == 1) {
                            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                        } else if (y == -1 || y == 2 || x == -1 || x == 1 || z == -1 || z == 1) {
                            world.setBlock(pos, Blocks.BRICKS.defaultBlockState(), 3);
                        } else {
                            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                        }
                    }
                }
            }
        }
    }
}
