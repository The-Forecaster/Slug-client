package trans.rights.client.events;

import net.minecraft.network.Packet;
import type.Cancellable;

public abstract class PacketEvent extends Cancellable {
    private Packet<?> packet;

    public final Packet<?> getPacket() {
        return this.packet;
    }

    public final void setPacket(Packet<?> packet) {
        this.packet = packet;
    }

    public static final class PreSend extends PacketEvent {
        private static final PreSend INSTANCE = new PreSend();

        public static PreSend get(Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.setPacket(packet);

            return INSTANCE;
        }
    }

    public static final class PreReceive extends PacketEvent {
        private static final PreReceive INSTANCE = new PreReceive();

        public static PreReceive get(Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.setPacket(packet);

            return INSTANCE;
        }
    }

    public static final class PostSend extends PacketEvent {
        private static final PostSend INSTANCE = new PostSend();

        public static PostSend get(Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.setPacket(packet);

            return INSTANCE;
        }
    }

    public static final class PostReceive extends PacketEvent {
        private static final PostReceive INSTANCE = new PostReceive();

        public static PostReceive get(Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.setPacket(packet);

            return INSTANCE;
        }
    }
}
