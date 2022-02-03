package trans.rights.client.util.player

import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.util.math.Vec3d
import trans.rights.client.misc.Globals.mc

fun setFlySpeed(speed: Float, cancelSpeed: Boolean) {
    val player = mc.player as ClientPlayerEntity

    if (playerCheck(player)) {
        if (cancelSpeed) player.velocity = Vec3d.ZERO

        player.abilities.allowFlying = true
        player.abilities.flySpeed = speed
    }
}

fun setVelocity(speed: Float, cancelSpeed: Boolean) {
    val player: ClientPlayerEntity = mc.player as ClientPlayerEntity

    if (!playerCheck(player)) {

        // this is retarded but I don't think there's a better way than this
        if (cancelSpeed) player.velocity = Vec3d.ZERO
        if (mc.options.keyJump.isPressed) player.addVelocity(0.0, 5.0, 0.0)
        if (mc.options.keySneak.isPressed) player.addVelocity(0.0, -5.0, 0.0)
        if (mc.options.keyForward.isPressed) player.addVelocity(speed.toDouble(), 0.0, 0.0)
        if (mc.options.keyBack.isPressed) player.addVelocity(-speed.toDouble(), 0.0, 0.0)
        if (mc.options.keyLeft.isPressed) player.addVelocity(0.0, 0.0, speed.toDouble())
        if (mc.options.keyRight.isPressed) player.addVelocity(0.0, 0.0, -speed.toDouble())
    }
}

private fun playerCheck(player: ClientPlayerEntity) = mc.player != null && mc.world != null && player.isSpectator
