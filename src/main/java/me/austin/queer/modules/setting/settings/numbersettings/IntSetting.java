package me.austin.queer.modules.setting.settings.numbersettings;

import me.austin.queer.modules.setting.settings.NumberSetting;

public class IntSetting extends NumberSetting<Integer> {
    public IntSetting(String name, String description, int defaultValue, int min, int max) {
        super(name, description, defaultValue, min, max);
    }

    @Override 
    public void set(Integer value) {
        this.value = value;
    }
}
