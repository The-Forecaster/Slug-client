package me.austin.queer.module.gui.clickgui;

import me.austin.queer.module.gui.Component;

public abstract class GuiComponent extends Component {
    public GuiComponent(String name, String description, int x, int y, int width, int height) {
        super(name, description, x, y, width, height);
    }

    public void mouseClicked(double mousex, double mousey, int keyCode) {
    }

	public void keyPressed(int keyCode, int scanCode, int modifiers) {
	}
}
