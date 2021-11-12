package me.austin.queer.modules.gui.clickgui.components.buttons;

import me.austin.queer.modules.gui.clickgui.ClickGuiComponent;
import me.austin.queer.modules.gui.hud.HudModule;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class HudComponentButton extends ClickGuiComponent {

    public HudComponentButton(int x, int y, int width, int height, boolean shown, HudModule component) {
        super(component.getName(), component.getDescription(),x, y, width, height, shown);
    }

    @Override
    public void render(MatrixStack matrices, TextRenderer textRenderer) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseClicked(double mousex, double mousey, int keyCode) {
        // TODO Auto-generated method stub
        
    }
    
}
