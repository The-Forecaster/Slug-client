package me.austin.queer.event.transrights;

import me.austin.queer.event.Event;
import me.austin.queer.modules.hacks.Hack;

public class ToggleEvent extends Event {
    public static ToggleEvent INSTANCE = new ToggleEvent();

    private Hack hack;

    public static ToggleEvent get(Hack hack) {
        if (INSTANCE == null) {
            INSTANCE = new ToggleEvent();
        }
        else if (INSTANCE.isCancelled()) {
            INSTANCE = new ToggleEvent();
        }
        INSTANCE.hack = hack;
        
        return INSTANCE;
    }

    public Hack getHack() {
        return this.hack;
    }
}
