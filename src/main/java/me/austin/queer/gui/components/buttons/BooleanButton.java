package me.austin.queer.gui.components.buttons;

import me.austin.queer.gui.components.Button;
import me.austin.queer.modules.setting.settings.ToggleSetting;
import me.austin.queer.util.client.ColorHelper;
import me.austin.queer.util.render.ScreenHelper;
import net.minecraft.client.util.math.MatrixStack;

public class BooleanButton extends Button<ToggleSetting> {
    public BooleanButton(int x, int y, int width, int height, ToggleSetting setting) {
        super(x, y, width, height, setting);
    }

    @Override
    public void mouseClicked(double mousex, double mousey, int button) {
        this.module.toggle();
    }

    @Override
    public void render(MatrixStack stack) {
        ScreenHelper.drawComponent(stack, this.module.get() ? ColorHelper.backGroundHigh : ColorHelper.backGroundLow, this);
    }
}
