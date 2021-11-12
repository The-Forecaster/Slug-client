package me.austin.queer.modules.gui.clickgui.components.buttons;

import java.util.ArrayList;
import java.util.List;

import me.austin.queer.modules.Modulus;
import me.austin.queer.modules.gui.clickgui.ClickGuiComponent;
import me.austin.queer.modules.gui.clickgui.components.Frame;
import me.austin.queer.modules.setting.Setting;
import me.austin.queer.modules.setting.Settings;
import me.austin.queer.modules.setting.settings.DoubleSetting;
import me.austin.queer.modules.setting.settings.IntSetting;
import me.austin.queer.modules.setting.settings.KeyBindSetting;
import me.austin.queer.modules.setting.settings.ModeSetting;
import me.austin.queer.modules.setting.settings.ToggleSetting;
import me.austin.queer.util.client.ColorHelper;
import me.austin.queer.util.render.ScreenHelper;
import me.austin.queer.util.text.JColor;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;

public class ModuleButton extends ClickGuiComponent {
	protected List<SettingButton<?>> buttons;
	protected Frame parent;
	private boolean settingsRendered;
	private Modulus module;

	public ModuleButton(String name, String description, int x, int y, Modulus module, Frame parent, boolean shown) {
		super(name, description, x, y, parent.width, 20 * ScreenHelper.wpixel(), shown);
		this.buttons = new ArrayList<>();
		this.module = module;
		this.parent = parent;
		this.settingsRendered = false;

		int offset = 15;
		for (Setting<?> setting : Settings.getInstance().getSettingsFromModule(module)) {
			switch (setting.getClass().toString().toLowerCase()) {
				case "togglesetting":
					this.buttons.add(new ToggleSettingButton(this.x, y + offset, (ToggleSetting)setting, this, true));
				case "keybindsetting":
					this.buttons.add(new BindSettingButton(this.x, y + offset, (KeyBindSetting)setting, this, true));
				case "modesetting":
					this.buttons.add(new ModeSettingButton(this.x, y + offset, (ModeSetting)setting, this, true));
				case "doublesetting":
					this.buttons.add(new NumberSettingButton(this.x, y + offset, (DoubleSetting)setting, this, true));
				case "intsetting":
					this.buttons.add(new NumberSettingButton(this.x, y + offset, (IntSetting)setting, this, true));
			}
			offset += 15;
		}
	}

	public boolean isOpen() {
		return settingsRendered;
	}

	public List<SettingButton<?>> getButtons() {
		return this.buttons;
	}
	
	public void keyPressed(int keyCode, int scanCode, int modifiers) {
		this.buttons.forEach(button -> {
			if (button instanceof BindSettingButton) {
				((BindSettingButton)button).keyPressed(keyCode, scanCode, modifiers);
			}
		});
	}
	
	@Override
	public void render(MatrixStack matrices, TextRenderer textRenderer) {
		Screen.fill(matrices, x, y, x + this.width, y + this.height, ColorHelper.backGroundLow);
		Screen.drawTextWithShadow(matrices, textRenderer, new LiteralText(module.getName()), x, y, new JColor(255, 255, 255, 255).getRGB());
		
		if (settingsRendered) {
			for (SettingButton<?> button : this.buttons) {
				button.render(matrices, textRenderer);
			}
		}
	}
	
	@Override
	public void mouseClicked(double mousex, double mousey, int keyCode) {
		mc.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 2.0f));
		if (keyCode == 1) {
			this.settingsRendered = !this.settingsRendered;
		}

		buttons.forEach(button -> {
			if (ScreenHelper.clickCheck(mousex, mousey, this)) {
				button.mouseClicked(mousex, mousey, keyCode);
			}
		});
	}
}
