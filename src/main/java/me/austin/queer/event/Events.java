package me.austin.queer.event;

import com.google.common.eventbus.Subscribe;

import me.austin.queer.TransRights;
import me.austin.queer.event.render.RenderEvent;
import me.austin.queer.event.server.PacketEvent;
import me.austin.queer.event.system.KeyEvent;
import me.austin.queer.event.world.TickEvent;
import me.austin.queer.modules.hacks.Hacks;
import me.austin.queer.util.Util;

public class Events implements Util {
    public Events() {
        TransRights.EVENTBUS.register(this);
    }

    @Subscribe
    public void onTick(TickEvent.Post event) {
        if (!Util.fullNullCheck()) {
            Hacks.getInstance().onTickUpdate();
        }
    }

    @Subscribe 
    public void onKeyEvent(KeyEvent event) {
        if (!Util.fullNullCheck()) {
            Hacks.getInstance().onKeyPress(event.getKey());
        }
    }

    @Subscribe
    public void onPacketRecieve(PacketEvent.Receive event) {
        if (!Util.fullNullCheck()) {
            Hacks.getInstance().onPacketRecieve(event.getPacket());
        }
    }

    @Subscribe
    public void onRenderOverlay(RenderEvent.DrawOverlayEvent event) {
        if (Util.mc.currentScreen == null) {
            // Hud.getInstance().onRenderOverlay(event.getStack());
        }
    }
}