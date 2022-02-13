package trans.rights.client.misc;

import java.awt.Color;
import java.io.Serial;

public final class JColor extends Color {
    @Serial
    private static final long serialVersionUID = 1L;

    public JColor(Color color) {
        super(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public static JColor fromHSB(float hue, float saturation, float brightness) {
        return new JColor(Color.getHSBColor(hue, saturation, brightness));
    }

    public float getHue() {
        return RGBtoHSB(getRed(), getGreen(), getBlue(), null)[0];
    }

    public float getSaturation() {
        return RGBtoHSB(getRed(), getGreen(), getBlue(), null)[1];
    }

    public float getBrightness() {
        return RGBtoHSB(getRed(), getGreen(), getBlue(), null)[2];
    }
}
