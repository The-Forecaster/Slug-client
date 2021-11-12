package me.austin.queer.modules.setting.settings;

import me.austin.queer.modules.Modulus;

public class IntSetting extends NumberSetting<Integer> {
    public IntSetting(String name, String description, int defaultValue, int min, int max, Modulus parent) {
        super(name, description, defaultValue, min, max, parent);
    }

    @Override 
    public void set(Integer value) {
        this.value = value;
    }
}
