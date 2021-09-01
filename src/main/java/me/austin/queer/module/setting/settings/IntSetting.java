package me.austin.queer.module.setting.settings;

import me.austin.queer.module.IModule;
import me.austin.queer.module.setting.Setting;

public class IntSetting extends Setting<Integer> {
    public int maxValue, minValue;

    public IntSetting(String name, String description, int defaultValue, int minValue, int maxValue, IModule parent) {
        super(name, description, defaultValue, parent);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
}
