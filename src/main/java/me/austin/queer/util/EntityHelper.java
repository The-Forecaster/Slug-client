package me.austin.queer.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public interface EntityHelper extends Globals {
    public static double getDistance(PlayerEntity player) {
        double length = Math.abs(mc.player.getX() - player.getX());
        double width = Math.abs(mc.player.getX() > player.getX() ? mc.player.getY() - player.getY() : player.getY() - mc.player.getY() + 1);
        double height = Math.abs(mc.player.getZ() - player.getZ());

        double base = Math.sqrt(length * length + width * width);

        return Math.sqrt(base * base + height * height);
    }

    public static void face(Entity entity) {
        PlayerUtil.setRotation(findPitchToFace(entity), findYawToFace(entity));
    }

    public static Double findYawToFace(Entity entity) {
        return Math.sqrt(getXDiff(entity) * getXDiff(entity) + getZDiff(entity) * getZDiff(entity));
    }

    public static Double findPitchToFace(Entity entity) {
        return Math.sqrt(findYawToFace(entity) * findYawToFace(entity) + getZDiff(entity) * getZDiff(entity));
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
}
