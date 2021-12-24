package me.austin.queer.feature.hack;

import java.util.HashMap;
import java.util.Map;

import me.austin.queer.feature.Feature;
import static me.austin.queer.misc.Globals.EVENTBUS;

public class Hack extends Feature {
    public final Map<String, Object> settings = new HashMap<>();
    private boolean enabled = false;

    public Hack(String name) {
        super(name);
    }

    public final boolean isEnabled() {
        return this.enabled;
    }

    public final void setEnabled(boolean enabled) {
        if (enabled) EVENTBUS.subscribe(this);
        else EVENTBUS.unsubscribe(this);
        this.enabled = enabled;
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public final void toggle() {
        if (!this.enabled) this.onEnable();
        else this.onDisable();
        this.setEnabled(!this.enabled);
    }
}
