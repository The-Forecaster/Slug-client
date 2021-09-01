package me.austin.queer.module.gui.clickgui.components;

import me.austin.queer.module.gui.clickgui.GuiComponent;
import me.austin.queer.module.setting.Setting;
import me.austin.queer.util.render.ScreenHelper;

public abstract class SettingButton<T extends Setting<J>, J> extends GuiComponent {
	public final Setting<J> setting;
	private final HackButton parent;

	public SettingButton(int x, int y, Setting<J> setting, HackButton parent) {
		super(setting.getName(), setting.getDescription(), x, y, parent.width, 15 * ScreenHelper.wpixel());
		this.setting = setting;
		this.parent = parent;
	}

	public HackButton getParent() {
		return parent;
	}	

	public Setting<J> getSetting() {
		return this.setting;
	}
}
