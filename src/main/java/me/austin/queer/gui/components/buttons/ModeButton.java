package me.austin.queer.gui.components.buttons;

import me.austin.queer.gui.components.Button;
import me.austin.queer.modules.setting.settings.ModeSetting;
import me.austin.queer.util.client.ColorHelper;
import me.austin.queer.util.render.ScreenHelper;
import net.minecraft.client.util.math.MatrixStack;

public class ModeButton extends Button<ModeSetting> {
    public ModeButton(int x, int y, int width, int height, ModeSetting module) {
        super(x, y, width, height, module);
    }

    @Override
    public void mouseClicked(double mousex, double mousey, int button) {
        this.module.cycle();
    }

    @Override
    public void render(MatrixStack stack) {
        ScreenHelper.drawComponent(stack, ColorHelper.backGroundLow, this);
        ScreenHelper.drawString(stack, module.getName() + " : " + this.module.get().toString(), x, y, ColorHelper.white, false);
    }
}
