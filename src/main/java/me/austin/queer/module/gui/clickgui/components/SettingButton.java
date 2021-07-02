package me.austin.queer.module.gui.clickgui.components;

import java.awt.Color;

import org.lwjgl.glfw.GLFW;

import me.austin.queer.module.gui.clickgui.Component;
import me.austin.queer.module.setting.Setting;
import me.austin.queer.module.setting.settings.KeyBindSetting;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class SettingButton extends Component {
	private Setting setting;
	private String displayName;
	private HackButton parent;
	private double filled;
	private boolean listening;

	public SettingButton(int x, int y, Setting setting, HackButton parent) {
		super(setting.getName(), setting.getDescription(), x, y, parent.width, (int)(15 * factor));
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
		Screen.fill(matrices, this.x, this.y, this.x + this.width, this.y + this.height, new Color(200, 0, 50, 10).getRGB());
		Screen.drawTextWithShadow(matrices, textRenderer, new LiteralText(displayName + "  :  " + setting.getValue().toString()), x, y, new Color(100, 100, 100, 155).getRGB());

		if ((setting.getValue() instanceof Double || setting.getValue() instanceof Integer) && !(setting instanceof KeyBindSetting)) {
			Screen.fill(matrices, this.x, this.y, (int) (this.x + filled), this.y + height, new Color(100, 0, 50, 195).getRGB());
		}
	}
	
	@Override
	public void mouseClicked(double mousex, double mousey, int keyCode) {
		if (setting.getValue() instanceof KeyBindSetting) {
			this.displayName =  listening ? "Bind" + GLFW.glfwGetKeyScancode((int)setting.getValue()) : "Listening...";
			listening = !listening;
		} if (setting.getValue() instanceof Enum) {
			setting.cycle();
		} if (setting.getValue() instanceof Boolean) {
			this.setting.setValue(!(Boolean)this.setting.getValue());
		} if (setting.getValue() instanceof Double) {
			this.setting.setValue(Math.round((mousex - this.x) * filled / 10) / 10);
		}
		
		else {
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
