package me.austin.queer.module.setting.settings;

import me.austin.queer.module.hacks.Hack;
import me.austin.queer.module.setting.Setting;

public class KeyBindSetting extends Setting<Integer> {
	public KeyBindSetting(int bind, Hack hack) {
		super("Bind", "Keybind for this module", bind, hack);
	}

	public static final int NONE = 0;
}
