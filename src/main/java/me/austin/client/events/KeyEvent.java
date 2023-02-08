package me.austin.client.events;

import me.austin.rush.Cancellable;

public final class KeyEvent extends Cancellable {
    private final int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }
}
