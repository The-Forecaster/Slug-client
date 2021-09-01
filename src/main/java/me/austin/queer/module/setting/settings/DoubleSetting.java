package me.austin.queer.module.setting.settings;

import me.austin.queer.module.IModule;
import me.austin.queer.module.setting.Setting;

public class DoubleSetting extends Setting<Double> {
    public double minValue, maxValue;

    public DoubleSetting(String name, String description, double defaultValue, double minValue, double maxValue, IModule parent) {
        super(name, description, defaultValue, parent);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
}
