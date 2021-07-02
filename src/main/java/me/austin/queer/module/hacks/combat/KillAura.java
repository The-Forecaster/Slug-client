package me.austin.queer.module.hacks.combat;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import me.austin.queer.module.hacks.Category;
import me.austin.queer.module.hacks.Hack;
import me.austin.queer.module.setting.Setting;
import me.austin.queer.util.EntityHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public final class KillAura extends Hack {
    public static final Setting<Enum<Targetting>> targetting = new Setting<>("Targetting", "How the client determines its target", Targetting.CLOSEST, Targetting.values());
    public static final Setting<Double> range = new Setting<>("Range", "How far you can hit niggas", 5d, 0d, 18d);
    public static final Setting<Boolean> rotate = new Setting<>("Rotate", "rotate to hit the target", true);

    private static PlayerEntity target;
    private static ArrayList<PlayerEntity> entities;

    public KillAura() {
        super("Killaura", "Attacks players near you", GLFW.GLFW_KEY_G, Category.COMBAT);
        this.settings.add(targetting, range, rotate);
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
            mc.world.getPlayers().forEach(player -> entities.add(player));

            entities.forEach(player -> {
                if (player.getAttacking() == mc.player) {
                    target = player;
                }
            });

            if (target == null) {
                target = mc.world.getClosestPlayer(mc.player.getX(), mc.player.getY(), mc.player.getZ(), 255d, true);
            }
        }

        if (target == null) {
            return;
        }

        if (EntityHelper.getDistance(target) < range.getValue()) {
            if (rotate.getValue()) {    
                EntityHelper.face(target);
            }

            if (!mc.player.handSwinging) {
                mc.interactionManager.attackEntity(mc.player, target);
                mc.player.swingHand(Hand.MAIN_HAND);
            }
        }
    }

    private static enum Targetting {
        CLOSEST,
        HEALTH,
        SMART
    }
}
