package me.austin.queer.modules.hacks.movement;

import me.austin.queer.modules.hacks.Category;
import me.austin.queer.modules.hacks.Hack;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.util.math.Vec3d;

@Hack.Register(name = "Anti-Knockback", description = "Stops things from knocking you back", category = Category.MOVEMENT)
public class AntiKnockBack extends Hack {
    public AntiKnockBack() {
        super(AntiKnockBack.class.getAnnotation(Hack.Register.class));
    }

    @Override
    public void onPacketRecieve(Packet<?> packet) {
        if (packet instanceof EntityVelocityUpdateS2CPacket) {
            mc.player.setVelocity(new Vec3d(player.prevX, player.getY(), player.prevZ));
            mc.getNetworkHandler().sendPacket(new EntityVelocityUpdateS2CPacket(mc.player));
        }
    }
}
