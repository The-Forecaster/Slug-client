package me.austin.client.events;

import org.jetbrains.annotations.NotNull;

import me.austin.rush.Cancellable;
import net.minecraft.network.Packet;

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
