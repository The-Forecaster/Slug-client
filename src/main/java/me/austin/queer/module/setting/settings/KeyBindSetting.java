package me.austin.queer.module.setting.settings;

import me.austin.queer.module.setting.Setting;

public class KeyBindSetting extends Setting<Integer> {
	public KeyBindSetting(int bind) {
		super("Bind", "Keybind for this module", bind, 0, 10000);
	}
}
