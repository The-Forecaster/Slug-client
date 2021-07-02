package me.austin.queer.module.hacks.client;

import org.lwjgl.glfw.GLFW;

import me.austin.queer.module.gui.clickgui.components.ClickGuiScreen;
import me.austin.queer.module.hacks.*;
import me.austin.queer.module.setting.Setting;

public final class ClickGui extends Hack {
	private static Hacks hacks;

	public static final Setting<Integer> backgroundRed = new Setting<>("Background red", "", 255, 0, 255);
	public static final Setting<Integer> backgroundGreen = new Setting<>("Background green", "", 0, 0, 255);
	public static final Setting<Integer> backgroundBlue = new Setting<>("Background blue", "", 0, 0, 255);

	private static ClickGuiScreen guiScreen;

	public ClickGui(Hacks hacks) {
		super("Clickgui", "Opens the Clickgui", GLFW.GLFW_KEY_RIGHT_SHIFT, Category.CLIENT);
		this.settings.add(backgroundRed, backgroundGreen, backgroundBlue);
		guiScreen = new ClickGuiScreen(hacks, backgroundRed, backgroundGreen, backgroundBlue);
	}
	
	@Override
	public final void onEnable() {
		mc.openScreen(guiScreen);
		this.disable();
	}
	
	@Override
	public final void onDisable() {
		mc.openScreen(null);
	}
}
