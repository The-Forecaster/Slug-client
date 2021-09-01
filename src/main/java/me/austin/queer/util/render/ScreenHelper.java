package me.austin.queer.util.render;

import me.austin.queer.util.Util;
import me.austin.queer.util.text.JColor;

public interface ScreenHelper extends Util {
    public static int white = new JColor(255, 255, 255, 255).getRGB();

	public static boolean clickCheck(double mousex, double mousey, int x, int y, int width, int height) {
		return mousex > x * swidth() && mousex < x * swidth() + width * swidth() && mousey > y * sheight() && mousey < y * sheight() + height * sheight();
	}

	public static int swidth() {
		if (mc.world == null) {
			return 1;
		} 
		return mc.getWindow().getScaledWidth();
	}

	public static int sheight() {
		if (mc.world == null) {
			return 1;
		} 
		return mc.getWindow().getScaledHeight();
	}

	public static int wpixel() {
		if (mc.world == null) {
			return 1;
		} 
		return mc.getWindow().getScaledWidth() / mc.getWindow().getHeight();
	}
	
	public static int hpixel() {
		if (mc.world == null) {
			return 1;
		}
		return mc.getWindow().getScaledHeight() / mc.getWindow().getHeight();
	}

	public static double factor() {
		if (mc.world == null) {
			return 1;
		}
		return mc.getWindow().getScaleFactor();
	}
}
