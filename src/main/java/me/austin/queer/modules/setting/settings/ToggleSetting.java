package me.austin.queer.modules.setting.settings;

import me.austin.queer.modules.hacks.Hack;
import me.austin.queer.modules.setting.Setting;

public class ToggleSetting extends Setting<Boolean> {
    public ToggleSetting(String name, String description, boolean defaultValue, Hack parent) {
        super(name, description, defaultValue, parent);
    }
}
