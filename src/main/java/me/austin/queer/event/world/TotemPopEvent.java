package me.austin.queer.event.world;

import me.austin.queer.event.Event;
import net.minecraft.entity.player.PlayerEntity;

public class TotemPopEvent extends Event {
    private static TotemPopEvent INSTANCE = new TotemPopEvent();

    private PlayerEntity player;

    public static TotemPopEvent get(PlayerEntity player) {
        if (INSTANCE == null || INSTANCE.isCancelled()) {
            INSTANCE = new TotemPopEvent();
        }

        return INSTANCE;
    }

    public PlayerEntity getPlayer() {
        return this.player;
    }
}
