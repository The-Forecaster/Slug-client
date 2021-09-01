package me.austin.queer.module.gui.clickgui.components.SettingButtons;

import org.lwjgl.glfw.GLFW;

import me.austin.queer.module.gui.clickgui.components.HackButton;
import me.austin.queer.module.gui.clickgui.components.SettingButton;
import me.austin.queer.module.setting.Setting;
import me.austin.queer.module.setting.settings.KeyBindSetting;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class BindSettingButton extends SettingButton<KeyBindSetting, Integer> {
    private boolean listening = false;

    public BindSettingButton(int x, int y, Setting<Integer> setting, HackButton parent) {
        super(x, y, setting, parent);
    }

    @Override
    public void render(MatrixStack matrices, TextRenderer textRenderer, int x, int y) {
        Screen.fill(matrices, x, y, x + width, y + height, backGroundLow);
        Screen.drawStringWithShadow(matrices, textRenderer, "Bind : " + (this.setting.get() == 0 ? "None" : GLFW.glfwGetKeyScancode(this.getSetting().get())), x, y, white);
    }

    @Override
    public void mouseClicked(double mousex, double mousey, int keyCode) {
        if (this.listening) {
            this.listening = false;
        } else {
            this.listening = true;
        }
    }

    @Override
    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.listening) {
            getSetting().set(keyCode);
            this.listening = false;
        }
    }

    public boolean isListening() {
        return this.listening;
    }
}
