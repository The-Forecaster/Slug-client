package me.austin.queer.module.hacks.client;

import org.lwjgl.glfw.GLFW;

import me.austin.queer.module.gui.clickgui.ClickGuiScreen;
import me.austin.queer.module.hacks.Category;
import me.austin.queer.module.hacks.Hack;
import me.austin.queer.module.setting.Setting;
import me.austin.queer.module.setting.settings.IntSetting;

public final class ClickGui extends Hack {
	public static Setting<Integer> backgroundRed;
	public static Setting<Integer> backgroundGreen;
	public static Setting<Integer> backgroundBlue;

	public ClickGui() {
		super("Clickgui", "Opens the Clickgui", GLFW.GLFW_KEY_RIGHT_SHIFT, Category.CLIENT);
		backgroundRed = new IntSetting("Background red", "", 255, 0, 255, this);
		backgroundGreen = new IntSetting("Background green", "", 0, 0, 255, this);
		backgroundBlue = new IntSetting("Background blue", "", 0, 0, 255, this);
	}
	
	@Override
	public final void onEnable() {
		mc.openScreen(ClickGuiScreen.INSTANCE);
	}
}
