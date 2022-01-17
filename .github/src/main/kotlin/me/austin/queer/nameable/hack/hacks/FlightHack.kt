package me.austin.queer.nameable.hack.hacks

import me.austin.queer.Globals.mc
import me.austin.queer.event.events.TickEvent
import me.austin.queer.nameable.hack.Hack
import me.austin.queer.util.player.*
import me.zero.alpine.listener.EventHandler
import me.zero.alpine.listener.Listener

object FlightHack : Hack("Flight") {
    private var mode = FlightMode.VANILLA
    private var speed = 10.0f
    private var withElytra = false
    private var cancelSpeed = false

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
        }
    })

    init {
        settings.put("Mode", mode)
        settings.put("Flight-speed", speed)
        settings.put("With-elytra", withElytra)
        settings.put("Canel-speed", cancelSpeed)
    }

    private fun doVanillaFlight() {
        setFlySpeed(trueSpeed(), cancelSpeed)
    }

    private fun doVelocity() {
        setVelocity(trueSpeed(), cancelSpeed)
    }

    private fun trueSpeed(): Float {
        return speed / 10
    }

    override fun onDisable() {
        mc.player!!.abilities.allowFlying = false
        mc.player!!.abilities.flySpeed = 0.05f
    }

    private enum class FlightMode {
        VANILLA,
        VELOCITY
    }
}
