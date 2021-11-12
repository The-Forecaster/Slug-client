package me.austin.queer.modules.gui.hud;

import me.austin.queer.modules.Modules;
import me.austin.queer.modules.gui.hud.components.*;
import me.austin.queer.util.Util;
import net.minecraft.client.util.math.MatrixStack;

public class Hud extends Modules<HudModule> implements Util {
    private static Hud INSTANCE;

    public Hud() {
        this.get().add(new Logo(10, 10));
        this.get().add(new ModuleList(mc.currentScreen.width, mc.currentScreen.height));
        this.get().add(new Watermark(100, 10));

        INSTANCE = this;
    }
    
    public void onRenderOverlay(MatrixStack stack) {
        if (mc.currentScreen == null) {
            this.get().forEach(component -> {
                component.render(stack, mc.textRenderer);
            });
        }
    }

    public static Hud getInstance() {
        return INSTANCE;
    }
}
