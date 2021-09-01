package me.austin.queer.event.world;

import me.austin.queer.event.Event;

public class TickEvent extends Event {
    public static class Pre extends TickEvent {
        private static Pre instance = new Pre();

        public Pre getIntance() {
            return instance;
        }
    }

    public static class Post extends TickEvent {
        private static Post instance = new Post();

        public Post getInstance() {
            return instance;
        }
    }
}