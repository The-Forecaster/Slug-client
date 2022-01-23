package trans.rights.event.listener;

public abstract class Event {
    private boolean cancelled = false;

    public final boolean isCancelled() {
        return this.cancelled;
    }

    public final void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
