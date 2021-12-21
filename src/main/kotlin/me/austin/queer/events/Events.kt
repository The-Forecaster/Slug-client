package me.austin.queer.events

import me.austin.queer.event.Event
import net.minecraft.network.Packet

open class PacketEvent(packet: Packet<*>) : Event(Stage.POST, Priority.HIGH) {
    val packet : Packet<*> = packet

    fun getPacket() : Packet<*> {
        return packet
    }
    
    class Send(packet : Packet<*>) : PacketEvent(packet)
    class Recieve(packet : Packet<*>) : PacketEvent(packet)
}

open class TickEvent() : Event(Stage.POST, Priority.LOW) {
    class ClientTick() : TickEvent()
    class ServerTick() : TickEvent()
}