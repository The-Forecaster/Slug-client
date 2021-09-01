package me.austin.queer.module.gui.clickgui.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import me.austin.queer.module.gui.clickgui.ClickGuiScreen;
import me.austin.queer.module.gui.clickgui.GuiComponent;
import me.austin.queer.module.hacks.Category;
import me.austin.queer.module.hacks.Hack;
import me.austin.queer.module.hacks.Hacks;
import me.austin.queer.util.render.ScreenHelper;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class CategoryFrame extends GuiComponent {
	private final Category category;
	public final List<HackButton> buttons = new ArrayList<>();
	public final ClickGuiScreen parent;
	public boolean dragging;
	
	public CategoryFrame(Category category, int x, int y, ClickGuiScreen parent) {
		super(category.getName(), category.getDescription(), x, y, 80 * ScreenHelper.wpixel(), Hacks.getHacksByCategory(category).size() * ScreenHelper.wpixel());
		this.category = category;
		this.parent = parent;

		int offset = 20 * ScreenHelper.wpixel();
		for (Hack hack : Hacks.getHacksByCategory(category)) {
			this.buttons.add(new HackButton(x, y + offset, hack, this));
			offset += 20 * ScreenHelper.wpixel();
		}
	}
	
	@Override
	public void render(MatrixStack matrices, TextRenderer textRenderer, int x, int y) {
		if (this.dragging) this.setPosition((int)mc.mouse.getX(), (int)mc.mouse.getY());

		Screen.fill(matrices, x, y, x + width, y + height, new Color(red, green, blue, 50).getRGB());
		Screen.drawTextWithShadow(matrices, textRenderer, new LiteralText(category.getName()), x, y, new Color(255, 255, 255, 255).getRGB());

		for (HackButton button : this.buttons) {
			button.render(matrices, textRenderer, x, y);
			y += button.height;
			if (button.settingsRendered) {
				for (SettingButton<?, ?> b : button.buttons) {
					y += b.height;
				}
			}
		}
	}
	
	@Override
	public void mouseClicked(double mousex, double mousey, int key) {
		if (ScreenHelper.clickCheck(mousex, mousey, x, y, width, height)) {
			this.dragging = true;
		}

		for (HackButton button : buttons) {
			if (ScreenHelper.clickCheck(mousex, mousey, button.x, button.y, button.width, button.height)) {
				button.mouseClicked(mousex, mousey, key);
			}
		}
	}

	@Override
	public void keyPressed(int keyCode, int scanCode, int modifiers) {
		this.buttons.forEach(button -> {
			button.keyPressed(keyCode, scanCode, modifiers);
		});
	}
}
