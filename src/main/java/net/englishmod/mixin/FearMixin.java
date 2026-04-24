package net.englishmod.mixin;

import net.englishmod.ModItems;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public class FearMixin {
    @Inject(method = "tickChildren", at = @At("TAIL"))
    private void onTick(BooleanSupplier booleanSupplier, CallbackInfo ci) {
        MinecraftServer server = (MinecraftServer) (Object) this;
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            if (player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.HEAD).is(ModItems.BLACK_VEIL)) {
                for (Entity entity : player.level().getEntities(player, player.getBoundingBox().inflate(12.0))) {
                    if (entity instanceof Mob mob) {
                        mob.setTarget(null);
                        PathNavigation nav = mob.getNavigation();
                        if (!nav.isDone()) {
                            nav.stop();
                        }
                        Vec3 awayPos = DefaultRandomPos.getPosAway((PathfinderMob) mob, 10, 7, player.position());
                        if (awayPos != null) {
                            Path path = nav.createPath(awayPos.x, awayPos.y, awayPos.z, 0);
                            if (path != null) {
                                nav.moveTo(path, 2.5);
                            }
                        }
                    }
                }
            }
        }
    }
}
