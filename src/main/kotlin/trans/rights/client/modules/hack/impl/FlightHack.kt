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
import trans.rights.client.modules.setting.settings.NumberSetting
import trans.rights.event.annotation.EventHandler
import trans.rights.event.listener.impl.*

object FlightHack : Hack("Flight", "Fly using hacks"), Globals {
    private val speed = NumberSetting("Speed", 15.0)
    private var cancelSpeed = BooleanSetting("Cancel-speed", true)

    @EventHandler
    val updateListener: LambdaListener<TickEvent.PostTick> = lambdaListener {
        if (!nullCheck()) player.setFlySpeed(trueSpeed(), cancelSpeed.value)
    }

    @EventHandler
    val packetListener: LambdaListener<PacketEvent.PostReceive> = lambdaListener { event ->
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

        this.enable()
    }

    override fun onEnable() {
        // if (nullCheck()) disable()
    }

    override fun onDisable() {
        if (!nullCheck()) {
            player.abilities.allowFlying = false
            player.abilities.flySpeed = 0.05f
        }
    }

    private fun trueSpeed(): Float {
        return speed.value.toFloat() / 10f
    }
}

private fun ClientPlayerEntity.setFlySpeed(speed: Float, cancelSpeed: Boolean) {
    if (!this.isSpectator || mc.world != null) {
        if (cancelSpeed) this.velocity = Vec3d.ZERO

        this.abilities.allowFlying = true
        this.abilities.flySpeed = speed
    }
}
