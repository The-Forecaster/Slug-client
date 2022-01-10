package me.austin.queer.event.events;

import me.austin.queer.event.Event;
import me.austin.queer.event.Priority;
import me.zero.alpine.event.EventState;
import net.minecraft.network.Packet;

public abstract class PacketEvent extends Event {
    private Packet<?> packet;

    protected PacketEvent(EventState state) {
        super(state, Priority.HIGH);
    }

    public Packet<?> getPacket() {
        return this.packet;
    }

    public void setPacket(Packet<?> packet) {
        this.packet = packet;
    }

    public static final class PreSend extends PacketEvent {
        private static final PreSend INSTANCE = new PreSend();

        private PreSend() {
            super(EventState.PRE);
        }

        public static final PreSend get(Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.setPacket(packet);

            return INSTANCE;
        }
    }

    public static final class PreReceive extends PacketEvent {
        private static final PreReceive INSTANCE = new PreReceive();

        private PreReceive() {
            super(EventState.PRE);
        }

        public static final PreReceive get(Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.setPacket(packet);

            return INSTANCE;
        }
    }

    public static final class PostSend extends PacketEvent {
        private static final PostSend INSTANCE = new PostSend();

        private PostSend() {
            super(EventState.POST);
        }

        public static final PostSend get(Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.setPacket(packet);

            return INSTANCE;
        }
    }

    public static final class PostReceive extends PacketEvent {
        private static final PostReceive INSTANCE = new PostReceive();

        private PostReceive() {
            super(EventState.POST);
        }

        public static final PostReceive get(Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.setPacket(packet);

            return INSTANCE;
        }
    }
}
