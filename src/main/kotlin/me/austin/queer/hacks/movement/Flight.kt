package me.austin.queer.features

import me.zero.alpine.listener.EventHandler
import me.zero.alpine.listener.Listener

import me.austin.queer.misc.Globals.mc
import me.austin.queer.hacks.Hack
import me.austin.queer.hacks.Category
import me.austin.queer.event.events.TickEvent
import me.austin.queer.event.events.PacketEvent

import net.minecraft.entity.player.PlayerAbilities
import net.minecraft.entity.EntityPose
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket
import net.minecraft.util.math.Vec3d

private enum class FlightMode {
    VANILLA,
    VELOCITY,
    BOTH
}

private fun setAbilities(speed: Float) {
    if (mc.player!!.isSpectator) return

    mc.player?.abilities?.allowFlying = true
    mc.player?.setVelocity(Vec3d.ZERO)
    mc.player?.abilities?.flySpeed = speed
}

private fun setVelocity(speed: Float) {
    mc.player?.setVelocity(0.0, 0.0, 0.0)

    var initialVelocity = mc.player?.getVelocity()
    var sped = speed.toDouble()

    if (mc.options.keyJump.isPressed()) mc.player?.setVelocity(initialVelocity?.add(0.0, 5.0, 0.0))
    if (mc.options.keySneak.isPressed()) mc.player?.setVelocity(initialVelocity?.subtract(0.0, 5.0, 0.0))

    mc.player?.setVelocity(sped, sped, sped);
}

private fun setAbilities(packet: Packet<*>) {
    mc.networkHandler?.sendPacket(packet)
}

private fun setVelocity(packet: Packet<*>) {
    if (packet is EntityVelocityUpdateS2CPacket)

    mc.networkHandler?.sendPacket(packet)
}

public object Flight : Hack("Flight", Category.MOVEMENT) {
    private var mode = FlightMode.VANILLA
    private var speed = 10.0f

    init {
        settings.put("Mode", mode)
        settings.put("Flight-speed", speed)
    }
    
    @EventHandler
    val updateListener = Listener<TickEvent.Post> ({
        if (mc.player == null || mc.world == null) return@Listener

        when (mode) {
            FlightMode.VANILLA -> {
                setAbilities(speed)
            }
            FlightMode.VELOCITY -> {
                setVelocity(speed)
            }
            FlightMode.BOTH -> {
                setAbilities(speed)
                setVelocity(speed)
            }
        }
    })

    @EventHandler
    private val packetlistener = Listener<PacketEvent.Recieve> ({
        if (it.PACKET !is PlayerAbilitiesS2CPacket) return@Listener

        when (mode) {
            FlightMode.VANILLA -> {
                setAbilities(it.PACKET)
            }
            FlightMode.VELOCITY -> {
                setVelocity(it.PACKET)
            }
            FlightMode.BOTH -> {
                setAbilities(it.PACKET)
                setVelocity(it.PACKET)
            }
        }
    })

    override fun onDisable() {
        mc.player?.abilities?.allowFlying = false
        mc.player?.abilities?.flySpeed = 0.05f
    } 
}