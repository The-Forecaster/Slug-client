package me.austin.queer.event.events;

import me.austin.queer.event.Event;
import net.minecraft.network.Packet;

public class PacketEvent extends Event {
    Packet packet;
    
    public PacketEvent(Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return this.packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    public static class Send extends PacketEvent {
        public Send(Packet packet) {
            super(packet);
        }
    }

    public static class Recieve extends PacketEvent {
        public Recieve(Packet packet) {
            super(packet);
        }
    }
}
