package me.austin.queer.module.hacks.combat;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import me.austin.queer.module.hacks.Category;
import me.austin.queer.module.hacks.Hack;
import me.austin.queer.module.setting.Setting;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public final class KillAura extends Hack {
    public final Setting<Enum<Targetting>> targetting = new Setting<>("Targetting", "How the client determines its target", Targetting.CLOSEST, Targetting.values());

    private final ArrayList<LivingEntity> entities = new ArrayList<>();
    private LivingEntity target;

    public KillAura() {
        super("Killaura", "Attacks players near you", GLFW.GLFW_KEY_G, Category.COMBAT);
        this.settings.add(targetting);
    }

    @Override
    public final void onUpdate() {
        float health = Integer.MAX_VALUE;

        if (targetting.getValue() == Targetting.CLOSEST) {
            target = mc.world.getClosestPlayer(mc.player.getX(), mc.player.getY(), mc.player.getZ(), 255d, true);
        }
        if (targetting.getValue() == Targetting.HEALTH) {
            for (PlayerEntity entity : mc.world.getPlayers()) {
               if (entity.getHealth() < health) {
                   target = entity;
                   health = entity.getHealth();
               }
            }
        }
        else {
            target = mc.player.getAttacker();
        }

        mc.player.swingHand(Hand.MAIN_HAND);
        mc.player.swingHand(Hand.OFF_HAND);
    }

    private static enum Targetting {
        CLOSEST,
        HEALTH,
        SMART
    }
}
