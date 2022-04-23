package trans.rights.client.events;

import net.minecraft.network.Packet;
import trans.rights.event.type.Cancellable;

import javax.annotation.ParametersAreNonnullByDefault;

public abstract class PacketEvent extends Cancellable {
    private Packet<?> packet;

    public final Packet<?> getPacket() {
        return this.packet;
    }

    @ParametersAreNonnullByDefault
    protected final void setPacket(Packet<?> packet) {
        this.packet = packet;
    }

    public static final class PreSend extends PacketEvent {
        private static final PreSend INSTANCE = new PreSend();

        @ParametersAreNonnullByDefault
        public static PreSend get(Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.setPacket(packet);

            return INSTANCE;
        }
    }

    public static final class PreReceive extends PacketEvent {
        private static final PreReceive INSTANCE = new PreReceive();

        @ParametersAreNonnullByDefault
        public static PreReceive get(Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.setPacket(packet);

            return INSTANCE;
        }
    }

    public static final class PostSend extends PacketEvent {
        private static final PostSend INSTANCE = new PostSend();

        @ParametersAreNonnullByDefault
        public static PostSend get(Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.setPacket(packet);

            return INSTANCE;
        }
    }

    public static final class PostReceive extends PacketEvent {
        private static final PostReceive INSTANCE = new PostReceive();

        @ParametersAreNonnullByDefault
        public static PostReceive get(Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.setPacket(packet);

            return INSTANCE;
        }
    }
}
