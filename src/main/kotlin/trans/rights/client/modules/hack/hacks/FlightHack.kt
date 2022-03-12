package trans.rights.client.modules.hack.hacks

import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket
import net.minecraft.util.math.Vec3d
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
    private var cancelSpeed = false

    @EventHandler
    val updateListener: LambdaListener<TickEvent.PostTick> = lambdaListener({ event ->
        if (!this.nullCheck() && mc.player!!.isFallFlying || !event.isInWorld) {
            when (this.vanilla) {
                true -> this.doVanillaFlight()
                false -> this.doVelocity()
            }
        }
    })

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
        this.settings["Vanilla"] = this.vanilla
        this.settings["Flight-speed"] = this.speed
        this.settings["Cancel-speed"] = this.cancelSpeed
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

fun ClientPlayerEntity.setFlySpeed(speed: Float, cancelSpeed: Boolean) {
    if (!this.isSpectator || mc.world != null) {
        if (cancelSpeed) this.velocity = Vec3d.ZERO

        this.abilities.allowFlying = true
        this.abilities.flySpeed = speed
    }
}

fun ClientPlayerEntity.setVelocity(speed: Float, cancelSpeed: Boolean) {
    if (cancelSpeed) this.velocity = Vec3d.ZERO

    if (!this.isSpectator && mc.world != null) {

        // this is retarded, but I don't think there's a better way than this
        if (cancelSpeed) this.velocity = Vec3d.ZERO
        if (mc.options.jumpKey.isPressed) this.addVelocity(0.0, 5.0, 0.0)
        if (mc.options.sneakKey.isPressed) this.addVelocity(0.0, -5.0, 0.0)
        if (mc.options.forwardKey.isPressed) this.addVelocity(speed.toDouble(), 0.0, 0.0)
        if (mc.options.backKey.isPressed) this.addVelocity(-speed.toDouble(), 0.0, 0.0)
        if (mc.options.leftKey.isPressed) this.addVelocity(0.0, 0.0, speed.toDouble())
        if (mc.options.rightKey.isPressed) this.addVelocity(0.0, 0.0, -speed.toDouble())
    }
}
