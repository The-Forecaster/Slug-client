package me.austin.queer.util.player

import me.austin.queer.Globals.mc
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.util.math.Vec3d

object PlayerUtil {
    @JvmStatic
    fun setFlySpeed(speed: Float, cancelSpeed: Boolean) {
        val player = mc.player as ClientPlayerEntity

        if (playerCheck(player)) return
        if (cancelSpeed) player.setVelocity(Vec3d.ZERO)

        player.abilities.allowFlying = true
        player.abilities.flySpeed = speed
    }

    @JvmStatic
    fun setVelocity(speed: Float, cancelSpeed: Boolean) {
        val player = mc.player as ClientPlayerEntity

        // this is retarted but i don't think there's a better way than this
        if (playerCheck(player)) return
        if (cancelSpeed) player.setVelocity(Vec3d.ZERO)
        if (mc.options.keyJump.isPressed) player.addVelocity(0.0, 5.0, 0.0))
        if (mc.options.keySneak.isPressed) player.addVelocity(0.0, -5.0, 0.0))
        if (mc.options.keyForward.isPressed) player.addVelocity(speed.toDouble(), 0.0, 0.0)
        if (mc.options.keyBack.isPressed) player.addVelocity(-speed.toDouble(), 0.0, 0.0)
        if (mc.options.keyLeft.isPressed) player.addVelocity(0.0, 0.0, speed.toDouble())
        if (mc.options.keyRight.isPressed) player.addVelocity(0.0, 0.0, -speed.toDouble())
    }

    @JvmStatic
    private fun playerCheck(player: ClientPlayerEntity): Boolean {
        return mc.world != null && player.isSpectator
    }
}
