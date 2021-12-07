package me.austin.queer.util.render;

import me.austin.queer.gui.Component;
import me.austin.queer.util.Util;
import me.austin.queer.util.client.ColorHelper;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

public class ScreenHelper extends DrawableHelper implements Util {
	private static ScreenHelper INSTANCE;

	private static ScreenHelper getInstance() {
		return INSTANCE;
	}

	public static void drawHoriLine(net.minecraft.client.util.math.MatrixStack matrices, int x1, int x2, int y, int color) {
		getInstance().drawHorizontalLine(matrices, x1, x2, y, color);
	}
	
	public static void drawVertLine(net.minecraft.client.util.math.MatrixStack matrices, int x, int y1, int y2, int color) {
		getInstance().drawVerticalLine(matrices, x, y1, y2, color);
	}

	public static boolean clickCheck(double mousex, double mousey, Component comp) {
		return mousex > comp.x() && 
			   mousex < comp.x() + comp.width() && 
			   mousey > comp.y() && 
			   mousey < comp.y() + comp.height();
	}

	public static int swidth() {
		return mc.getWindow().getScaledWidth();
	}

	public static int sheight() {
		return mc.getWindow().getScaledHeight();
	}

	public static int wpixel() {
		return mc.getWindow().getScaledWidth() / mc.getWindow().getWidth();
	}
	
	public static int hpixel() {
		return mc.getWindow().getScaledHeight() / mc.getWindow().getHeight();
	}

	public static double factor() {
		return mc.getWindow().getScaleFactor();
	}

	public static void drawString(MatrixStack stack, String text, int x, int y, int color, boolean shadow) {
		if (shadow) {
			mc.textRenderer.drawWithShadow(stack, text, x, y, color);
			mc.textRenderer.drawWithShadow(stack, text, x, y, color);
		}
		else {
			mc.textRenderer.draw(stack, text, x, y, color);
		}
	}

	public static void drawComponent(MatrixStack stack, int color, Component component) {
		fill(stack, component.x(), component.y(), component.x() + component.width(), component.y() + component.height(), color);
		drawString(stack, component.getText(), component.x(),  component.y(), ColorHelper.white, false);
	}

	static {
		INSTANCE = new ScreenHelper();
	}
}
