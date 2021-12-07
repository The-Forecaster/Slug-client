package me.austin.queer.event.system;

import me.austin.queer.event.Event;

public class KeyEvent extends Event {
    private static KeyEvent INSTANCE = new KeyEvent();

    private int key;
    private int scanCode;

    public static KeyEvent get(int key, int scanCode) {
        if (INSTANCE == null) {
            INSTANCE = new KeyEvent();
        } else if (INSTANCE.isCancelled()) {
            INSTANCE = new KeyEvent();
        }
        INSTANCE.key = key;
        INSTANCE.scanCode = scanCode;

        return INSTANCE;
    }

    public int getKey() {
        return this.key;
    }

    public int getScanCode() {
		return this.scanCode;
	}
}
