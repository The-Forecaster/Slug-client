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
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;

public class HackButton extends Component {
	public List<SettingButton> buttons;
	public Hack hack;
	public CategoryFrame parent;
	public boolean settingsRendered;
	
	public HackButton(int x, int y, Hack hack, CategoryFrame parent) {
		super(hack.getName(), hack.getDescription(), (int)(x * factor), (int)(y * factor), parent.width, (int)(20 * factor));
		this.buttons = new ArrayList<>();
		this.hack = hack;
		this.parent = parent;
		this.settingsRendered = false;

		for (Setting<Object> setting : hack.getSettings()) {
			buttons.add(new SettingButton((int) x, (int) y, setting, this));
		}
	}
	
	@Override
	public void render(MatrixStack matrices, TextRenderer textRenderer, int x, int y) {
		Screen.drawTextWithShadow(matrices, textRenderer, new LiteralText(hack.getName()), x, y, new Color(255, 255, 255, 190).getRGB());
		Screen.fill(matrices, x, y, x + this.width, y + this.height, new Color(90, 70, 200, 150).getRGB());
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
		mc.world.playSound(new BlockPos(mc.player.getPos()), SoundEvents.UI_BUTTON_CLICK, SoundCategory.MASTER, 10f, 10f, true);
		if (keyCode == 1) {
			this.hack.toggle();
		}
		if (keyCode == 2) {
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
