package me.austin.queer.modules.hacks.movement;

import me.austin.queer.modules.hacks.Category;
import me.austin.queer.modules.hacks.Hack;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

@Hack.Register(name = "No-Slow", description = "Prevents things from slowing you down", category = Category.MOVEMENT)
public class NoSlow extends Hack {
    public NoSlow() {
        super(NoSlow.class.getAnnotation(Hack.Register.class));
    }

    @Override
    public void onUpdate() {
        if (mc.player.getMovementSpeed() > 1f) {
            mc.player.setMovementSpeed(1f);
        }
    }

    @Override
    public void onPacketRecieve(Packet<?> packet) {
        if (packet instanceof PlayerMoveC2SPacket) {
            mc.send((Runnable) packet);
        }
    }
}
