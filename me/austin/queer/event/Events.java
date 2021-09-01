package me.austin.queer.event;

import java.lang.invoke.MethodHandles;

import me.austin.queer.TransRights;
import me.austin.queer.event.client.KeyEvent;
import me.austin.queer.event.render.DrawOverlayEvent;
import me.austin.queer.event.server.PacketEvent;
import me.austin.queer.event.world.TickEvent;
import me.austin.queer.module.gui.clickgui.ClickGuiScreen;
import me.austin.queer.module.gui.hud.Hud;
import me.austin.queer.module.hacks.Hacks;
import me.austin.queer.util.Util;
import meteordevelopment.orbit.EventHandler;

public class Events implements Util {
    public Events() {
        TransRights.EVENTBUS.registerLambdaFactory(TransRights.modid,  (lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));
        TransRights.EVENTBUS.subscribe(this.getClass());
    }

    @EventHandler
    public void onTickUpdate(TickEvent.Post event) {
        if (!Util.fullNullCheck()) {
			Hacks.onTickUpdate();
        }
    }

    @EventHandler
    public void onKeyEvent(KeyEvent event) {
        TransRights.printLog("Key Pressed");
        if (!Util.fullNullCheck()) {
            Hacks.onKeyPress(event.getKey());
        }
    }

    @EventHandler
    public void onPacketRecieve(PacketEvent.Receive event) {
        if (!Util.fullNullCheck()) {
            Hacks.onPacketRecieve(event.getPacket());
        }
    }

    @EventHandler
    public void onDrawOverlayEvent(DrawOverlayEvent event) {
        if (mc.inGameHud != null && !(mc.currentScreen instanceof ClickGuiScreen)) {
            Hud.onRenderOverlay(event.getStack());
        }
    }
}