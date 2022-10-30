package trans.rights.client.events;

import net.minecraft.network.Packet;
import org.jetbrains.annotations.NotNull;
import trans.rights.event.Cancellable;

public abstract class PacketEvent extends Cancellable {
    private Packet<?> packet;

    public PacketEvent(@NotNull Packet<?> packet) {
        this.packet = packet;
    }

    public final @NotNull Packet<?> getPacket() {
        return this.packet;
    }

    protected final void setPacket(@NotNull Packet<?> packet) {
        this.packet = packet;
    }

    public static final class PreSend extends PacketEvent {
        public PreSend(@NotNull Packet<?> packet) {
            super(packet);
        }
    }

    public static final class PreReceive extends PacketEvent {
        public PreReceive(@NotNull Packet<?> packet) {
            super(packet);
        }
    }

    public static final class PostSend extends PacketEvent {
        public PostSend(@NotNull Packet<?> packet) {
            super(packet);
        }
    }

    public static final class PostReceive extends PacketEvent {
        public PostReceive(@NotNull Packet<?> packet) {
            super(packet);
        }
    }
}
