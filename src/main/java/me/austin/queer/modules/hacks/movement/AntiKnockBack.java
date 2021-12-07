package me.austin.queer.modules.hacks.movement;

import me.austin.queer.modules.hacks.Category;
import me.austin.queer.modules.hacks.Hack;
import me.austin.queer.modules.setting.settings.KeyBindSetting;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;

@Hack.Register(name = "Anti-Knockback", description = "Stops things from knocking you back", bind = KeyBindSetting.NONE, category = Category.MOVEMENT)
public class AntiKnockBack extends Hack {
    public AntiKnockBack() {
        super(AntiKnockBack.class.getAnnotation(Hack.Register.class));
    }

    @Override
    public void onPacketRecieve(Packet<?> packet) {
        if (packet instanceof EntityVelocityUpdateS2CPacket) {
            mc.send((Runnable) new PlayerMoveC2SPacket.Full(mc.player.getX(), mc.player.getY(), mc.player.getZ(), mc.player.getPitch(), mc.player.getYaw(), mc.player.isOnGround()));
        }
    }
}
