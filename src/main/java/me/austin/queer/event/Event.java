package me.austin.queer.event;

import me.zero.alpine.event.EventState;
import me.zero.alpine.event.type.ICancellable;

public abstract class Event implements ICancellable {
    protected final EventState stage;
    private final Priority priority;
    private boolean cancelled = false;

    protected Event(EventState stage, int priority) {
        this(stage, Priority.getPriority(priority));
    }

    protected Event(EventState stage, Priority priority) {
        this.stage = stage;
        this.priority = priority;
    }

    public EventState stage() {
        return this.stage;
    }

    public Priority priority() {
        return this.priority;
    }

    @Override
    public void cancel() {
        this.cancelled = true;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }
}
