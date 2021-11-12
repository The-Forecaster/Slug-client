package me.austin.queer.event.transrights;

import me.austin.queer.event.Event;
import me.austin.queer.modules.hacks.Hack;

public class ToggleEvent extends Event {
    private final Hack hack;

    public ToggleEvent(Hack hack) {
        this.hack = hack;
    }

    public Hack getHack() {
        return this.hack;
    }
}
