package me.austin.queer.modules.hacks.combat;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import me.austin.queer.modules.hacks.Category;
import me.austin.queer.modules.hacks.Hack;
import me.austin.queer.modules.setting.settings.ModeSetting;
import me.austin.queer.modules.setting.settings.ToggleSetting;
import me.austin.queer.modules.setting.settings.numbersettings.DoubleSetting;
import me.austin.queer.util.entity.EntityHelper;
import me.austin.queer.util.entity.player.PlayerUtil;
import net.minecraft.entity.LivingEntity;

@Hack.Register(name = "Kill-Aura", description = "Attacks players near you", bind = GLFW.GLFW_KEY_G, category = Category.COMBAT)
public final class KillAura extends Hack {
    public ModeSetting targetting = register(new ModeSetting("Targetting", "How the client determines its target", Targetting.CLOSEST, Targetting.values(), this));
    public DoubleSetting range = register(new DoubleSetting("Range", "How far you can hit your target", 5d, 0d, 18d, this));
    public ToggleSetting rotate = register(new ToggleSetting("Rotate", "Rotate to hit the target", true, this));
    public ToggleSetting targetFriends = register(new ToggleSetting("TargetFriends", "Target friends with the client", false, this));

    private static LivingEntity target;
    private static final ArrayList<LivingEntity> entities = new ArrayList<>();

    public KillAura() {
        super(KillAura.class.getAnnotation(Hack.Register.class));
    }

    @Override
    public final void onUpdate() {
        float health = Integer.MAX_VALUE;
        entities.clear();

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

    private static enum Targetting {
        CLOSEST,
        HEALTH,
        SMART
    }
}
