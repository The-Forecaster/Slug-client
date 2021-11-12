package me.austin.queer.modules.gui.clickgui.components.buttons;

import me.austin.queer.modules.setting.settings.ToggleSetting;
import me.austin.queer.util.client.ColorHelper;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class ToggleSettingButton extends SettingButton<ToggleSetting> {
    public ToggleSettingButton(int x, int y, ToggleSetting setting, ModuleButton parent, boolean shown) {
        super(x, y, setting, parent, shown);
    }

    @Override
    public void render(MatrixStack matrices, TextRenderer textRenderer) {
        if (this.getSetting().get()) {
            Screen.fill(matrices, x, y, x + width, y + height, ColorHelper.backGroundLow);
        } else {
            Screen.fill(matrices, x, y, x + width, y + height, ColorHelper.backGroundHigh);
        } 
        Screen.drawStringWithShadow(matrices, textRenderer, this.setting.getName(), x, y, white);
    }

    @Override
    public void mouseClicked(double mousex, double mousey, int keyCode) {
        this.setting.set(!setting.get());
    }
}
