package me.austin.queer.event.world;

import me.austin.queer.event.Event;
import net.minecraft.client.MinecraftClient;

public class TickEvent extends Event {
    private final float tickdelta;

    public TickEvent(float tickdelta) {
        this.tickdelta = tickdelta;
    }

    public TickEvent() {
        this.tickdelta = MinecraftClient.getInstance().getTickDelta();
    }

    public float getTickDelta() {
        return this.tickdelta;
    }
}