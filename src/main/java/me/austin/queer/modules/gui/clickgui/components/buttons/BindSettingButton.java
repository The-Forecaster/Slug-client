package me.austin.queer.modules.gui.clickgui.components.buttons;

import org.lwjgl.glfw.GLFW;

import me.austin.queer.modules.setting.settings.KeyBindSetting;
import me.austin.queer.util.client.ColorHelper;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class BindSettingButton extends SettingButton<KeyBindSetting> {
    protected boolean listening = false;

    public BindSettingButton(int x, int y, KeyBindSetting setting, ModuleButton parent, boolean shown) {
        super(x, y, setting, parent, shown);
    }

    @Override
    public void render(MatrixStack matrices, TextRenderer textRenderer) {
        Screen.fill(matrices, x, y, x + width, y + height, ColorHelper.backGroundLow);
        if (this.listening)
            Screen.drawStringWithShadow(matrices, textRenderer, "Bind : " + (this.setting.get() == 0 ? "None" : GLFW.glfwGetKeyScancode(this.getSetting().get())), x, y, white);
        else
            Screen.drawStringWithShadow(matrices, textRenderer, "Listening...", x, y, white);
    }

    @Override
    public void mouseClicked(double mousex, double mousey, int keyCode) {
        this.listening = !this.listening;
    }

    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.listening) {
            this.getSetting().set(keyCode);
            this.listening = false;
        }
    }

    public boolean isListening() {
        return this.listening;
    }
}
