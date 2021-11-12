package me.austin.queer.util.client;

import me.austin.queer.modules.hacks.client.ClickGui;
import me.austin.queer.util.text.JColor;

public class ColorHelper {
    public static final int backGroundLow = new JColor(red(), green(), blue(), 100).getRGB(), backGroundHigh = new JColor(red(), green(), blue(), 195).getRGB(), white = new JColor(255, 255, 255, 255).getRGB();;

    public static int red() {
        return ClickGui.getInstance().backgroundRed.get();
    }

    public static int green() {
        return ClickGui.getInstance().backgroundGreen.get();
    }

    public static int blue() {
        return ClickGui.getInstance().backgroundBlue.get();
    }
}
