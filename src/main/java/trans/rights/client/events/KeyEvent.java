package trans.rights.client.events;

public final class KeyEvent {
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
