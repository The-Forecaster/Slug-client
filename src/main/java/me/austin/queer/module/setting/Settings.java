package me.austin.queer.module.setting;

import java.util.List;

import me.austin.queer.module.Modules;

public class Settings extends Modules<Setting> {
    public List<Setting> getSettings() {
        return this.modules;
    }

    public void add(Setting<?> setting) {
        this.modules.add(setting);
    }

    public void add(Setting<?>... settings) {
        for (Setting<?> setting : settings) {
            this.modules.add(setting);
        }
    }
}
