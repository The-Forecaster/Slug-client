package me.austin.queer.event.events;

import me.austin.queer.event.Event;

public abstract class TickEvent extends Event {
    private boolean inworld;

    public final boolean isInWorld() {
        return inworld;
    }

    public final void setInworld(boolean inworld) {
        this.inworld = inworld;
    }

    public static final class PreTick extends TickEvent {
        private static final PreTick INSTANCE = new PreTick();

        public static PreTick get(boolean inworld) {
            INSTANCE.setCancelled(false);
            INSTANCE.setInworld(inworld);

            return INSTANCE;
        }
    }

    public static final class PostTick extends TickEvent {
        private static final PostTick INSTANCE = new PostTick();

        public static final PostTick get(boolean inworld) {
            INSTANCE.setCancelled(false);
            INSTANCE.setInworld(inworld);

            return INSTANCE;
        }
    }
}
