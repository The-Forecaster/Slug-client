package me.austin.queer.misc.color;

import java.awt.Color;

/** 
 * This is an attempt to rework the base color class, just a little improvement nothing super special
 * @author srgtmooomoo
*/

public class JColor extends Color {
	private static final long serialVersionUID = 1L;
	
	public JColor (Color color) {
		super(color.getRed(),color.getGreen(),color.getBlue(),color.getAlpha());
	}
	
	public static JColor fromHSB (float hue, float saturation, float brightness) {
		return new JColor(Color.getHSBColor(hue,saturation,brightness));
	}
	
	public float getHue() {
		return RGBtoHSB(getRed(), getGreen(), getBlue(),null)[0];
	}
	
	public float getSaturation() {
		return RGBtoHSB(getRed(), getGreen(), getBlue(),null)[1];
	}
	
	public float getBrightness() {
		return RGBtoHSB(getRed(), getGreen(), getBlue(),null)[2];
	}
}
