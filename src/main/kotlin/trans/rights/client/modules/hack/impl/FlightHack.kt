package trans.rights.client.modules.hack.impl

import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket
import net.minecraft.network.packet.c2s.play.UpdatePlayerAbilitiesC2SPacket
import net.minecraft.util.math.Vec3d
import trans.rights.TransRights
import trans.rights.client.events.PacketEvent
import trans.rights.client.events.TickEvent
import trans.rights.client.misc.api.Globals
import trans.rights.client.misc.api.Globals.mc
import trans.rights.client.modules.hack.Hack
import trans.rights.client.modules.setting.impl.BooleanSetting
import trans.rights.client.modules.setting.impl.NumberSetting
import trans.rights.event.listener.EventHandler
import trans.rights.event.listener.impl.listener
import trans.rights.event.listener.impl.LambdaListener

object FlightHack : Hack("Flight", "Fly using hacks"), Globals {
    private val speed = settings.add(NumberSetting("Speed", "How fast you want to fly.",15.0))
    private val cancelSpeed = settings.add(BooleanSetting("Cancel-speed", "Do you want to cancel current speed before doing adding speed?", true))

    @EventHandler
    val updateListener: LambdaListener<TickEvent.PostTick> = listener {
        if (!nullCheck()) player.setFlySpeed(trueSpeed(), cancelSpeed.value)
    }

    @EventHandler
    val packetReceiveListener: LambdaListener<PacketEvent.PostReceive> = listener { event ->
        if (event.packet is PlayerAbilitiesS2CPacket) {
            (event.packet as PlayerAbilitiesS2CPacket).let { packet ->
                packet.allowFlying = true
                packet.flying = true
                packet.flySpeed = trueSpeed()
            }
        }
    }

    @EventHandler
    val packetSendListener: LambdaListener<PacketEvent.PreSend> = listener { event ->
        if (event.packet is UpdatePlayerAbilitiesC2SPacket) {
            (event.packet as UpdatePlayerAbilitiesC2SPacket).flying = true
        }
    }

    override fun onEnable() {
        // if (nullCheck()) disable()

        TransRights.LOGGER.info("$name enabled")
    }

    override fun onDisable() {
        if (!nullCheck()) {
            if (player.isCreative) player.abilities.allowFlying = false
            player.abilities.flySpeed = 0.05f
            player.abilities.flying = false
        }
    }

    private fun trueSpeed(): Float = speed.value.toFloat() / 10f
}

private fun ClientPlayerEntity.setFlySpeed(speed: Float, cancelSpeed: Boolean) {
    if (!this.isSpectator || mc.world != null) {
        if (cancelSpeed) this.velocity = Vec3d.ZERO

        this.abilities.allowFlying = true
        this.abilities.flying = true
        this.abilities.flySpeed = speed
    }
}
