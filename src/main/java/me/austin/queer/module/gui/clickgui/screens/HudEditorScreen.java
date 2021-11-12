package me.austin.queer.modules.gui.clickgui.screens;

import java.util.ArrayList;
import java.util.List;

import me.austin.queer.modules.gui.clickgui.components.Frame;
import me.austin.queer.modules.gui.clickgui.components.buttons.HudComponentButton;
import me.austin.queer.modules.gui.hud.Hud;
import me.austin.queer.modules.gui.hud.HudModule;
import me.austin.queer.util.render.ScreenHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class HudEditorScreen extends Screen {
    private final List<HudComponentButton> components = new ArrayList<>();
    private final Frame buttonFrame = new Frame("Hud Modules", "",  100 * ScreenHelper.wpixel(), 20 * Hud.getInstance().get().size() * ScreenHelper.wpixel(), true, Hud.getInstance().get());

    public HudEditorScreen() {
        super(Text.of("Hudeditor"));

        for (HudModule component : Hud.getInstance().get()) {
            this.components.add(new HudComponentButton(component.x, component.y, component.width, component.height, true, component));
        }
    }

    @Override
    public void render(MatrixStack matrices, int mousex, int mousey, float delta) {
        this.buttonFrame.render(matrices, textRenderer);

        components.forEach(comp -> comp.render(matrices, textRenderer));
    }
}
