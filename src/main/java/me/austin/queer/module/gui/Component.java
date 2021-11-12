package me.austin.queer.modules.gui;

import me.austin.queer.modules.Modulus;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

public abstract class Component extends Modulus {
	public int x, y, width, height;
	protected boolean shown;

	public Component(String name, String description, int x, int y, int width, int height, boolean shown) {
		super(name, description);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.shown = shown;
	}
	
	public abstract void render(MatrixStack matrices, TextRenderer textRenderer);
	
	public boolean isShown() {
		return this.shown;
	}

	public void setShown(boolean shown) {
		this.shown = shown;
	}
	
	public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}