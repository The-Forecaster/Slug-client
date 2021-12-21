package me.austin.queer.modules.hacks.client;

import me.austin.queer.gui.screens.ClickGuiScreen;
import me.austin.queer.modules.hacks.Category;
import me.austin.queer.modules.hacks.Hack;
import me.austin.queer.modules.setting.settings.numbersettings.IntSetting;

@Hack.Register(name = "ClickGui", description = "Opens the Clickgui", category = Category.CLIENT)
public final class ClickGui extends Hack {
	private static ClickGui INSTANCE;

	public final IntSetting backgroundRed = register(new IntSetting("Background-red", "", 255, 0, 255));
	public final IntSetting backgroundGreen = register(new IntSetting("Background-green", "", 0, 0, 255));
	public final IntSetting backgroundBlue = register(new IntSetting("Background-blue", "", 0, 0, 255));

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
