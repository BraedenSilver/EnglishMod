package net.englishmod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {

    public static SoundEvent MOBY_DICK_AMBIENT;

    public static void register() {
        Identifier id = Identifier.fromNamespaceAndPath("englishmod", "entity.moby_dick.ambient");
        MOBY_DICK_AMBIENT = Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }
}
