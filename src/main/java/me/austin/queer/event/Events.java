package me.austin.queer.event;

import me.austin.queer.event.events.*;
import me.austin.queer.module.hacks.Hacks;
import me.austin.queer.util.Globals;
import meteordevelopment.orbit.EventHandler;

public class Events implements Globals {
    public Hacks hacks;

    public Events(Hacks hacks) {
        this.hacks = hacks;
    }

    public Events() {
        this(new Hacks());
    }

    @EventHandler
    private void onTick() {
        hacks.onTickUpdate();
    }

    @EventHandler
    private void onKeyPressed(KeyInputEvent event) {
        this.hacks.onKeyPress(event.getKey());
    }

    @EventHandler
    private void onPacketRecieve(PacketEvent event) {
        this.hacks.onPacketRecieve(event.getPacket());
    }
}