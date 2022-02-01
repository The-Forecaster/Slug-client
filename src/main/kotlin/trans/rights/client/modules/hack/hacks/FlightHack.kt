package trans.rights.client.modules.hack.hacks

import me.zero.alpine.listener.EventHandler
import me.zero.alpine.listener.Listener
import trans.rights.client.Globals.mc
import trans.rights.client.Globals.nullCheck
import trans.rights.client.events.TickEvent
import trans.rights.client.modules.hack.Hack
import trans.rights.client.util.player.*

object FlightHack : Hack("Flight", "Fly using hacks") {
    private var vanilla = true
    private var speed = 10.0f
    private var withElytra = false
    private var cancelSpeed = false

    @EventHandler
    private val updateListener = Listener<TickEvent.PostTick>({
        when (vanilla) {
            true -> this.doVanillaFlight()
            false -> this.doVelocity()
        }
    }, { event ->
        !nullCheck() && 
        mc.player!!.isFallFlying() && 
        withElytra || 
        event.isInWorld()
    })

    init {
        settings.put("Vanilla", vanilla)
        settings.put("Flight-speed", speed)
        settings.put("With-elytra", withElytra)
        settings.put("Canel-speed", cancelSpeed)
    }

    internal fun doVanillaFlight() {
        setFlySpeed(trueSpeed(), cancelSpeed)
    }

    internal fun doVelocity() {
        setVelocity(trueSpeed(), cancelSpeed)
    }

    private fun trueSpeed(): Float {
        return speed / 10
    }

    override fun onDisable() {
        mc.player!!.abilities.allowFlying = false
        mc.player!!.abilities.flySpeed = 0.05f
    }
}
