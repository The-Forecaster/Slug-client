package trans.rights.client.events;

import javax.annotation.ParametersAreNonnullByDefault;

public final class KeyEvent {
    private static final KeyEvent INSTANCE = new KeyEvent();

    private int key;

    @ParametersAreNonnullByDefault
    public static KeyEvent get(int key) {
        INSTANCE.key = key;

        return INSTANCE;
    }

    public int getKey() {
        return this.key;
    }
}
