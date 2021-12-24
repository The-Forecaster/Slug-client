package me.austin.queer.features

import me.zero.alpine.listener.EventHandler
import me.zero.alpine.listener.Listener

import me.austin.queer.misc.Globals.mc
import me.austin.queer.feature.hack.Hack
import me.austin.queer.events.TickEvent
import me.austin.queer.events.PacketEvent

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
    var forward = mc.options.keyForward.isPressed
    var left = mc.options.keyLeft.isPressed
    var right = mc.options.keyRight.isPressed
    var back = mc.options.keyBack.isPressed
    var jump = mc.options.keyJump.isPressed
    var crouch = mc.options.keySneak.isPressed

    var forwardspeed = when (forward and back) {
        true -> 0
        false -> {
            if (forward) speed
            else -speed
        }
    }

    val leftspeed = when (left and right) {
        true -> 0
        false -> {
            if (left) speed
            else -speed
        }
    }

    val upspeed = when (jump and crouch) {
        true -> 0
        false -> {
            if (jump) speed
            else -speed
        }
    }

    mc.player?.setVelocity(forwardspeed.toDouble(), leftspeed.toDouble(), upspeed.toDouble())
}

private fun setAbilities(packet: Packet<*>) {
    mc.networkHandler?.sendPacket(packet)
}

private fun setVelocity(packet: Packet<*>) {
    mc.networkHandler?.sendPacket(packet)
}

public object Flight : Hack("Flight") {
    private var mode = FlightMode.VANILLA
    private var speed = 10.0f

    fun Flight() {
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
        if (it.packet !is PlayerAbilitiesS2CPacket) return@Listener

        when (mode) {
            FlightMode.VANILLA -> {
                setAbilities(it.packet)
            }
            FlightMode.VELOCITY -> {
                setVelocity(it.packet)
            }
            FlightMode.BOTH -> {
                setAbilities(it.packet)
                setVelocity(it.packet)
            }
        }
    })

    override fun onDisable() {
        mc.player?.abilities?.allowFlying = false
    } 
}