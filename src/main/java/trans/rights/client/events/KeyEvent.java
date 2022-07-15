package trans.rights.client.events;

import trans.rights.event.type.Cancellable;

public final class KeyEvent extends Cancellable {
    private final int key, scancode, action, modifiers;

    public KeyEvent(int key, int scancode, int action, int modifiers) {
        this.key = key;
        this.scancode = scancode;
        this.action = action;
        this.modifiers = modifiers;
    }

    public int getKey() {
        return this.key;
    }

    public int getScancode() {
        return this.scancode;
    }

    public int getAction() {
        return this.action;
    }

    public int getModifiers() {
        return this.modifiers;
    }
}
