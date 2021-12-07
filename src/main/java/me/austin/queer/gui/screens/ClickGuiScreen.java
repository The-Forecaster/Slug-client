package me.austin.queer.gui.screens;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;

import me.austin.queer.gui.ClientScreen;
import me.austin.queer.gui.Component;
import me.austin.queer.gui.components.Button;
import me.austin.queer.gui.components.Frame;
import me.austin.queer.gui.components.frames.CategoryFrame;
import me.austin.queer.modules.hacks.Category;
import me.austin.queer.util.render.ScreenHelper;
import net.minecraft.client.util.math.MatrixStack;

@ClientScreen.Register(name = "ClickGuiScreen", description = "Opens the module Clickgui for this client")
public class ClickGuiScreen extends ClientScreen {
	protected static ClickGuiScreen INSTANCE = new ClickGuiScreen();
	private boolean closeOnEsc = true;
	private List<CategoryFrame> frames = new ArrayList<>();
		
	public ClickGuiScreen() {
		super(ClickGuiScreen.class.getAnnotation(Register.class));

		int offset = 20 * ScreenHelper.hpixel();
		for (Category category : Category.values()) {
			this.frames.add(new CategoryFrame(20 + offset, 20, 40, 120, this, category));
			offset += 60 * ScreenHelper.wpixel();
		}
		INSTANCE = this;
	}

	public static ClickGuiScreen getInstance() {
		return INSTANCE;
	}

	private void forEachShown(@NotNull Consumer<Component> action) {
		for (CategoryFrame frame : this.frames) {
			for (Button<?> button : frame.getButtons()) {
				if (button.isShown()) action.accept(button);
			}
		}
	}

	@Override
	public void render(MatrixStack matrices, int mousex, int mousey, float delta) {
		this.forEachShown(component -> component.render(matrices));
	}
	
	@Override
	public boolean mouseClicked(double mousex, double mousey, int button) {
		for (CategoryFrame frame : frames) {
			if (ScreenHelper.clickCheck(mousex, mousey, frame)) {
				frame.mouseClicked(mousex, mousey, button);
				return super.mouseClicked(mousex, mousey, button);
			}
			if (frame.isOpen()) {
				for (Component component : frame.getButtons()) {
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
		return closeOnEsc;
	}

	@Override
	public void save() {
		try {
			int linenum = 0;
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.getFile())); 
			BufferedReader reader = new BufferedReader(new FileReader(this.getFile()));

			for (Category category : Category.values()) {
				String[] line = reader.readLine().split(" ");

				if (line[1].equals(category.getName())) {
					writer.write(category.getName() + " position: x" + frames.get(linenum).x() + ", y : " + frames.get(linenum).y());
				}
				linenum += 1;
			}
		}
		catch(IOException e) {}
	}
}
