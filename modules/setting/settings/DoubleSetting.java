package me.austin.queer.modules.setting.settings;

import me.austin.queer.modules.Modulus;

public class DoubleSetting extends NumberSetting<Double> {
    public DoubleSetting(String name, String description, double defaultValue, double min, double max, Modulus parent) {
        super(name, description, defaultValue, min, max, parent);
    }

    @Override
    public void set(Double value) {
        this.value = value;
    }
}
