package me.austin.queer.events

import me.austin.queer.event.*
import me.zero.alpine.event.EventState
import net.minecraft.network.Packet

open class PacketEvent(stage: EventState, val packet: Packet<*>) : Event(stage, Priority.HIGH) {
    class Send(packet: Packet<*>) : PacketEvent(EventState.PRE, packet)
    class Recieve(packet: Packet<*>) : PacketEvent(EventState.PRE, packet)
    class PostSend(packet: Packet<*>) : PacketEvent(EventState.POST, packet)
    class PostRecieve(packet: Packet<*>) : PacketEvent(EventState.POST, packet)
}

open class TickEvent(stage: EventState, val inworld: Boolean,) : Event(stage, Priority.LOW) {
    class Pre(inworld: Boolean) : TickEvent(EventState.PRE, inworld)
    class Post(inworld: Boolean): TickEvent(EventState.POST, inworld)
}