package me.austin.queer.module.setting.settings;

import java.util.List;

import me.austin.queer.module.hacks.Hack;
import me.austin.queer.module.setting.Setting;

public class ModeSetting extends Setting<Enum<?>> {
    public List<Enum<?>> values;

    public ModeSetting(String name, String description, Enum<?> defaultValue, Enum<?>[] values, Hack parent) {
        super(name, description, defaultValue, parent);
    }
    
    public void cycle() {
		this.set(currentValue == values.get(-1) ? values.get(0) : values.get(values.indexOf(currentValue) + 1));
	}
}
