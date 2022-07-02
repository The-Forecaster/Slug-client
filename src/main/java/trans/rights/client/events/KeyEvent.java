package trans.rights.client.events;

import trans.rights.event.type.Cancellable;

public final class KeyEvent extends Cancellable {
    private static final KeyEvent INSTANCE = new KeyEvent();

    private int key;

    public static KeyEvent get(int key) {
        INSTANCE.key = key;

        return INSTANCE;
    }

    public int getKey() {
        return this.key;
    }
}
