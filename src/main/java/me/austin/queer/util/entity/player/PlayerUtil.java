package me.austin.queer.util.entity.player;

import me.austin.queer.util.entity.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;

public interface PlayerUtil extends EntityHelper {
    public static void hitEntity(boolean rotate, Entity entity) {
        if (rotate) {
            setRotation(EntityHelper.findPitchToFace(entity), EntityHelper.findYawToFace(entity));
        }
        mc.interactionManager.attackEntity(mc.player, entity);
        mc.player.swingHand(Hand.MAIN_HAND);
    }

    public static void setRotation(double pitch, double yaw) {
        mc.player.setPitch((float)pitch);
        mc.player.setYaw((float)yaw);
    }

    public static double getTraceDistance(Entity entity) {
        return Math.sqrt(Math.pow(Math.sqrt(getTraceX(entity) * getTraceX(entity) + getTraceY(entity) * getTraceY(entity)), 2) + getTraceZ(entity) * getTraceZ(entity));
    }

    public static double getTraceX(Entity entity) {
        return Math.max(Math.ceil(Math.abs(mc.player.getX() - entity.getX())),  Math.floor(Math.abs(mc.player.getX() - entity.getX())));
    }

    public static double getTraceY(Entity entity) {
        return Math.max(Math.ceil(Math.abs(mc.player.getY() - entity.getY())),  Math.floor(Math.abs(mc.player.getY() - entity.getY())));
    }

    public static double getTraceZ(Entity entity) {
        return Math.max(Math.ceil(Math.abs(mc.player.getZ() - entity.getZ())),  Math.floor(Math.abs(mc.player.getZ() - entity.getZ())));
    }

    public static int find(Item item) {
        for (int i = 0; i < mc.player.getInventory().size(); i ++) {
            if (mc.player.getInventory().getStack(i).getItem() == item) {
                return i;
            }
        }
        return -1;
    }

    public static void setOffhand(int index) {
        mc.player.getInventory().offHand.set(index, mc.player.getInventory().getStack(index));
        mc.player.getInventory().swapSlotWithHotbar(PlayerInventory.OFF_HAND_SLOT);
    }
}
