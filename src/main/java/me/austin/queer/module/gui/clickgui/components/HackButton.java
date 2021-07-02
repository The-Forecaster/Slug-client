package me.austin.queer.module.gui.clickgui.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import me.austin.queer.module.gui.clickgui.Component;
import me.austin.queer.module.hacks.Hack;
import me.austin.queer.module.setting.Setting;
import me.austin.queer.util.ScreenHelper;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;

public class HackButton extends Component {
	public List<SettingButton> buttons;
	public Hack hack;
	public CategoryFrame parent;
	public boolean settingsRendered;
	
	public HackButton(int x, int y, Hack hack, CategoryFrame parent) {
		super(hack.getName(), hack.getDescription(), (int)(x * wpixel), (int)(y * hpixel), parent.width, (int)(20 * hpixel));
		this.buttons = new ArrayList<>();
		this.hack = hack;
		this.parent = parent;
		this.settingsRendered = false;

		for (Setting<?> setting : hack.getSettings()) {
			buttons.add(new SettingButton((int) x, (int) y, setting, this));
		}
	}
	
	@Override
	public void render(MatrixStack matrices, TextRenderer textRenderer, int x, int y) {
		Screen.fill(matrices, x, y, x + this.width, y + this.height, new Color(red, green, blue, 50).getRGB());
		Screen.drawTextWithShadow(matrices, textRenderer, new LiteralText(hack.getName()), x, y, white);
		
		if (settingsRendered) {
			int settingPos = y + this.height * hpixel;
			for (SettingButton button : this.buttons) {
				settingPos += button.height * hpixel;
				button.render(matrices, textRenderer, x, settingPos);
			}
		}
	}
	
	@Override
	public void mouseClicked(double mousex, double mousey, int keyCode) {
		mc.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));

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
