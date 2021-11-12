package me.austin.queer.modules.gui.clickgui.components.buttons;

import me.austin.queer.modules.setting.settings.ModeSetting;
import me.austin.queer.util.client.ColorHelper;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class ModeSettingButton extends SettingButton<ModeSetting> {
    public ModeSettingButton(int x, int y, ModeSetting setting, ModuleButton parent, boolean shown) {
        super(x, y, setting, parent, shown);
    }

    @Override
    public void render(MatrixStack matrices, TextRenderer textRenderer) {
        Screen.fill(matrices, x, y, x + width, y + height, ColorHelper.backGroundLow);
        Screen.drawStringWithShadow(matrices, textRenderer, setting.getName() + " : " + setting.get(), x, y, white);
    }

    @Override
    public void mouseClicked(double mousex, double mousey, int keyCode) {
        this.setting.cycle();
    }
}
