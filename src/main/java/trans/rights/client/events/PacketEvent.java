package trans.rights.client.events;

import net.minecraft.network.Packet;
import trans.rights.event.listener.Event;

public abstract class PacketEvent extends Event {
    private Packet<?> packet;

    public Packet<?> getPacket() {
        return this.packet;
    }

    public void setPacket(Packet<?> packet) {
        this.packet = packet;
    }

    public static final class PreSend extends PacketEvent {
        private static final PreSend INSTANCE = new PreSend();

        public static final PreSend get(Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.setPacket(packet);

            return INSTANCE;
        }
    }

    public static final class PreReceive extends PacketEvent {
        private static final PreReceive INSTANCE = new PreReceive();

        public static final PreReceive get(Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.setPacket(packet);

            return INSTANCE;
        }
    }

    public static final class PostSend extends PacketEvent {
        private static final PostSend INSTANCE = new PostSend();

        public static final PostSend get(Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.setPacket(packet);

            return INSTANCE;
        }
    }

    public static final class PostReceive extends PacketEvent {
        private static final PostReceive INSTANCE = new PostReceive();

        public static final PostReceive get(Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.setPacket(packet);

            return INSTANCE;
        }
    }
}
