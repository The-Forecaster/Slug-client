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

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket
import net.minecraft.util.math.Vec3d

public object Flight : Hack("Flight", Category.MOVEMENT) {
    private var mode = FlightMode.VANILLA
    private var speed = 10.0f
    private var withElytra = false
    
    @EventHandler
    val updateListener = Listener<TickEvent.Post> ({
        if (mc.player == null || mc.world == null) return@Listener
        if (mc.player!!.isFallFlying() && withElytra) return@Listener

        when (mode) {
            FlightMode.VANILLA -> {
                mc.player?.abilities?.flySpeed = speed
                mc.player?.abilities?.allowFlying = true

                mc.player?.sendAbilitiesUpdate()
            }

            FlightMode.VELOCITY -> {
                mc.player?.setVelocity(Vec3d.ZERO)

                var forward = Vec3d(speed.toDouble(), 0.0, 0.0).rotateY(mc.player!!.getYaw(mc.tickDelta))

                mc.player?.setVelocity(forward)
            }
        }
    })

    @EventHandler
    val sendListener = Listener<PacketEvent.Send> ({
        if (it.PACKET !is PlayerMoveC2SPacket) return@Listener

        val packet : PlayerMoveC2SPacket = it.PACKET
        packet.onGround = false
    })
    
    init {
        settings.put("Mode", mode)
        settings.put("Flight-speed", speed)
        settings.put("With-elytra", withElytra)
    }

    override fun onDisable() {
        mc.player?.abilities?.allowFlying = false
        mc.player?.abilities?.flySpeed = 0.05f
    } 
    
    private enum class FlightMode {
        VANILLA,
        VELOCITY
    }
}