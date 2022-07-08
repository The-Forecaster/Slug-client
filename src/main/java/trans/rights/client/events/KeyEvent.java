package trans.rights.client.events;

import trans.rights.event.type.Cancellable;

public final class KeyEvent extends Cancellable {
    private final int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }
}
