package trans.rights.client.events;

import net.minecraft.network.Packet;
import org.jetbrains.annotations.NotNull;
import trans.rights.event.type.Cancellable;

public abstract class PacketEvent extends Cancellable {
    private Packet<?> packet;

    public PacketEvent(Packet<?> packet) {
        this.packet = packet;
    }

    @NotNull
    public final Packet<?> getPacket() {
        return this.packet;
    }

    protected final void setPacket(@NotNull Packet<?> packet) {
        this.packet = packet;
    }

    public static final class PreSend extends PacketEvent {
        public PreSend(Packet<?> packet) {
            super(packet);
        }
    }

    public static final class PreReceive extends PacketEvent {
        public PreReceive(Packet<?> packet) {
            super(packet);
        }
    }

    public static final class PostSend extends PacketEvent {
        public PostSend(Packet<?> packet) {
            super(packet);
        }
    }

    public static final class PostReceive extends PacketEvent {
        public PostReceive(Packet<?> packet) {
            super(packet);
        }
    }
}
