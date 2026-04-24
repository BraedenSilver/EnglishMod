package net.englishmod;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroups {

    public static final ResourceKey<CreativeModeTab> ENGLISH_MOD_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(),
            Identifier.fromNamespaceAndPath("englishmod", "english_mod_tab"));

    public static final CreativeModeTab ENGLISH_MOD_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.MOBY_DICK_SPAWN_EGG))
            .title(Component.translatable("itemGroup.englishmod"))
            .displayItems((params, output) -> {
                output.accept(ModItems.AMONTILLADO_WINE);
                output.accept(ModItems.BLACK_VEIL);
                output.accept(ModItems.RAVEN_SPAWN_EGG);
                output.accept(ModItems.MOBY_DICK_SPAWN_EGG);
            })
            .build();

    public static void register() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ENGLISH_MOD_TAB_KEY, ENGLISH_MOD_TAB);
    }
}
