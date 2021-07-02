package me.austin.queer.util;

public interface ScreenHelper extends Globals {
	static int width = mc.getWindow().getScaledWidth();
	static int height = mc.getWindow().getScaledHeight();
	static int wpixel = width / mc.getWindow().getWidth();
	static int hpixel = height / mc.getWindow().getHeight();
	static double factor = mc.getWindow().getScaleFactor();

	static boolean clickCheck(double mousex, double mousey, int x, int y, int width, int height) {
		return mousex * factor > x * factor && 
			   mousex * factor < x * factor + width * factor && 
			   mousey * factor > y * factor && 
			   mousey * factor < y * factor + height * factor;
	}
}
