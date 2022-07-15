package trans.rights.client.events;

public abstract class TickEvent {
    private boolean inWorld;

    public final boolean isInWorld() {
        return this.inWorld;
    }

    protected final void setInWorld(boolean inWorld) {
        this.inWorld = inWorld;
    }

    public TickEvent(boolean inWorld) {
        this.inWorld = inWorld;
    }

    public static final class Pre extends TickEvent {
        public Pre(boolean inWorld) {
            super(inWorld);
        }
    }

    public static final class Post extends TickEvent {
        public Post(boolean inWorld) {
            super(inWorld);
        }
    }
}
