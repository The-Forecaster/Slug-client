package me.austin.queer.event;

public abstract class Event {
    private boolean cancelled = false;

    public final boolean isCancelled() {
        return this.cancelled;
    }

    public final void cancel() {
        this.cancelled = true;
    }
}
