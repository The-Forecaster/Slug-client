package trans.rights.client.events;

import net.minecraft.network.Packet;
import org.jetbrains.annotations.NotNull;
import trans.rights.event.type.Cancellable;

public abstract class PacketEvent extends Cancellable {
    private Packet<?> packet;

    public final Packet<?> getPacket() {
        return this.packet;
    }

    protected final void setPacket(@NotNull Packet<?> packet) {
        this.packet = packet;
    }

    public static final class PreSend extends PacketEvent {
        private static final PreSend INSTANCE = new PreSend();

        public static PreSend get(@NotNull Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.setPacket(packet);

            return INSTANCE;
        }
    }

    public static final class PreReceive extends PacketEvent {
        private static final PreReceive INSTANCE = new PreReceive();

        public static PreReceive get(@NotNull Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.setPacket(packet);

            return INSTANCE;
        }
    }

    public static final class PostSend extends PacketEvent {
        private static final PostSend INSTANCE = new PostSend();

        public static PostSend get(@NotNull Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.setPacket(packet);

            return INSTANCE;
        }
    }

    public static final class PostReceive extends PacketEvent {
        private static final PostReceive INSTANCE = new PostReceive();

        public static PostReceive get(@NotNull Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.setPacket(packet);

            return INSTANCE;
        }
    }
}
