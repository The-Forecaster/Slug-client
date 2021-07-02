package me.austin.queer.module.gui.clickgui;

import me.austin.queer.module.Module;
import me.austin.queer.util.ScreenHelper;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

public abstract class Component extends Module implements ScreenHelper {
	public int x, y, width, height;
	public Component(String name, String description, int x, int y, int width, int height) {
		super(name, description);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public abstract void render(MatrixStack matrices, TextRenderer textRenderer, int x, int y);
	
	public abstract void mouseClicked(double mousex, double mousey, int keyCode);

	public abstract void keyPressed(int keyCode, int scanCode, int modifiers);

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
