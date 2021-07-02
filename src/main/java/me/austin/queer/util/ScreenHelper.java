package me.austin.queer.util;

import java.awt.Color;

import me.austin.queer.module.hacks.client.ClickGui;

public interface ScreenHelper extends Globals {
	static int swidth = mc.getWindow().getScaledWidth();
	static int sheight = mc.getWindow().getScaledHeight();
	static int wpixel = swidth / mc.getWindow().getWidth();
	static int hpixel = sheight / mc.getWindow().getHeight();
	static double factor = mc.getWindow().getScaleFactor();
	static int red = ClickGui.backgroundRed.getValue();
	static int green = ClickGui.backgroundGreen.getValue();
	static int blue = ClickGui.backgroundBlue.getValue();

	static int white = new Color(255, 255, 255, 255).getRGB();

	static boolean clickCheck(double mousex, double mousey, int x, int y, int width, int height) {
		return mousex * factor > x * factor && 
			   mousex * factor < x * factor + width * factor && 
			   mousey * factor > y * factor && 
			   mousey * factor < y * factor + height * factor;
	}
}
