package me.austin.queer.module.gui.clickgui.components.SettingButtons;

import me.austin.queer.module.gui.clickgui.components.HackButton;
import me.austin.queer.module.gui.clickgui.components.SettingButton;
import me.austin.queer.module.setting.settings.ModeSetting;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class ModeSettingButton extends SettingButton<ModeSetting, Enum<?>> {
    public ModeSetting setting;

    public ModeSettingButton(int x, int y, ModeSetting setting, HackButton parent) {
        super(x, y, setting, parent);
        this.setting = setting;
    }

    @Override
    public void render(MatrixStack matrices, TextRenderer textRenderer, int x, int y) {
        Screen.fill(matrices, x, y, x + width, y + height, backGroundLow);
        Screen.drawStringWithShadow(matrices, textRenderer, setting.getName() + " : " + setting.get(), x, y, white);
    }

    @Override
    public void mouseClicked(double mousex, double mousey, int keyCode) {
        this.setting.cycle();
    }
}
