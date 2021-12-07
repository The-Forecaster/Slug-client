package me.austin.queer.modules.hacks.client;

import org.lwjgl.glfw.GLFW;

import me.austin.queer.gui.screens.ClickGuiScreen;
import me.austin.queer.modules.hacks.Category;
import me.austin.queer.modules.hacks.Hack;
import me.austin.queer.modules.setting.settings.numbersettings.IntSetting;

@Hack.Register(name = "ClickGui", description = "Opens the Clickgui", bind = GLFW.GLFW_KEY_RIGHT_SHIFT, category = Category.CLIENT)
public final class ClickGui extends Hack {
	private static ClickGui INSTANCE;

	public IntSetting backgroundRed = this.register(new IntSetting("Background-red", "", 255, 0, 255, this));
	public IntSetting backgroundGreen = this.register(new IntSetting("Background-green", "", 0, 0, 255, this));
	public IntSetting backgroundBlue = this.register(new IntSetting("Background-blue", "", 0, 0, 255, this));

	public ClickGui() {
		super(ClickGui.class.getAnnotation(Hack.Register.class));
		INSTANCE = this;
	}

	public static ClickGui getInstance() {
		return INSTANCE;
	}
	
	@Override
	public final void onEnable() {
		mc.setScreen(ClickGuiScreen.getInstance());
		this.disable();
	}
}
