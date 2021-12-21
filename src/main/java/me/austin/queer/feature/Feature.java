package me.austin.queer.feature;

import static me.austin.queer.util.Globals.EVENTBUS;

import me.zero.alpine.listener.Listenable;;

public abstract class Feature implements Listenable {
    private final String name;
    private boolean enabled = false;
    
    protected Feature(String name) {
        this.name = name;
    }

    public final String getName() {
        return this.name;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public final void setEnabled(boolean enabled) {
        if (enabled) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
        
        this.enabled = enabled;
    }

    public void onEnable() {
        EVENTBUS.subscribe(this);
    }

    public void onDisable() {
        EVENTBUS.unsubscribe(this);
    }

    public final void toggle() {
        if (!this.enabled) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }

        this.enabled = !enabled;
    }
}
