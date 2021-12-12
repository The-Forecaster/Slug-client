package me.austin.queer.util.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;

public interface PlayerUtil extends EntityHelper {
    public static void hitEntity(boolean rotate, Entity entity) {
        if (rotate) {
            setRotation(findPitchToFace(entity), findYawToFace(entity));
        }
        mc.interactionManager.attackEntity(mc.player, entity);
        mc.player.swingHand(Hand.MAIN_HAND);
    }

    public static void setRotation(double pitch, double yaw) {
        mc.player.setPitch((float)pitch);
        mc.player.setYaw((float)yaw);
    }

    public static void face(Entity entity) {
        setRotation(findPitchToFace(entity), findYawToFace(entity));
    }

    public static Double getXDiff(Entity entity) {
        return Math.abs(mc.player.getX() - entity.getX());
    }

    public static Double getYDiff(Entity entity) {
        return Math.abs(mc.player.getY() - entity.getY());
    }

    public static Double getZDiff(Entity entity) {
        return Math.abs(mc.player.getZ() - entity.getZ());
    }

    public static Double findYawToFace(Entity entity) {
        return Math.sqrt(getXDiff(entity) * getXDiff(entity) + getZDiff(entity) * getZDiff(entity));
    }

    public static Double findPitchToFace(Entity entity) {
        return Math.sqrt(findYawToFace(entity) * findYawToFace(entity) + getZDiff(entity) * getZDiff(entity));
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

    public static double getDistance(Entity entity) {
        double length = Math.abs(mc.player.getX() - entity.getX());
        double width = Math.abs(mc.player.getX() > entity.getX() ? mc.player.getY() - entity.getY() : entity.getY() - mc.player.getY() + 1);
        double height = Math.abs(mc.player.getZ() - entity.getZ());

        double base = Math.sqrt(length * length + width * width);

        return Math.sqrt(base * base + height * height);
    }
}
