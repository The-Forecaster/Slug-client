package me.austin.queer.gui.components.buttons;

import java.util.ArrayList;
import java.util.List;

import me.austin.queer.gui.components.Button;
import me.austin.queer.modules.hacks.Hack;
import me.austin.queer.modules.setting.Setting;
import me.austin.queer.modules.setting.settings.KeyBindSetting;
import me.austin.queer.modules.setting.settings.ModeSetting;
import me.austin.queer.modules.setting.settings.NumberSetting;
import net.minecraft.client.util.math.MatrixStack;

public class HackButton extends Button<Hack> {
    private boolean open;
    private List<Button<?>> children = new ArrayList<>();

    public HackButton(int x, int y, int width, int height, Hack hack) {
        super(x, y, width, height, hack);

        int offset = 10;
        for (Setting<?> setting : hack.getSettings().get()) {
            if (setting instanceof KeyBindSetting) {
                children.add(new KeyButton(this.x() + offset, this.y() + offset, this.width, 15, (KeyBindSetting)setting));
            } 
            else if (setting instanceof ModeSetting) {
                children.add(new ModeButton(this.x() + offset, this.y() + offset, this.width, 15, (ModeSetting)setting));
            }
            else if (setting instanceof NumberSetting) {
                
            }

            offset += 15;
        }
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    @Override
    public void render(MatrixStack stack) {
    }

    @Override
    public void mouseClicked(double mousex, double mousey, int button) {
        if (button == 1)
            this.module.toggle();
        else if (button == 2)
            this.open = !this.open;
    }
}
