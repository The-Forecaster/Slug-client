package me.austin.queer.util

import me.austin.queer.misc.Globals.mc

import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket
import net.minecraft.util.math.Vec3d
import net.minecraft.client.network.ClientPlayerEntity

object PlayerUtil {
    @JvmStatic
    fun setPlayerSpeed(speed: Float) {
        if (mc.player!!.isSpectator) return

        mc.player?.abilities?.allowFlying = true
        mc.player?.setVelocity(Vec3d.ZERO)
        mc.player?.abilities?.flySpeed = speed
    }

    @JvmStatic
    fun setVelocity(speed: Float) {
        mc.player!!.setVelocity(0.0, 0.0, 0.0)

        val player = mc.player as ClientPlayerEntity

        val initialVelocity = player.getVelocity()
        val sped = speed.toDouble()

        if (mc.options.keyJump.isPressed()) player.setVelocity(initialVelocity?.add(0.0, 5.0, 0.0))
        if (mc.options.keySneak.isPressed()) player.setVelocity(initialVelocity?.subtract(0.0, 5.0, 0.0))

        mc.player?.setVelocity(sped, sped, sped);
    }
}