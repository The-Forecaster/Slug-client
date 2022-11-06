package trans.rights.client.events;

import net.minecraft.network.Packet;
import org.jetbrains.annotations.NotNull;
import trans.rights.event.Cancellable;

public abstract class PacketEvent extends Cancellable {
    private final Packet<?> packet;

    public PacketEvent(@NotNull Packet<?> packet) {
        this.packet = packet;
    }

    public final @NotNull Packet<?> getPacket() {
        return this.packet;
    }

    public static final class PreSend extends PacketEvent {
        public PreSend(@NotNull Packet<?> packet) {
            super(packet);
        }
    }

    public static final class PostReceive extends PacketEvent {
        public PostReceive(@NotNull Packet<?> packet) {
            super(packet);
        }
    }
}
