package me.austin.queer.event.client;

import me.austin.queer.event.Event;

public class GameLeftEvent extends Event {
    private static final GameLeftEvent instance = new GameLeftEvent();

    public static GameLeftEvent get() {
        return instance;
    }
}
