package me.austin.queer.module.gui.clickgui.components;

import java.util.List;

import me.austin.queer.TransRights;
import me.austin.queer.module.hacks.Category;
import me.austin.queer.module.hacks.Hacks;
import me.austin.queer.module.setting.Setting;
import me.austin.queer.util.ScreenHelper;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class ClickGuiScreen extends Screen implements ScreenHelper {
	public static List<CategoryFrame> frames;
	public static boolean shouldCloseOnEsc;
	
	private int x, y, backgroundRed, backgroundGreen, backgroundBlue;
	
	public ClickGuiScreen(Setting<Integer> backgroundRed, Setting<Integer> backgroundGreen, Setting<Integer> backgroundBlue) {
		super(new LiteralText(TransRights.modname));

		this.backgroundRed = backgroundRed.getValue();
		this.backgroundGreen = backgroundGreen.getValue();
		this.backgroundBlue = backgroundBlue.getValue();

		for (Category category : Category.values()) {
			frames.add(new CategoryFrame(category, Hacks.getHacksByCategory(category), x, y, this, this.backgroundRed, this.backgroundGreen, this.backgroundBlue));
		}
	}
	
	@Override
	public void render(MatrixStack matrices, int mousex, int mousey, float delta) {
		x = (int)(20d * wpixel);
		y = (int)(20d * hpixel);
		for (CategoryFrame frame : frames) {
			frame.render(matrices, mc.textRenderer, x, y);
			x += 80 * wpixel;
		}
	}
	
	@Override
	public boolean mouseClicked(double mousex, double mousey, int button) {
		for (CategoryFrame frame : frames) {
			if (ScreenHelper.clickCheck(mousex, mousey, x, y, frame.width, frame.height)) {
				frame.mouseClicked(mousex, mousey, button);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		frames.forEach(frame -> {
			frame.keyPressed(keyCode, scanCode, modifiers);
		}); 
		return true;
	}

	@Override
	public boolean shouldCloseOnEsc() {
		return shouldCloseOnEsc;
	}
}
