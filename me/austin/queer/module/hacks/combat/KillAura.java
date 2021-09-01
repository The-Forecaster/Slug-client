package me.austin.queer.module.hacks.combat;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import me.austin.queer.module.hacks.Category;
import me.austin.queer.module.hacks.Hack;
import me.austin.queer.module.setting.Setting;
import me.austin.queer.module.setting.settings.DoubleSetting;
import me.austin.queer.module.setting.settings.ModeSetting;
import me.austin.queer.module.setting.settings.ToggleSetting;
import me.austin.queer.util.entity.EntityHelper;
import me.austin.queer.util.entity.PlayerUtil;
import net.minecraft.entity.LivingEntity;

public final class KillAura extends Hack {
    public static Setting<Enum<?>> targetting;
    public static Setting<Double> range;
    public static Setting<Boolean> rotate;

    private static LivingEntity target;
    private static final ArrayList<LivingEntity> entities = new ArrayList<>();

    public KillAura() {
        super("Killaura", "Attacks players near you", GLFW.GLFW_KEY_G, Category.COMBAT);
        targetting = new ModeSetting("Targetting", "How the client determines its target", Targetting.CLOSEST, Targetting.values(), this);
        range = new DoubleSetting("Range", "How far you can hit your target", 5d, 0d, 18d, this);
        rotate = new ToggleSetting("Rotate", "Rotate to hit the target", true, this);
    }

    @Override
    public final void onUpdate() {
        float health = Integer.MAX_VALUE;

        if (EntityHelper.getLivingEntities().size() == 0) {
            return;
        }

        if (targetting.get() == Targetting.CLOSEST) {
            target = mc.world.getClosestPlayer(mc.player.getX(), mc.player.getY(), mc.player.getZ(), 255d, true);
        }
        if (targetting.get() == Targetting.HEALTH) {
            for (LivingEntity entity : EntityHelper.getLivingEntities()) {
                if (entity.getHealth() < health) {
                    target = entity;
                    health = entity.getHealth();
                }
            }
        }
        else {
            mc.world.getPlayers().forEach(entity -> entities.add(entity));

            entities.forEach(entity -> {
                if (entity.getAttacking() == mc.player) {
                    target = entity;
                }
            });

            if (target == null) {
                target = mc.world.getClosestPlayer(mc.player.getX(), mc.player.getY(), mc.player.getZ(), 255d, true);
            }
        }

        if (target == null) {
            return;
        }

        if (EntityHelper.getDistance(target) < range.get()) {
            if (mc.player.getAttackCooldownProgressPerTick() == mc.player.getMainHandStack().getCooldown()) {
                PlayerUtil.hitEntity(rotate.get(), target);
            }
        }
    }

    enum Targetting {
        CLOSEST,
        HEALTH,
        SMART
    }
}
