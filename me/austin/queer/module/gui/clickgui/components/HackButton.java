package me.austin.queer.module.gui.clickgui.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import me.austin.queer.module.gui.clickgui.GuiComponent;
import me.austin.queer.module.gui.clickgui.components.SettingButtons.BindSettingButton;
import me.austin.queer.module.gui.clickgui.components.SettingButtons.DoubleSettingButton;
import me.austin.queer.module.gui.clickgui.components.SettingButtons.IntSettingButton;
import me.austin.queer.module.gui.clickgui.components.SettingButtons.ModeSettingButton;
import me.austin.queer.module.gui.clickgui.components.SettingButtons.ToggleSettingButton;
import me.austin.queer.module.hacks.Hack;
import me.austin.queer.module.setting.Setting;
import me.austin.queer.module.setting.Settings;
import me.austin.queer.module.setting.settings.DoubleSetting;
import me.austin.queer.module.setting.settings.IntSetting;
import me.austin.queer.module.setting.settings.KeyBindSetting;
import me.austin.queer.module.setting.settings.ModeSetting;
import me.austin.queer.module.setting.settings.ToggleSetting;
import me.austin.queer.util.render.ScreenHelper;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;

public class HackButton extends GuiComponent {
	public List<SettingButton<?, ?>> buttons;
	public Hack hack;
	public CategoryFrame parent;
	public boolean settingsRendered;
	
	public HackButton(int x, int y, Hack hack, CategoryFrame parent) {
		super(hack.getName(), hack.getDescription(), x, y, parent.width, 20 * ScreenHelper.wpixel());
		this.buttons = new ArrayList<>();
		this.hack = hack;
		this.parent = parent;
		this.settingsRendered = false;

		int offset = 15;
		for (Setting<?> setting : Settings.getSettingsFromHack(hack)) {
			if (setting instanceof ToggleSetting) {
				this.buttons.add(new ToggleSettingButton(x, y + offset, (ToggleSetting)setting, this));
			} else if (setting instanceof KeyBindSetting) {
				this.buttons.add(new BindSettingButton(x, y + offset, (KeyBindSetting)setting, this));
			} else if (setting instanceof ModeSetting) {
				this.buttons.add(new ModeSettingButton(x, y + offset, (ModeSetting)setting, this));
			} else if (setting instanceof DoubleSetting) {
				this.buttons.add(new DoubleSettingButton(x, y + offset, (DoubleSetting)setting, this));
			} else if (setting instanceof IntSetting) {
				this.buttons.add(new IntSettingButton(x, y + offset, (IntSetting)setting, this));
			}
		}
	}
	
	@Override
	public void render(MatrixStack matrices, TextRenderer textRenderer, int x, int y) {
		Screen.fill(matrices, x, y, x + this.width, y + this.height, new Color(red, green, blue, 100).getRGB());
		Screen.drawTextWithShadow(matrices, textRenderer, new LiteralText(hack.getName()), x, y, white);
		
		if (settingsRendered) {
			int offset = y + this.height;
			for (SettingButton<?, ?> button : this.buttons) {
				offset += button.height;
				button.render(matrices, textRenderer, x, y + offset);
			}
		}
	}
	
	@Override
	public void mouseClicked(double mousex, double mousey, int keyCode) {
		mc.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 2.0f));

		if (keyCode == 0) {
			this.hack.toggle();
		}
		if (keyCode == 1) {
			this.settingsRendered = !this.settingsRendered;
		}

		buttons.forEach(button -> {
			if (ScreenHelper.clickCheck(mousex, mousey, this.x, this.y, this.width, this.height)) {
				button.mouseClicked(mousex, mousey, keyCode);
			}
		});
	}

	@Override
	public void keyPressed(int keyCode, int scanCode, int modifiers) {
		this.buttons.forEach(button -> {
			button.keyPressed(keyCode, scanCode, modifiers);
		});
	}
}
