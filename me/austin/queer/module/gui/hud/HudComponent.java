package me.austin.queer.module.gui.hud;

import me.austin.queer.module.gui.Component;

public abstract class HudComponent extends Component {
    public HudComponent(String name, String description, int x, int y, int width, int height) {
        super(name, description, x, y, width, height);
    }
}
