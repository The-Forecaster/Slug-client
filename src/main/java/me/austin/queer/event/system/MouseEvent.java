package me.austin.queer.event.system;

import me.austin.queer.event.Event;

public class MouseEvent extends Event {
    private static MouseEvent INSTANCE = new MouseEvent();

    private int button;

    public static MouseEvent get(int button) {
        if (INSTANCE == null) {
            INSTANCE = new MouseEvent();
        }
        else if (INSTANCE.isCancelled()) {
            INSTANCE = new MouseEvent();
        }
        INSTANCE.button = button;

        return INSTANCE;
    }

    public int getButton() {
        return this.button;
    }
}
