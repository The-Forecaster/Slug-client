package me.austin.queer.modules.setting.settings.numbersettings;

import me.austin.queer.modules.setting.settings.NumberSetting;

public class DoubleSetting extends NumberSetting<Double> {
    public DoubleSetting(String name, String description, double defaultValue, double min, double max) {
        super(name, description, defaultValue, min, max);
    }

    @Override
    public void set(Double value) {
        this.value = value;
    }
}
