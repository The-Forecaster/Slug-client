package me.austin.queer.gui.components;

import java.io.File;
import java.util.List;

import me.austin.queer.gui.ClientScreen;
import me.austin.queer.gui.Component;
import me.austin.queer.util.client.ColorHelper;
import me.austin.queer.util.render.ScreenHelper;
import net.minecraft.client.util.math.MatrixStack;

public abstract class Frame extends Component {
    protected List<Button<?>> buttons;

    private boolean dragging = false, open = true;
    protected File file;

    public Frame(int x, int y, int width, int height, ClientScreen parent) {
        super(x, y, width, height);
        this.buttons = this.initButtons();
        this.file = parent.getFile();
    }

    protected abstract List<Button<?>> initButtons();

    public abstract void save();

    public List<? extends Button<?>> getButtons() {
        return this.buttons;
    }

    public boolean isDragging() {
        return this.dragging;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    public void keyPressed(int keyCode, int scanCode, int modifiers) {
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    @Override
    public void render(MatrixStack stack) {
        ScreenHelper.drawComponent(stack, ColorHelper.backGroundLow, this);

        if (this.isOpen()) buttons.forEach(button -> button.render(stack));
    }

    @Override
    public void mouseClicked(double mousex, double mousey, int button) {
        if (mousex > this.x && mousex < this.x + this.width && mousey > this.y && mousey < this.y + 20) {
            if (button == 1) {
                this.dragging = true;
            }
            else if (button == 2) {
                this.open = !this.open;
            }
        }
        else {
            for (Button<?> butt : this.buttons) {
                if (ScreenHelper.clickCheck(mousex, mousey, butt)) {
                    butt.mouseClicked(mousex, mousey, button);
                }
            }
        }
    }
}
