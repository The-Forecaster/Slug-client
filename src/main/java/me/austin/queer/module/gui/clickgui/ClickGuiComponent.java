package me.austin.queer.modules.gui.clickgui;

import me.austin.queer.modules.gui.Component;
import me.austin.queer.util.render.ScreenHelper;

public abstract class ClickGuiComponent extends Component implements ScreenHelper {
    public ClickGuiComponent(String name, String description, int x, int y, int width, int height, boolean shown) {
        super(name, description, x, y, width, height, shown);
    }
    
    public abstract void mouseClicked(double mousex, double mousey, int keyCode);
}
