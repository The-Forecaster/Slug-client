package me.austin.queer.modules.gui.clickgui.screens;

import java.util.ArrayList;
import java.util.List;

import me.austin.queer.TransRights;
import me.austin.queer.modules.gui.clickgui.ClickGuiComponent;
import me.austin.queer.modules.gui.clickgui.components.Frame;
import me.austin.queer.modules.hacks.Category;
import me.austin.queer.modules.hacks.Hacks;
import me.austin.queer.util.Util;
import me.austin.queer.util.render.ScreenHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class ClickGuiScreen extends Screen implements Util {
	protected static ClickGuiScreen INSTANCE;

	private boolean closeOnEsc = true;
	private List<Frame> frames = new ArrayList<>();
		
	public ClickGuiScreen() {
		super(Text.of(TransRights.NAME));

		int offset = 20 * ScreenHelper.hpixel();
		for (Category category : Category.values()) {
			this.frames.add(new Frame(category.getName(), category.getDescription(), 20 * ScreenHelper.wpixel() + offset, 20 * ScreenHelper.wpixel(), true, Hacks.getInstance().getHacksByCategory(category)));
			offset += 60 * ScreenHelper.wpixel();
		}
		this.setInstance();
	}

	public static ClickGuiScreen getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ClickGuiScreen();
		}
		return INSTANCE;
	}

	private void setInstance() {
		INSTANCE = this;
	}

	@Override
	public void render(MatrixStack matrices, int mousex, int mousey, float delta) {
		for (Frame frame : frames) {
			frame.render(matrices, mc.textRenderer);
		}
	}
	
	@Override
	public boolean mouseClicked(double mousex, double mousey, int button) {
		for (Frame frame : frames) {
			if (ScreenHelper.clickCheck(mousex, mousey, frame)) {
				frame.mouseClicked(mousex, mousey, button);
				return super.mouseClicked(mousex, mousey, button);
			}
			if (frame.isOpen()) {
				for (ClickGuiComponent component : frame.getButtons()) {
					if (ScreenHelper.clickCheck(mousex, mousey, component)) {
						component.mouseClicked(mousex, mousey, button);
						return super.mouseClicked(mousex, mousey, button);
					}
				}
			}
		}
		return super.mouseClicked(mousex, mousey, button);
	}

	@Override 
	public boolean mouseReleased(double mousex, double mousey, int button) {
		for (Frame frame : frames) {
			if (frame.isDragging()) {
				frame.setDragging(false);
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
		this.frames.forEach(frame -> frame.keyPressed(keyCode, scanCode, modifiers));
		return super.keyPressed(keyCode, scanCode, modifiers);
	}

	@Override
	public void renderBackground(MatrixStack matrices, int vOffset) {
		return;
	}

	@Override
	public boolean shouldCloseOnEsc() {
		return this.closeOnEsc;
	}
}
