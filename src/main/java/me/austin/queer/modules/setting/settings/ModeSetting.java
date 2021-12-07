package me.austin.queer.modules.setting.settings;

import java.util.Arrays;
import java.util.List;

import me.austin.queer.modules.hacks.Hack;
import me.austin.queer.modules.setting.Setting;

public class ModeSetting extends Setting<Enum<?>> {
    protected List<Enum<?>> values;

    public ModeSetting(String name, String description, Enum<?> defaultValue, Enum<?>[] values, Hack parent) {
        super(name, description, defaultValue, parent);

        this.values = Arrays.asList(values);
    }

    public List<Enum<?>> getModes() {
        return this.values;
    }
    
    public void cycle() {
		this.set(this.get() == this.values.get(-1) ? this.values.get(0) : this.values.get(this.values.indexOf(this.get()) + 1));
	}
}
