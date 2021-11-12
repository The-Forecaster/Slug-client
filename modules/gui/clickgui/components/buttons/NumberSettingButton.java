package me.austin.queer.modules.gui.clickgui.components.buttons;

import me.austin.queer.modules.setting.settings.NumberSetting;
import me.austin.queer.util.client.ColorHelper;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class NumberSettingButton extends SettingButton<NumberSetting<?>> {
    private Number filled;

    public NumberSettingButton(int x, int y, NumberSetting<?> setting, ModuleButton parent, boolean shown) {
        super(x, y, setting, parent, shown);
        this.filled = (int)((this.x + (int)setting.get() / (int)setting.getMax()) / this.x + this.width);
    }

    @Override
    public void render(MatrixStack matrices, TextRenderer textRenderer) {
        Screen.fill(matrices, this.x, this.y, this.x + this.width, this.y + this.height, ColorHelper.backGroundLow);
        Screen.fill(matrices, this.x, this.y, this.x + this.filled.intValue(), this.y + this.height, ColorHelper.backGroundHigh);
        Screen.drawStringWithShadow(matrices, textRenderer, setting.getName() + " : " + (Math.round((int)setting.get() * 10) / 10), this.x, this.y, white);
    }

    @Override
    public void mouseClicked(double mousex, double mousey, int keyCode) {
        this.filled = (int) ((mousex - this.x) * (double)this.setting.getMax() - (double)this.setting.getMin());
        this.setting.set(Math.round((this.x + setting.get().intValue()) / setting.getMax().intValue() / (this.x + this.width) * 10) / 10);
    }
}