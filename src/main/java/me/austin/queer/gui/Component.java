package me.austin.queer.gui;

import me.austin.queer.util.Util;
import net.minecraft.client.util.math.MatrixStack;

public abstract class Component implements Util {
    protected int x, y;
    protected int width, height;
    protected boolean shown;

    public Component(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    } 

    public abstract void render(MatrixStack stack);
    public abstract void mouseClicked(double mousex, double mousey, int button);
    public abstract String getText();

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isShown() {
        return this.shown;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }
}
