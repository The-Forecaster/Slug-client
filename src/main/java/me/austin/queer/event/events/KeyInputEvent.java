package me.austin.queer.event.events;

import me.austin.queer.event.Event;

public class KeyInputEvent extends Event {
    int key;

    public KeyInputEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
