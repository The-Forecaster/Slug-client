package me.austin.queer.event.events;

import me.austin.queer.event.Event;
import me.austin.queer.event.Priority;
import me.zero.alpine.event.EventState;

public abstract class TickEvent extends Event {
    public final boolean inworld;

    protected TickEvent(EventState stage, Priority priority, boolean inworld) {
        super(stage, priority);
        this.inworld = inworld;
    }

    public static class Pre extends TickEvent {
        public Pre(boolean inworld) {
            super(EventState.PRE, Priority.HIGHEST, inworld);
        }
    }

    public static class Post extends TickEvent {
        public Post(boolean inworld) {
            super(EventState.POST, Priority.HIGHEST, inworld);
        }
    }
}
