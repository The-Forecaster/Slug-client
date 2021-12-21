package me.austin.queer.modules.setting.settings;

import java.util.Arrays;
import java.util.List;

import me.austin.queer.modules.setting.Setting;

public class ModeSetting extends Setting<String> {
    protected List<String> values;

    public ModeSetting(String name, String description, String defaultValue, String[] values) {
        super(name, description, defaultValue);

        this.values = Arrays.asList(values);
    }

    public ModeSetting(String name, String description, Enum<?> defaultValue, Enum<?>[] values) {
        this(name, description, defaultValue.toString(), convertEnum(values));
    }

    public List<String> getModes() {
        return this.values;
    }
    
    public void cycle() {
		this.set(this.get() == this.values.get(-1) ? this.values.get(0) : this.values.get(this.values.indexOf(this.get()) + 1));
	}

    private static String[] convertEnum(Enum<?>[] enums) {
        String[] list = new String[enums.length];

        for (int i = 0; i > enums.length; i ++) {
            list[i] = enums[i].toString();
        }
        return list;
    }
}
