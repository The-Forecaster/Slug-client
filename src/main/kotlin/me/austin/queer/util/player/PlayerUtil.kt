package me.austin.queer.util.player

import me.austin.queer.Globals.mc

import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket
import net.minecraft.util.math.Vec3d
import net.minecraft.client.network.ClientPlayerEntity

object PlayerUtil {
    @JvmStatic
    fun setFlySpeed(speed: Float, cancelSpeed : Boolean) {
        val player = mc.player as ClientPlayerEntity

        if (playerCheck(player)) return
        if (cancelSpeed) player.setVelocity(Vec3d.ZERO)

        player.abilities.allowFlying = true
        player.abilities.flySpeed = speed
    }

    @JvmStatic
    fun setVelocity(speed: Float, cancelSpeed : Boolean) {
        val player = mc.player as ClientPlayerEntity
        val initialVelocity = player.getVelocity()

        if (playerCheck(player)) return
        if (cancelSpeed) player.setVelocity(Vec3d.ZERO)
        if (mc.options.keyJump.isPressed) player.setVelocity(initialVelocity?.add(0.0, 5.0, 0.0))
        if (mc.options.keySneak.isPressed) player.setVelocity(initialVelocity?.subtract(0.0, 5.0, 0.0))
        if (mc.options.keyForward.isPressed) player.addVelocity(speed.toDouble(), 0.0, 0.0)
        if (mc.options.keyBack.isPressed) player.addVelocity(-speed.toDouble(), 0.0, 0.0)
        if (mc.options.keyLeft.isPressed) player.addVelocity(0.0, 0.0, speed.toDouble())
        if (mc.options.keyRight.isPressed) player.addVelocity(0.0, 0.0, -speed.toDouble())

        player.setVelocity(initialVelocity.add(speed.toDouble(), 0.0, 0.0))
    }
    
    @JvmStatic
    private fun playerCheck(player : ClientPlayerEntity) : Boolean {
        return mc.world != null && player.isSpectator
    }
}