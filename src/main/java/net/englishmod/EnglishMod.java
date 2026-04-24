package net.englishmod;

import net.englishmod.entity.RavenEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.AABB;

public class EnglishMod implements ModInitializer {

    private static final AABB WORLD_BOUNDS = new AABB(-3e7, -64, -3e7, 3e7, 320, 3e7);

    @Override
    public void onInitialize() {
        ModSounds.register();
        ModItems.register();
        ModEntities.register();
        ModItemGroups.register();

        ServerMessageEvents.CHAT_MESSAGE.register((message, sender, params) -> {
            var server = ((ServerLevel) sender.level()).getServer();
            var level = (ServerLevel) sender.level();
            server.execute(() -> {
                new Thread() {
                    { setDaemon(true); }
                    @Override
                    public void run() {
                        try {
                            sleep(500);
                        } catch (InterruptedException ignored) {}
                        server.execute(() -> {
                            for (RavenEntity raven : level.getEntitiesOfClass(RavenEntity.class, WORLD_BOUNDS)) {
                                if (raven.hasCustomName() && "Poe".equals(raven.getCustomName().getString())) {
                                    server.getPlayerList().broadcastSystemMessage(
                                            Component.literal("Poe: Nevermore"), false);
                                    break;
                                }
                            }
                        });
                    }
                }.start();
            });
        });
    }
}
