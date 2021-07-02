package me.austin.queer.module.gui.clickgui.components;

import java.util.List;

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
	public Hacks hacks;
	
	public ClickGuiScreen(Hacks hacks, Setting<Integer> backgroundRed, Setting<Integer> backgroundGreen, Setting<Integer> backgroundBlue) {
		super(new LiteralText("ClickGuiScreen"));

		this.hacks = hacks;
		int offset = 20;
		for (Category category : Category.values()) {
			frames.add(new CategoryFrame(category, (20 + offset) * wpixel, 20 * hpixel, this));
			offset += 60;
		}
	}

	@Override
	public void render(MatrixStack matrices, int mousex, int mousey, float delta) {
		for (CategoryFrame frame : frames) {
			frame.render(matrices, mc.textRenderer, frame.x, frame.y);
		}
	}
	
	@Override
	public boolean mouseClicked(double mousex, double mousey, int button) {
		for (CategoryFrame frame : frames) {
			if (ScreenHelper.clickCheck(mousex, mousey, frame.x, frame.y, frame.width, 20)) {
				frame.mouseClicked(mousex, mousey, button);
				return true;
			}
		}
		return false;
	}

	@Override 
	public boolean mouseReleased(double mousex, double mousey, int button) {
		for (CategoryFrame frame : frames) {
			if (frame.dragging) {
				frame.dragging = false;
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

	@Override
	public void renderBackground(MatrixStack matrices, int vOffset) {
		return;
	}
}
