package me.austin.queer.modules.gui.clickgui.components.buttons;

import me.austin.queer.modules.gui.clickgui.ClickGuiComponent;
import me.austin.queer.modules.setting.Setting;
import me.austin.queer.util.render.ScreenHelper;

public abstract class SettingButton<T extends Setting<?>> extends ClickGuiComponent {
	protected final T setting;
	private final ModuleButton parent;

	public SettingButton(int x, int y, T setting, ModuleButton parent, boolean shown) {
		super(setting.getName(), setting.getDescription(), x, y, parent.width, 15 * ScreenHelper.wpixel(), shown);
		this.setting = setting;
		this.parent = parent;
	}
	
	public T getSetting() {
		return this.setting;
	}

	public ModuleButton getParent() {
		return parent;
	}	

	
}
