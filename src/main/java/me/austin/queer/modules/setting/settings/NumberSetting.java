package me.austin.queer.modules.setting.settings;

import me.austin.queer.modules.setting.Setting;

public abstract class NumberSetting<T extends Number> extends Setting<T> {
    protected T min, max;

    public NumberSetting(String name, String description, T defaultValue, T min, T max) {
        super(name, description, defaultValue);
        this.min = min;
        this.max = max;
    }

    public Number getMin() {
        return this.min;
    }

    public Number getMax() {
        return this.max;
    }

    @Override
    public void set(Number value) {
        this.set(value);
    } 
}
