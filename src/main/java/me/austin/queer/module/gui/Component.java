package me.austin.queer.module.gui;

import me.austin.queer.module.Module;
import me.austin.queer.module.hacks.client.ClickGui;
import me.austin.queer.util.render.ScreenHelper;
import me.austin.queer.util.text.JColor;
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

	public static final int red = ClickGui.backgroundRed.get();
	public static final int green = ClickGui.backgroundGreen.get();
	public static final int blue = ClickGui.backgroundBlue.get();

	public static final int backGroundLow = new JColor(red, green, blue, 100).getRGB();
	public static final int backGroundHigh = new JColor(red, green, blue, 195).getRGB();
	
	public abstract void render(MatrixStack matrices, TextRenderer textRenderer, int x, int y);

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
