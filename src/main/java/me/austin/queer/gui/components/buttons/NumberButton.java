package me.austin.queer.gui.components.buttons;

import me.austin.queer.gui.components.Button;
import me.austin.queer.modules.setting.settings.NumberSetting;

public class NumberButton extends Button<NumberSetting<?>> {
    private int filled;

    public NumberButton(int x, int y, int width, int height, NumberSetting<?> module) {
        super(x, y, width, height, module);
    }

    public int getFilled() {
        return this.filled;
    }

    @Override
    public void mouseClicked(double mousex, double mousey, int button) {
        this.filled = (int) ((this.x + this.width) - mousex / (this.x + this.width));
        this.module.set((double)this.module.get() * filled / 100);
    }
}
