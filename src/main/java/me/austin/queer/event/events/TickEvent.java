package me.austin.queer.event.events;

import me.austin.queer.event.Event;
import me.austin.queer.event.Priority;
import me.zero.alpine.event.EventState;

public abstract class TickEvent extends Event {
    private boolean inworld;
    
    protected TickEvent(EventState stage, Priority priority) {
        super(stage, priority);
    }

    public final boolean isInWorld() {
        return inworld;
    }

    public final void setInworld(boolean inworld) {
        this.inworld = inworld;
    }

    public static final class PreTick extends TickEvent {
        private static final PreTick INSTANCE = new PreTick();

        private PreTick() {
            super(EventState.PRE, Priority.HIGHEST);
        }

        public static PreTick get(boolean inworld) {
            INSTANCE.setCancelled(false);
            INSTANCE.setInworld(inworld);

            return INSTANCE;
        }
    }

    public static final class PostTick extends TickEvent {
        public static PostTick INSTANCE = new PostTick();

        private PostTick() {
            super(EventState.POST, Priority.DEFAULT);
        }

        public static final PostTick get(boolean inworld) {
            INSTANCE.setCancelled(false);
            INSTANCE.setInworld(inworld);

            return INSTANCE;
        }
    }
}
