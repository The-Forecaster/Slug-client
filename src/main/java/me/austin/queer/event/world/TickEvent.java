package me.austin.queer.event.world;

import me.austin.queer.event.Event;

public class TickEvent extends Event {
    public static class Pre extends TickEvent {
        private static Pre INSTANCE = new Pre();

        public static Pre get() {
            if (INSTANCE == null || INSTANCE.isCancelled()) {
                INSTANCE = new TickEvent.Pre();
            }

            return INSTANCE;
        }
    }

    public static class Post extends TickEvent {
        private static Post INSTANCE = new Post();

        public static Post get() {
            if (INSTANCE == null || INSTANCE.isCancelled()) {
                INSTANCE = new TickEvent.Post();
            }

            return INSTANCE;
        }
    }
}