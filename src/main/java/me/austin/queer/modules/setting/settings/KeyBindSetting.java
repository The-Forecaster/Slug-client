package me.austin.queer.modules.setting.settings;

import me.austin.queer.modules.setting.Setting;

public class KeyBindSetting extends Setting<Integer> {
	public KeyBindSetting(int bind) {
		super("Bind", "Keybind for this module", bind);
	}

	public static final int NONE = 0;
}
