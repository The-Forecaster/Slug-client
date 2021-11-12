package me.austin.queer.modules.gui.hud.components;

import me.austin.queer.TransRights;
import me.austin.queer.modules.gui.hud.HudModule;
import me.austin.queer.util.text.TextFormatting;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class Watermark extends HudModule {
    public Watermark(int x, int y) {
        super("Watermark", "", x, y, 1280, 760, true);
    }

    @Override
    public void render(MatrixStack matrices, TextRenderer textRenderer) {
        mc.textRenderer.draw(matrices, TransRights.NAME, x, y, TextFormatting.DARK_PURPLE.getColorIndex());
    }
}