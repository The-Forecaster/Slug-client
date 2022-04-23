package trans.rights.client.events;

import javax.annotation.ParametersAreNonnullByDefault;

public abstract class TickEvent {
    private boolean inWorld;

    public final boolean isInWorld() {
        return this.inWorld;
    }

    @ParametersAreNonnullByDefault
    protected final void setInWorld(boolean inWorld) {
        this.inWorld = inWorld;
    }

    public static final class PreTick extends TickEvent {
        private static final PreTick INSTANCE = new PreTick();

        @ParametersAreNonnullByDefault
        public static PreTick get(boolean inWorld) {
            INSTANCE.setInWorld(inWorld);

            return INSTANCE;
        }
    }

    public static final class PostTick extends TickEvent {
        private static final PostTick INSTANCE = new PostTick();

        @ParametersAreNonnullByDefault
        public static PostTick get(boolean inWorld) {
            INSTANCE.setInWorld(inWorld);

            return INSTANCE;
        }
    }
}
