package trans.rights.client.modules.hack.impl

import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket
import net.minecraft.util.math.Vec3d
import trans.rights.client.events.PacketEvent
import trans.rights.client.events.TickEvent
import trans.rights.client.misc.api.Globals
import trans.rights.client.misc.api.Globals.mc
import trans.rights.client.modules.hack.Hack
import trans.rights.client.modules.setting.settings.BooleanSetting
import trans.rights.client.modules.setting.settings.DoubleSetting
import trans.rights.event.annotation.EventHandler
import trans.rights.event.listener.impl.LambdaListener
import trans.rights.event.listener.impl.lambdaListener

object FlightHack : Hack("Flight", "Fly using hacks"), Globals {
    private val speed = DoubleSetting("Speed", 15.0)
    private var cancelSpeed = BooleanSetting("Cancel-speed", true)

    @JvmField
    @EventHandler
    var updateListener: LambdaListener<TickEvent.PostTick> = lambdaListener {
        if (!nullCheck()) {
            doFlight()
        }
    }

    @EventHandler
    fun onPacketRecieve(event: PacketEvent) {
        if (event.packet is PlayerAbilitiesS2CPacket) {
            val packet = event.packet as PlayerAbilitiesS2CPacket

            packet.allowFlying = true
            packet.flying = true
            packet.flySpeed = trueSpeed()
        }
    }

    init {
        settings.add(speed)
        settings.add(cancelSpeed)
    }

    override fun onEnable() {
        if (nullCheck()) disable()
    }

    override fun onDisable() {
        if (!nullCheck()) {
            mc.player!!.abilities.allowFlying = false
            mc.player!!.abilities.flySpeed = 0.05f
        }
    }

    private fun doFlight() {
        mc.player!!.run { setFlySpeed(trueSpeed(), cancelSpeed.value) }
    }

    private fun trueSpeed(): Float {
        return speed.value.toFloat() / 10f
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
