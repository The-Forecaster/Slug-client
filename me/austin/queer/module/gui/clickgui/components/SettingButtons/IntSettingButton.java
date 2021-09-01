package me.austin.queer.module.gui.clickgui.components.SettingButtons;

import me.austin.queer.module.gui.clickgui.components.HackButton;
import me.austin.queer.module.gui.clickgui.components.SettingButton;
import me.austin.queer.module.setting.settings.IntSetting;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class IntSettingButton extends SettingButton<IntSetting, Integer> {
    private int filled, difvalue;
    private IntSetting setting;

    public IntSettingButton(int x, int y, IntSetting setting, HackButton parent) {
        super(x, y, setting, parent);
        this.filled = (this.x + setting.get() / setting.maxValue) / this.x + this.width;
        this.difvalue = setting.maxValue - setting.minValue;
        this.setting = setting;
    }

    @Override
    public void render(MatrixStack matrices, TextRenderer textRenderer, int x, int y) {
        Screen.fill(matrices, x, y, x + width, y + height, backGroundLow);
        Screen.fill(matrices, x, y, x + filled, y + height, backGroundHigh);
        Screen.drawStringWithShadow(matrices, textRenderer, setting.getName() + " : " + setting.get(), x, y, white);
    }

    @Override
    public void mouseClicked(double mousex, double mousey, int keyCode) {
        this.filled = (int)((mousex - this.x) * (difvalue));
        this.setting.set((int)(mousex - this.x) * (this.setting.maxValue - this.setting.minValue));
    }
}
