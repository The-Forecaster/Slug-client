package me.austin.client.impl.hack

import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.network.packet.c2s.play.UpdatePlayerAbilitiesC2SPacket
import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket
import net.minecraft.util.math.Vec3d
import me.austin.client.Slug.Companion.LOGGER
import me.austin.client.api.hack.Hack
import me.austin.client.events.PacketEvent
import me.austin.client.events.TickEvent
import me.austin.client.impl.setting.FloatSetting
import me.austin.client.impl.setting.Settings
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
            if (event.packet is UpdatePlayerAbilitiesC2SPacket) (event.packet as UpdatePlayerAbilitiesC2SPacket).flying = true
        }
    )

    private fun trueSpeed() = (speed.value / 10f)

    override fun onEnable() {
        if (nullCheck()) disable()

        LOGGER.info("$name enabled")
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