package me.austin.queer.event.client;

import me.austin.queer.event.Event;

public class MouseEvent extends Event {
    private final int button;

    public MouseEvent(int button) {
        this.button = button;
    }

    public int getButton() {
        return this.button;
    }
}
