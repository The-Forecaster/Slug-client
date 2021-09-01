package me.austin.queer.module.gui.hud.components;

import me.austin.queer.TransRights;
import me.austin.queer.module.gui.hud.HudComponent;
import me.austin.queer.util.text.TextFormatting;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class Watermark extends HudComponent {
    public Watermark(int x, int y) {
        super("Watermark", "Shows the watermark for the client", x, y, 1280, 760);
    }

    @Override
    public void render(MatrixStack matrices, TextRenderer textRenderer, int x, int y) {
        mc.textRenderer.draw(matrices, TransRights.modname, x, y, TextFormatting.DARK_PURPLE.getColorIndex());
    }
}