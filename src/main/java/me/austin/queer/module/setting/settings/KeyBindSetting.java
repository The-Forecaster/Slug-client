package me.austin.queer.modules.setting.settings;

import me.austin.queer.modules.hacks.Hack;
import me.austin.queer.modules.setting.Setting;

public class KeyBindSetting extends Setting<Integer> {
	public KeyBindSetting(int bind, Hack hack) {
		super("Bind", "Keybind for this module", bind, hack);
	}

	public static final int NONE = 0;
}
