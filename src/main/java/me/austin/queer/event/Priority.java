package me.austin.queer.event;

import me.zero.alpine.event.EventPriority;

public enum Priority implements EventPriority {
    HIGHEST(EventPriority.HIGHEST),
    HIGH(EventPriority.HIGH),
    MEDIUM(EventPriority.MEDIUM),
    LOW(EventPriority.LOW),
    LOWEST(EventPriority.LOWEST),
    DEFAULT(EventPriority.DEFAULT);

    private int priority;

    private Priority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return this.priority;
    }

    public static Priority getPriority(int priority) {
        for (Priority p : values()) {
            if (p.priority == priority) {
                return p;
            }
        }
        return null;
    }
}
