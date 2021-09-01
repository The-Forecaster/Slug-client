package me.austin.queer.module.gui.clickgui.components.SettingButtons;

import me.austin.queer.module.gui.clickgui.components.HackButton;
import me.austin.queer.module.gui.clickgui.components.SettingButton;
import me.austin.queer.module.setting.settings.DoubleSetting;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class DoubleSettingButton extends SettingButton<DoubleSetting, Double> {
    private int filled;
    private DoubleSetting setting;

    public DoubleSettingButton(int x, int y, DoubleSetting setting, HackButton parent) {
        super(x, y, setting, parent);
        this.filled = (int)((this.x + setting.get() / setting.maxValue) / this.x + this.width);
        this.setting = setting;
    }

    @Override
    public void render(MatrixStack matrices, TextRenderer textRenderer, int x, int y) {
        Screen.fill(matrices, this.x, this.y, this.x + this.width, this.y + this.height, backGroundLow);
        Screen.fill(matrices, this.x, this.y, this.x + this.filled, this.y + this.height, backGroundHigh);
        Screen.drawStringWithShadow(matrices, textRenderer, setting.getName() + " : " + (Math.round(setting.get() * 10) / 10), x, y, white);
    }

    @Override
    public void mouseClicked(double mousex, double mousey, int keyCode) {
        this.filled = (int)((mousex - this.x) * (this.setting.maxValue - this.setting.minValue));
        this.setting.set((double)Math.round(((this.x + setting.get()) / setting.maxValue) / (this.x + this.width) * 10) / 10);
    }
}
