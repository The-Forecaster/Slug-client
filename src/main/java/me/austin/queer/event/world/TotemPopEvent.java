package me.austin.queer.event.world;

import me.austin.queer.event.Event;
import net.minecraft.entity.player.PlayerEntity;

public class TotemPopEvent extends Event {
    private final PlayerEntity player;

    public TotemPopEvent(PlayerEntity player) {
        this.player = player;
    }

    public PlayerEntity getPlayer() {
        return this.player;
    }
}
