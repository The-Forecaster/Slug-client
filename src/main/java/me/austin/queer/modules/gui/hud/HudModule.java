package me.austin.queer.modules.gui.hud;

import me.austin.queer.modules.gui.Component;
import me.austin.queer.util.Util;

public abstract class HudModule extends Component implements Util {
    public HudModule(String name, String description, int x, int y, int width, int height, boolean shown) {
        super(name, description, x, y, width, height, shown);
    }
}
