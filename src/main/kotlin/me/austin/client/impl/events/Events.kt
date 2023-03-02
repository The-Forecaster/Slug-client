package me.austin.client.impl.events

import me.austin.rush.Cancellable
import net.minecraft.network.Packet

data class KeyEvent(val key: Int) : Cancellable()

data class TickEvent(val isInWorld: Boolean)

abstract class PacketEvent(val packet: Packet<*>) : Cancellable() {
    class PreSend(packet: Packet<*>) : PacketEvent(packet)

    class PostReceive(packet: Packet<*>) : PacketEvent(packet)
}