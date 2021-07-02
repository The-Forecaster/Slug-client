package me.austin.queer.event;

import me.austin.queer.TransRights;
import me.austin.queer.event.events.*;
import me.austin.queer.module.hacks.Hacks;
import me.austin.queer.util.Globals;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listenable;
import me.zero.alpine.listener.Listener;

public class Events implements Globals, Listenable {
    public Events() {
        TransRights.EVENTBUS.subscribeAll(packetListener, updateListener, keyListener);
    }

    @EventHandler
    private static final Listener<PacketEvent> packetListener = new Listener<>(event -> {
        Hacks.hacks.forEach(hack -> {
            hack.onPacketRecieve(event.getPacket());
        });
    });

    @EventHandler
    private static final Listener<TickEvent> updateListener = new Listener<>(event -> {
        TransRights.HACKS.onTickUpdate();
    });

    @EventHandler
    private static final Listener<KeyInputEvent> keyListener = new Listener<>(event -> {
        TransRights.HACKS.onKeyPress(event.getKey());
    });
}