package me.austin.queer.module.gui.clickgui.components;

import java.awt.Color;

import org.lwjgl.glfw.GLFW;

import me.austin.queer.module.gui.clickgui.Component;
import me.austin.queer.module.setting.Setting;
import me.austin.queer.module.setting.settings.KeyBindSetting;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;

public class SettingButton extends Component {
	private Setting setting;
	private String displayName;
	private HackButton parent;
	private double filled;
	private boolean listening;

	public SettingButton(int x, int y, Setting setting, HackButton parent) {
		super(setting.getName(), setting.getDescription(), x, y, parent.width, 15);
		this.setting = setting;
		this.parent = parent;
		this.displayName = setting instanceof KeyBindSetting ? "Bind" + GLFW.glfwGetKeyScancode((int)setting.getValue()) : setting.getName();

		if (setting.getValue() instanceof Double || setting.getValue() instanceof Integer) {
			this.filled = ((double)setting.getValue() - (double)setting.getMinValue()) / ((double)setting.getMaxValue() - (double)setting.getMinValue()) * 100;
			if (setting.getValue() instanceof Integer) {
				this.filled = Math.floor(this.filled);
			}
		}
	}
	
	@Override
	public void render(MatrixStack matrices, TextRenderer textRenderer, int x, int y) {
		Screen.fill(matrices, this.x, this.y, this.x + this.width, this.y + this.height, new Color(red, green, blue, 100).getRGB());
		Screen.drawTextWithShadow(matrices, textRenderer, new LiteralText(displayName + "  :  " + setting.getValue().toString()), x, y, new Color(255, 255, 255, 255).getRGB());

		if ((setting.getValue() instanceof Double || setting.getValue() instanceof Integer) && !(setting instanceof KeyBindSetting)) {
			Screen.fill(matrices, this.x, this.y, (int) (this.x + filled), this.y + height, new Color(red, green, blue, 150).getRGB());
		}
	}

	@Override
	public void mouseClicked(double mousex, double mousey, int keyCode) {	
		mc.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));

		if (setting.getValue() instanceof KeyBindSetting) {
			this.displayName =  listening ? "Bind" : "Listening...";
			this.listening = !listening;
		} if (setting.getValue() instanceof Enum) {
			this.setting.cycle();
		} if (setting.getValue() instanceof Boolean) {
			this.setting.setValue(!(Boolean)this.setting.getValue());
		} if (setting.getValue() instanceof Double) {
			this.setting.setValue(Math.round((mousex - this.x) * filled / 10) / 10);
		} else {
			this.setting.setValue((mousex - this.x) * filled / 100);
		}
	}

	@Override
	public void keyPressed(int keyCode, int scanCode, int modifiers) {
		if (this.listening) {
			this.setting.setValue(keyCode);
			this.listening = false;
		}
	}

	public HackButton getParent() {
		return parent;
	}	

	public boolean isListening() {
		return this.listening;
	}

	public void setListening(boolean listening) {
		this.listening = listening;
	}

	public Setting getSetting() {
		return this.setting;
	}
}
