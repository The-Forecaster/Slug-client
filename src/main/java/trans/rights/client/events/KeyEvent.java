package trans.rights.client.events;

public class KeyEvent {
    private static final KeyEvent INSTANCE = new KeyEvent();

    private int key;

    public static KeyEvent get(int key) {
        INSTANCE.key = key;

        return INSTANCE;
    }

    public final int getKey() {
        return this.key;
    }
}
