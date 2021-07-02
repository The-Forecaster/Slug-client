package me.austin.queer.module.setting;

import java.util.ArrayList;
import java.util.List;

public class Settings {
    public List<Setting> settings = new ArrayList<>();

    public List<Setting> getSettings() {
        return this.settings;
    }

    public void add(Setting setting) {
        this.settings.add(setting);
    }

    public void add(Setting... settings) {
        for (Setting setting : settings) {
            this.settings.add(setting);
        }
    }
}
