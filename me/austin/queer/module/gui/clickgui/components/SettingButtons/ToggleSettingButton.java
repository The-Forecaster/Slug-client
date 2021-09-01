package me.austin.queer.module.gui.clickgui.components.SettingButtons;

import me.austin.queer.module.gui.clickgui.components.HackButton;
import me.austin.queer.module.gui.clickgui.components.SettingButton;
import me.austin.queer.module.setting.settings.ToggleSetting;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class ToggleSettingButton extends SettingButton<ToggleSetting, Boolean> {
    public ToggleSettingButton(int x, int y, ToggleSetting setting, HackButton parent) {
        super(x, y, setting, parent);
    }

    @Override
    public void render(MatrixStack matrices, TextRenderer textRenderer, int x, int y) {
        if (getSetting().get()) {
            Screen.fill(matrices, x, y, x + width, y + height, backGroundHigh);
        } else {
            Screen.fill(matrices, x, y, x + width, y + height, backGroundLow);
        } 
        Screen.drawStringWithShadow(matrices, textRenderer, this.setting.getName(), x, y, white);
    }

    @Override
    public void mouseClicked(double mousex, double mousey, int keyCode) {
        this.setting.set(!setting.get());
    }
}
