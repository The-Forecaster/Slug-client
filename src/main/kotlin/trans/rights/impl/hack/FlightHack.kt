package trans.rights.impl.hack

import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.network.packet.c2s.play.UpdatePlayerAbilitiesC2SPacket
import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket
import net.minecraft.util.math.Vec3d
import trans.rights.Queer
import trans.rights.api.hack.Hack
import trans.rights.events.PacketEvent
import trans.rights.events.TickEvent
import trans.rights.impl.setting.FloatSetting
import trans.rights.impl.setting.Settings
import me.austin.rush.listener

object FlightHack : Hack("Flight", "Fly using hacks") {
    private val speed = FloatSetting("Speed", "How fast you want to fly.", 15f, 0.1f)
    // private val cancelSpeed = settings.add(BooleanSetting("Cancel-speed", "Do you want to cancel current speed before doing adding speed?", true))

    override val settings = Settings(speed)

    override val listeners = listOf(
        listener<TickEvent.Post> {
            if (!nullCheck()) player!!.setFlySpeed(trueSpeed(), true)
        },
        listener<PacketEvent.PostReceive> { event ->
            if (event.packet is PlayerAbilitiesS2CPacket) (event.packet as PlayerAbilitiesS2CPacket).let {
                it.allowFlying = true
                it.flying = true
                it.flySpeed = trueSpeed()
            }
        },
        listener<PacketEvent.PreSend> { event ->
            if (event.packet is UpdatePlayerAbilitiesC2SPacket) {
                (event.packet as UpdatePlayerAbilitiesC2SPacket).flying = true
            }
        }
    )

    private fun trueSpeed() = (speed.value / 10f)

    override fun onEnable() {
        if (nullCheck()) disable()

        Queer.LOGGER.info("$name enabled")
    }

    override fun onDisable() {
        player?.let {
            if (!it.isCreative) it.abilities.allowFlying = false
            it.abilities.flySpeed = 0.05f
            it.abilities.flying = false
        }
    }
}

private fun ClientPlayerEntity.setFlySpeed(speed: Float, cancelSpeed: Boolean) {
    if (!this.isSpectator || MinecraftClient.getInstance().world != null) {
        if (cancelSpeed) this.velocity = Vec3d.ZERO

        this.abilities.allowFlying = true
        this.abilities.flying = true
        this.abilities.flySpeed = speed
    }
}