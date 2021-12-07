package me.austin.queer.event.client;

import me.austin.queer.event.Event;

public class GameJoinedEvent extends Event {
    private static final GameJoinedEvent instance = new GameJoinedEvent();

    public static GameJoinedEvent get() {
        return instance;
    }
}
