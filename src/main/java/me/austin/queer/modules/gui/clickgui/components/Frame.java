package me.austin.queer.modules.gui.clickgui.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import me.austin.queer.modules.Modulus;
import me.austin.queer.modules.gui.Component;
import me.austin.queer.modules.gui.clickgui.ClickGuiComponent;
import me.austin.queer.modules.gui.clickgui.components.buttons.BindSettingButton;
import me.austin.queer.modules.gui.clickgui.components.buttons.ModuleButton;
import me.austin.queer.util.client.ColorHelper;
import me.austin.queer.util.render.ScreenHelper;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class Frame extends ClickGuiComponent {
	private boolean dragging, open;

	protected final List<ModuleButton> buttons; 
	
	public Frame(String name, String description, int x, int y, boolean shown, List<? extends Modulus> modules) {
		super(name, description, x, y, 80 * ScreenHelper.wpixel(), modules.size() * ScreenHelper.wpixel(), shown);
		this.buttons = new ArrayList<>();

		int offset = 20 * ScreenHelper.wpixel();
		for (Modulus mod : modules) {
			this.getButtons().add(new ModuleButton(mod.getName(), mod.getDescription(), x, y + offset, mod, this, true));
			offset += 20 * ScreenHelper.wpixel();
		}
	}

	public List<ModuleButton> getButtons() {
		return this.buttons;
	}

	public boolean isDragging() {
		return this.dragging;
	}

	public void setDragging(boolean dragging) {
		this.dragging = dragging;
	}
	
	public void keyPressed(int keyCode, int scanCode, int modifiers) {
		this.getButtons().forEach(button ->  {
			button.getButtons().forEach(setting -> {
				if (setting instanceof BindSettingButton) {
					((BindSettingButton)setting).keyPressed(keyCode, scanCode, modifiers);
				}
			});
		});
	}

	public boolean isOpen() {
		return this.open;
	}
	
	@Override
	public void render(MatrixStack matrices, TextRenderer textRenderer) {
		if (this.dragging) this.setPosition((int)mc.mouse.getX(), (int)mc.mouse.getY());

		Screen.fill(matrices, this.x, this.y, this.x + this.width, this.y + this.height, ColorHelper.backGroundLow);
		Screen.drawTextWithShadow(matrices, textRenderer, new LiteralText(this.getName()), x, y, new Color(255, 255, 255, 255).getRGB());

		for (Component button : this.getButtons()) {
			button.render(matrices, textRenderer);
		}
	}
	
	@Override
	public void mouseClicked(double mousex, double mousey, int key) {
		if (ScreenHelper.clickCheck(mousex, mousey, this)) {
			this.dragging = true;
		}
	}
}
