package me.austin.queer.event.events;

import me.austin.queer.event.Event;
import me.austin.queer.event.Priority;
import me.zero.alpine.event.EventState;
import net.minecraft.network.Packet;

public class PacketEvent extends Event {
    public final Packet<?> PACKET;

    protected PacketEvent(EventState state, Packet<?> packet) {
        super(state, Priority.HIGH);
        this.PACKET = packet;
    }

    public static class Send extends PacketEvent {
        public Send(Packet<?> packet) {
            super(EventState.PRE, packet);
        }
    }

    public static class Recieve extends PacketEvent {
        public Recieve(Packet<?> packet) {
            super(EventState.PRE, packet);
        }
    }

    public static class PostSend extends PacketEvent {
        public PostSend(Packet<?> packet) {
            super(EventState.POST, packet);
        }
    }

    public static class PostRecieve extends PacketEvent {
        public PostRecieve(Packet<?> packet) {
            super(EventState.POST, packet);
        }
    }
}
