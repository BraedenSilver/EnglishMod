package net.englishmod.util;

public final class HeartbeatState {
    private HeartbeatState() {}

    // Set by server-side BeatingHeartMixin, consumed by client-side HeartbeatMixin.
    // Works for single-player (integrated server shares the JVM).
    public static volatile boolean killPending = false;
}
