package me.austin.queer.event.client;

import me.austin.queer.event.Event;

public class KeyEvent extends Event {
    private final int key;
    private final int scanCode;

    public KeyEvent(int key, int scanCode) {
        this.key = key;
        this.scanCode = scanCode;
    }

    public int getKey() {
        return this.key;
    }

    public int getScanCode() {
		return this.scanCode;
	}
}
