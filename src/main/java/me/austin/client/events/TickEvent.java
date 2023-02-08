package me.austin.client.events;

public abstract class TickEvent {
    private final boolean inWorld;

    public final boolean isInWorld() {
        return this.inWorld;
    }

    public TickEvent(boolean inWorld) {
        this.inWorld = inWorld;
    }

    public static final class Post extends TickEvent {
        public Post(boolean inWorld) {
            super(inWorld);
        }
    }
}
