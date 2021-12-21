package me.austin.queer.event;

import me.zero.alpine.event.type.ICancellable;

public abstract class Event implements ICancellable{
    private Stage stage;
    private Priority priority;
    private boolean cancelled = false;

    protected Event(Stage stage, int priority) {
        this(stage, Priority.get(priority));        
    }

    protected Event(Stage stage, Priority priority) {
        this.stage = stage;
        this.priority = priority;
    }

    public Stage stage() {
        return this.stage;
    }

    public Priority priority() {
        return this.priority;
    }

    protected static enum Stage {
        PRE,
        POST
    }

    protected static enum Priority {
        HGIHEST(400),
        HIGH(300),
        MID(200),
        LOW(100),
        NONE(0);

        private final int priority;

        Priority(int priority) {
            this.priority = priority;
        }

        public int getPriority() {
            return this.priority;
        }

        public static Priority get(int priority) {
            for (Priority pri : Priority.values()) {
                if (pri.getPriority() == priority) return pri;
            }
            return NONE;
        }
    }

    @Override
    public void cancel() {
        this.cancelled = true;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }
}
