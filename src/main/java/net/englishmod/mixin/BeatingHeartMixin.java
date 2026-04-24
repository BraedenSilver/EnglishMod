package net.englishmod.mixin;

import net.englishmod.util.HeartbeatState;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class BeatingHeartMixin {
    @Inject(method = "die", at = @At("HEAD"))
    private void onDeath(DamageSource damageSource, CallbackInfo ci) {
        LivingEntity dying = (LivingEntity)(Object) this;
        // Don't trigger on player deaths, and only run server-side
        if (dying instanceof Player) return;
        if (dying.level().isClientSide()) return;

        Entity killer = damageSource.getEntity();
        if (killer instanceof ServerPlayer player) {
            player.playSound(SoundEvents.PIGLIN_DEATH, 1.0f, 1.0f);
            HeartbeatState.killPending = true;
        }
    }
}
