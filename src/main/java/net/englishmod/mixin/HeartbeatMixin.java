package net.englishmod.mixin;

import net.englishmod.api.HeartbeatAccessor;
import net.englishmod.util.HeartbeatState;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class HeartbeatMixin implements HeartbeatAccessor {
    private int heartbeatTimer = 0;
    private int attackDecay = 0;
    private int killDecay = 0;

    private static final int IDLE_INTERVAL   = 24;
    private static final int ATTACK_INTERVAL = 14;
    private static final int KILL_INTERVAL   = 8;

    private static final float IDLE_VOLUME   = 0.2f;
    private static final float ATTACK_VOLUME = 0.5f;
    private static final float KILL_VOLUME   = 0.85f;

    private static final int ATTACK_DECAY_TICKS = 200;
    private static final int KILL_DECAY_TICKS   = 300;

    @Inject(method = "attack", at = @At("HEAD"))
    private void onAttackEntity(Entity target, CallbackInfo ci) {
        Player player = (Player)(Object) this;
        if (!player.level().isClientSide()) return;
        if (target instanceof LivingEntity) {
            attackDecay = ATTACK_DECAY_TICKS;
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        Player player = (Player)(Object) this;
        if (!player.level().isClientSide()) return;

        if (HeartbeatState.killPending) {
            HeartbeatState.killPending = false;
            killDecay = KILL_DECAY_TICKS;
            attackDecay = ATTACK_DECAY_TICKS;
        }

        if (attackDecay > 0) attackDecay--;
        if (killDecay > 0) killDecay--;

        int interval;
        float volume;
        if (killDecay > 0) {
            interval = KILL_INTERVAL;
            volume = KILL_VOLUME;
        } else if (attackDecay > 0) {
            interval = ATTACK_INTERVAL;
            volume = ATTACK_VOLUME;
        } else {
            interval = IDLE_INTERVAL;
            volume = IDLE_VOLUME;
        }

        heartbeatTimer++;
        if (heartbeatTimer >= interval) {
            heartbeatTimer = 0;
            player.playSound(SoundEvents.WARDEN_HEARTBEAT, volume, 1.0f);
        }
    }

    @Override
    public int getKillBoostTimer() { return killDecay; }

    @Override
    public void setKillBoostTimer(int timer) { this.killDecay = timer; }
}
