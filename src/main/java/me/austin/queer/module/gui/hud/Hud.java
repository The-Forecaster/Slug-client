package me.austin.queer.module.gui.hud;

import java.util.ArrayList;
import java.util.List;

import me.austin.queer.module.Modules;
import me.austin.queer.module.gui.hud.components.Logo;
import me.austin.queer.module.gui.hud.components.Watermark;
import me.austin.queer.util.Util;
import net.minecraft.client.util.math.MatrixStack;

public class Hud extends Modules<HudComponent> implements Util {
    public static List<HudComponent> COMPONENTS;

    public Hud() {
        COMPONENTS = new ArrayList<>();
        
        COMPONENTS.add(new Logo(10, 10));
        COMPONENTS.add(new Watermark(10, 200));
    }

    public static List<HudComponent> getComponents() {
        return COMPONENTS;
    }

    @Override
    public List<HudComponent> get() {
        return getComponents();
    }
    
    public static void onRenderOverlay(MatrixStack stack) {
        if (mc.currentScreen == null) {
            COMPONENTS.forEach(component -> {
                component.render(stack, mc.textRenderer, component.x, component.y);
            });
        }
    }
}
