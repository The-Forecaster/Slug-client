package me.austin.queer.module.setting.settings;

import me.austin.queer.module.hacks.Hack;
import me.austin.queer.module.setting.Setting;

public class ToggleSetting extends Setting<Boolean> {
    public ToggleSetting(String name, String description, boolean defaultValue, Hack parent) {
        super(name, description, defaultValue, parent);
    }
}
