package me.austin.queer.misc;

import java.awt.Color;

public class JColor extends Color {
	private static final long serialVersionUID = 1L;
	
	public JColor (Color color) {
		super(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}
	
	public static final JColor fromHSB (float hue, float saturation, float brightness) {
		return new JColor(Color.getHSBColor(hue, saturation, brightness));
	}
	
	public final float getHue() {
		return RGBtoHSB(getRed(), getGreen(), getBlue(),null)[0];
	}
	
	public final float getSaturation() {
		return RGBtoHSB(getRed(), getGreen(), getBlue(),null)[1];
	}
	
	public final float getBrightness() {
		return RGBtoHSB(getRed(), getGreen(), getBlue(),null)[2];
	}
}
