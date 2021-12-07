package me.austin.queer.modules.setting.settings.numbersettings;

import me.austin.queer.modules.Nameable;
import me.austin.queer.modules.setting.settings.NumberSetting;

public class IntSetting extends NumberSetting<Integer> {
    public IntSetting(String name, String description, int defaultValue, int min, int max, Nameable parent) {
        super(name, description, defaultValue, min, max, parent);
    }

    @Override 
    public void set(Integer value) {
        this.value = value;
    }
}
