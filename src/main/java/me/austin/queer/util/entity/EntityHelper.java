package me.austin.queer.util.entity;

import java.util.ArrayList;
import java.util.List;

import me.austin.queer.util.Util;
import me.austin.queer.util.entity.player.PlayerUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public interface EntityHelper extends Util {
    public static double getDistance(Entity entity) {
        double length = Math.abs(mc.player.getX() - entity.getX());
        double width = Math.abs(mc.player.getX() > entity.getX() ? mc.player.getY() - entity.getY() : entity.getY() - mc.player.getY() + 1);
        double height = Math.abs(mc.player.getZ() - entity.getZ());

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

    public static List<LivingEntity> getLivingEntities() {
        List<LivingEntity> entities = new ArrayList<>();

        for (Entity entity : mc.world.getEntities()) {
            if (entity instanceof LivingEntity) {
                entities.add((LivingEntity)entity);
            }
        } 
        return entities;
    }
}
