package trans.rights.client.modules.hack.hacks

import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket
import trans.rights.client.events.PacketEvent
import trans.rights.client.events.TickEvent
import trans.rights.client.misc.api.Globals
import trans.rights.client.misc.api.Globals.mc
import trans.rights.client.modules.hack.Hack
import trans.rights.client.util.player.*
import trans.rights.event.annotation.EventHandler
import trans.rights.event.listener.impl.LambdaListener
import trans.rights.event.listener.impl.lambdaListener

object FlightHack : Hack("Flight", "Fly using hacks"), Globals {
    private var vanilla = true
    private var speed = 10.0f
    private var withElytra = false
    private var cancelSpeed = false

    @EventHandler
    val updateListener: LambdaListener<TickEvent.PostTick> = lambdaListener({ event ->
        if (!this.nullCheck() && mc.player!!.isFallFlying && !this.withElytra || !event.isInWorld) {
            when (this.vanilla) {
                true -> this.doVanillaFlight()
                false -> this.doVelocity()
            }
        }
    }, -50)

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
        settings["Vanilla"] = this.vanilla
        settings["Flight-speed"] = this.speed
        settings["With-elytra"] = this.withElytra
        settings["Cancel-speed"] = this.cancelSpeed
    }

    override fun onEnable() {
        if (nullCheck()) this.disable()
    }

    override fun onDisable() {
        mc.player!!.abilities.allowFlying = false
        mc.player!!.abilities.flySpeed = 0.05f
    }

    private fun doVanillaFlight() {
        mc.player!!.setFlySpeed(trueSpeed(), cancelSpeed)
    }

    private fun doVelocity() {
        mc.player!!.setVelocity(trueSpeed(), cancelSpeed)
    }

    private fun trueSpeed(): Float {
        return this.speed / 10
    }
}
