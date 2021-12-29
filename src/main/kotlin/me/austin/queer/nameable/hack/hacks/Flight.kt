package me.austin.queer.nameable.hack.hacks

import me.zero.alpine.listener.EventHandler
import me.zero.alpine.listener.Listener

import me.austin.queer.misc.Globals.mc

import me.austin.queer.nameable.hack.Hack
import me.austin.queer.nameable.hack.Category
import me.austin.queer.event.events.TickEvent
import me.austin.queer.event.events.PacketEvent
import me.austin.queer.util.PlayerUtil

import net.minecraft.entity.player.PlayerAbilities
import net.minecraft.entity.EntityPose

import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket
import net.minecraft.network.Packet
import net.minecraft.util.math.Vec3d

private enum class FlightMode {
    VANILLA,
    VELOCITY
}

public object Flight : Hack("Flight", Category.MOVEMENT) {
    private var mode = FlightMode.VANILLA
    private var speed = 10.0f
    private var withElytra = false

    init {
        settings.put("Mode", mode)
        settings.put("Flight-speed", speed)
        settings.put("With-elytra", withElytra)
    }
    
    @EventHandler
    val updateListener = Listener<TickEvent.Post> ({
        if (mc.player == null || mc.world == null) return@Listener

        when (mode) {
            FlightMode.VANILLA -> {
                PlayerUtil.setPlayerSpeed(speed)
            }

            FlightMode.VELOCITY -> {
                PlayerUtil.setVelocity(speed)
            }
        }
    })

    @EventHandler
    val sendListener = Listener<PacketEvent.Send> ({
        if (it.PACKET !is PlayerMoveC2SPacket) return@Listener

        val packet = it.PACKET
    })

    override fun onDisable() {
        mc.player?.abilities?.allowFlying = false
        mc.player?.abilities?.flySpeed = 0.05f
    } 
}