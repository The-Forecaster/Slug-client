package me.austin.queer.nameable.hack.hacks

import me.austin.queer.Globals.mc
import me.austin.queer.event.events.PacketEvent
import me.austin.queer.event.events.TickEvent
import me.austin.queer.nameable.hack.*
import me.austin.queer.util.player.PlayerUtil
import me.zero.alpine.listener.EventHandler
import me.zero.alpine.listener.Listener
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket

object FlightHack : Hack("Flight", Category.MOVEMENT) {
    private var mode = FlightMode.VANILLA
    private var speed = 10.0f
    private var withElytra = false
    private var cancelSpeed = false

    private var player = mc.player as ClientPlayerEntity

    @EventHandler
    val updateListener = Listener<TickEvent.PostTick>({
        if (mc.player == null || mc.world == null) return@Listener
        if (mc.player!!.isFallFlying() && withElytra) return@Listener

        when (mode) {
            FlightMode.VANILLA -> {
                doVanillaFlight()
            }
            FlightMode.VELOCITY -> {
                doVelocity()
            }
            FlightMode.BOTH -> {
                doVanillaFlight()
                doVelocity()
            }
        }
    })

    @EventHandler
    val sendListener = Listener<PacketEvent.PreSend>({
        if (it.getPacket() !is PlayerMoveC2SPacket) return@Listener

        val packet = it.getPacket() as PlayerMoveC2SPacket
        packet.onGround = false
    })

    init {
        settings.put("Mode", mode)
        settings.put("Flight-speed", speed)
        settings.put("With-elytra", withElytra)
        settings.put("Canel-speed", cancelSpeed)
    }

    private fun doVanillaFlight() {
        PlayerUtil.setFlySpeed(speed, cancelSpeed)
    }

    private fun doVelocity() {
        PlayerUtil.setVelocity(speed, cancelSpeed)
    }

    override fun onDisable() {
        player.abilities.allowFlying = false
        player.abilities.flySpeed = 0.05f
    }

    private enum class FlightMode {
        VANILLA,
        VELOCITY,
        BOTH
    }
}
