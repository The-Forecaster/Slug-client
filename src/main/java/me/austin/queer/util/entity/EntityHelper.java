package me.austin.queer.util.entity;

import java.util.ArrayList;
import java.util.List;

import me.austin.queer.util.Globals;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface EntityHelper extends Globals {
    static List<LivingEntity> getLivingEntities() {
        return getLivingEntities(false);
    }

    static List<LivingEntity> getLivingEntities(boolean includePlayer) {
        List<LivingEntity> entities = new ArrayList<>();

        for (Entity entity : mc.world.getEntities()) {
            if (entity instanceof LivingEntity) {
                entities.add((LivingEntity)entity);
            }
        } 
        return entities;
    }

    static List<PlayerEntity> getOtherPlayers() {
        List<PlayerEntity> players = new ArrayList<>();

        for (PlayerEntity player : mc.world.getPlayers()) {
            if (player.getUuid().equals(mc.player.getUuid())) {
                continue;
            }
            players.add(player);
        }
        return players;
    }
}
