package me.austin.queer.module.gui.hud.components;

import me.austin.queer.module.gui.hud.HudComponent;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class Logo extends HudComponent {
    public Logo(int x, int y) {
        super("Shows the client's logo", "shows the logo of the client", x, y, 1280, 760);
    }

    @Override
    public void render(MatrixStack matrices, TextRenderer textRenderer, int x, int y) {
        mc.inGameHud.drawTexture(matrices, x, y, 0, 2, 1280, 760);
    }
}