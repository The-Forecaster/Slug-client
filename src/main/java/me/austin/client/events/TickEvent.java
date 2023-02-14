package me.austin.client.events;

public record TickEvent(boolean inWorld) {
    public boolean isInWorld() {
        return inWorld;
    }
}
