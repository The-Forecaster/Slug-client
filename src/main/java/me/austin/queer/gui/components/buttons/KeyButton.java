package me.austin.queer.gui.components.buttons;

import org.lwjgl.glfw.GLFW;

import me.austin.queer.gui.components.Button;
import me.austin.queer.modules.setting.settings.KeyBindSetting;
import me.austin.queer.util.client.ColorHelper;
import me.austin.queer.util.render.ScreenHelper;
import net.minecraft.client.util.math.MatrixStack;

public class KeyButton extends Button<KeyBindSetting> {
    private boolean listening;

    public KeyButton(int x, int y, int width, int height, KeyBindSetting module) {
        super(x, y, width, height, module);
    }

    public boolean isListening() {
        return this.listening;
    }

    @Override
    public void mouseClicked(double mousex, double mousey, int button) {
        this.listening = !this.listening;
    }

    @Override
    public void render(MatrixStack stack) {
        ScreenHelper.drawComponent(stack, ColorHelper.backGroundLow, this);
        ScreenHelper.drawString(stack, module.getName() + GLFW.glfwGetKeyName(this.module.get(), GLFW.glfwGetKeyScancode(this.module.get())), x, y, ColorHelper.backGroundLow, false);
    }
}
