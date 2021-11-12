package me.austin.queer.modules.hacks.client;

import org.lwjgl.glfw.GLFW;

import me.austin.queer.modules.gui.clickgui.screens.ClickGuiScreen;
import me.austin.queer.modules.hacks.Category;
import me.austin.queer.modules.hacks.Hack;
import me.austin.queer.modules.setting.settings.IntSetting;

@Hack.Register(name = "ClickGui", description = "Opens the Clickgui", bind = GLFW.GLFW_KEY_RIGHT_SHIFT, category = Category.CLIENT)
public final class ClickGui extends Hack {
	private static ClickGui instance;

	public IntSetting backgroundRed = register(new IntSetting("Background red", "", 255, 0, 255, this));
	public IntSetting backgroundGreen = register(new IntSetting("Background green", "", 0, 0, 255, this));
	public IntSetting backgroundBlue = register(new IntSetting("Background blue", "", 0, 0, 255, this));

	public ClickGui() {
		super(ClickGui.class.getAnnotation(Hack.Register.class));
		this.setInstance();
	}

	public static ClickGui getInstance() {
		if (instance == null) {
			instance = new ClickGui();
		}
		return instance;
	}

	private void setInstance() {
		instance = this;
	}
	
	@Override
	public final void onEnable() {
		mc.openScreen(ClickGuiScreen.getInstance());
	}

	@Override
	public final void onDisable() {
		mc.openScreen(null);
	}
}
