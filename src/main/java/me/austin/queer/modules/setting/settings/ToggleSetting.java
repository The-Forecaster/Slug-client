package me.austin.queer.modules.setting.settings;

import me.austin.queer.modules.setting.Setting;

public class ToggleSetting extends Setting<Boolean> {
    public ToggleSetting(String name, String description, boolean defaultValue) {
        super(name, description, defaultValue);
    }

    public void toggle() {
        this.set(!this.get());
    }
}
