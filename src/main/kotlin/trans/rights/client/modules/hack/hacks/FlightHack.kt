package trans.rights.client.modules.hack.hacks

import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket
import trans.rights.client.events.PacketEvent
import trans.rights.client.events.TickEvent
import trans.rights.client.misc.Globals
import trans.rights.client.misc.Globals.mc
import trans.rights.client.modules.hack.Hack
import trans.rights.client.util.player.*
import trans.rights.event.annotation.EventHandler
import trans.rights.event.annotation.EventListener
import trans.rights.event.listener.impl.LambdaListener

object FlightHack : Hack("Flight", "Fly using hacks"), Globals {
    private var vanilla = true
    private var speed = 10.0f
    private var withElytra = false
    private var cancelSpeed = false

    @EventListener
    val updateListener = LambdaListener({ event ->
        if (!nullCheck() && mc.player!!.isFallFlying && !withElytra || !event.isInWorld)
            when (vanilla) {
                true -> this.doVanillaFlight()
                false -> this.doVelocity()
            }   
        }, 
        -50,
        this,
        TickEvent.PostTick::class.java
    )

    @EventHandler
    fun onPacketRecieve(event: PacketEvent) {
        if (event.packet is PlayerAbilitiesS2CPacket) {
            val packet = event.packet as PlayerAbilitiesS2CPacket

            packet.allowFlying = true
            packet.flying = true
            packet.flySpeed = this.speed
        }
    }

    init {
        settings["Vanilla"] = vanilla
        settings["Flight-speed"] = speed
        settings["With-elytra"] = withElytra
        settings["Cancel-speed"] = cancelSpeed
    }

    override fun onDisable() {
        mc.player!!.abilities.allowFlying = false
        mc.player!!.abilities.flySpeed = 0.05f
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
}
