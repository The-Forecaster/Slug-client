package me.austin.queer.event;

import me.zero.alpine.event.EventState;

public abstract class Event {
    private EventState stage;
    private Priority priority;
    private boolean cancelled = false;

    protected Event(EventState stage, int priority) {
        this(stage, Priority.getPriority(priority));
    }

    protected Event(EventState stage, Priority priority) {
        this.stage = stage;
        this.priority = priority;
    }

    public final EventState getStage() {
        return this.stage;
    }

    public final Priority getPrio() {
        return this.priority;
    }

    public final boolean isCancelled() {
        return this.cancelled;
    }

    public final void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
