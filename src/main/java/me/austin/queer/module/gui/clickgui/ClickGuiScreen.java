package me.austin.queer.module.gui.clickgui;

import java.util.ArrayList;
import java.util.List;

import me.austin.queer.module.gui.clickgui.components.CategoryFrame;
import me.austin.queer.module.hacks.Category;
import me.austin.queer.module.hacks.Hacks;
import me.austin.queer.module.hacks.client.ClickGui;
import me.austin.queer.module.setting.Setting;
import me.austin.queer.util.Util;
import me.austin.queer.util.render.ScreenHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class ClickGuiScreen extends Screen implements Util {
	public static ClickGuiScreen INSTANCE;

	public final Hacks hacks;
	public List<CategoryFrame> frames;
	public static boolean shouldCloseOnEsc = true;
	
	public ClickGuiScreen(Hacks hacks, Setting<Integer> backgroundRed, Setting<Integer> backgroundGreen, Setting<Integer> backgroundBlue) {
		super(new LiteralText("ClickGuiScreen"));
		this.hacks = hacks;
		int offset = 20 * ScreenHelper.wpixel();

		frames = new ArrayList<>();
		for (Category category : Category.values()) {
			this.frames.add(new CategoryFrame(category, 20 + offset, 20 * ScreenHelper.wpixel(), this));
			offset += 60 * ScreenHelper.wpixel();
		}
		INSTANCE = this;
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
			}
		}
		mc.openScreen(INSTANCE);
		return super.mouseClicked(mousex, mousey, button);
	}

	@Override 
	public boolean mouseReleased(double mousex, double mousey, int button) {
		for (CategoryFrame frame : frames) {
			if (frame.dragging) {
				frame.dragging = false;
			}
		}
		return super.mouseReleased(mousex, mousey, button);
	} 
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		this.frames.forEach(frame -> {
			frame.keyPressed(keyCode, scanCode, modifiers);
		});
		mc.openScreen(INSTANCE);
		return super.keyPressed(keyCode, scanCode, modifiers);
	}

	@Override
	public boolean shouldCloseOnEsc() {
		return shouldCloseOnEsc;
	}

	@Override
	public void renderBackground(MatrixStack matrices, int vOffset) {
		return;
	}

	@Override
	public void onClose() {
		Hacks.getHackByClass(ClickGui.class).disable();
	}
}
