package me.austin.queer.modules.gui.hud.components;

import me.austin.queer.modules.gui.hud.HudModule;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class Logo extends HudModule {
    public Logo(int x, int y) {
        super("Logo", "", x, y, 1280, 760, true);
    }

    @Override
    public void render(MatrixStack matrices, TextRenderer textRenderer) {
        mc.inGameHud.drawTexture(matrices, this.x, this.y, 0, 2, this.width, this.height);
    }
}