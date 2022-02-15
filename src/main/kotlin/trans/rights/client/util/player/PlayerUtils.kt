package trans.rights.client.util.player

import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.util.math.Vec3d
import trans.rights.client.misc.api.Globals.mc

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
        if (mc.options.keyJump.isPressed) this.addVelocity(0.0, 5.0, 0.0)
        if (mc.options.keySneak.isPressed) this.addVelocity(0.0, -5.0, 0.0)
        if (mc.options.keyForward.isPressed) this.addVelocity(speed.toDouble(), 0.0, 0.0)
        if (mc.options.keyBack.isPressed) this.addVelocity(-speed.toDouble(), 0.0, 0.0)
        if (mc.options.keyLeft.isPressed) this.addVelocity(0.0, 0.0, speed.toDouble())
        if (mc.options.keyRight.isPressed) this.addVelocity(0.0, 0.0, -speed.toDouble())
    }
}
