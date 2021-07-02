package me.austin.queer.module.gui.clickgui.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import me.austin.queer.module.gui.clickgui.Component;
import me.austin.queer.module.hacks.Hack;
import me.austin.queer.module.hacks.Category;
import me.austin.queer.util.ScreenHelper;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class CategoryFrame extends Component {
	private Category category;
	public List<HackButton> buttons;
	public ClickGuiScreen parent;

	public boolean dragging;

	private int backgroundRed, backgroundGreen, backgroundBlue;
	
	public CategoryFrame(Category category, List<Hack> hacks, int x, int y, ClickGuiScreen parent, int backgroundRed, int backgroundGreen, int backgroundBlue) {
		super(category.getName(), category.getDescription(), (int)(x * factor), (int)(y * factor), (int)(80 * factor), (int)(hacks.size() * 20 * factor));
		this.category = category;
		this.parent = parent;
		this.buttons = new ArrayList<>();
		this.backgroundRed = backgroundRed;
		this.backgroundGreen = backgroundGreen;
		this.backgroundBlue = backgroundBlue;

		for (Hack hack : hacks) {
			if (hack.getCategory() == category) {
				this.buttons.add(new HackButton(x, y, hack, this));
			}
		}
	}
	
	@Override
	public void render(MatrixStack matrices, TextRenderer textRenderer, int x, int y) {
		Screen.fill(new MatrixStack(), x, y, x + width, y + height, new Color(backgroundRed, backgroundGreen, backgroundBlue, 200).getRGB());
		Screen.drawTextWithShadow(matrices, textRenderer, new LiteralText(category.getName()), x, y, new Color(backgroundRed, backgroundGreen, backgroundBlue, 255).getRGB());
		for (HackButton button : this.buttons) {
			button.render(matrices, textRenderer, x, y);
			y += button.height;
			if (button.settingsRendered) y += button.hack.getSettings().size() * 15 * factor;
		}
	}
	
	@Override
	public void mouseClicked(double mousex, double mousey, int key) {
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
