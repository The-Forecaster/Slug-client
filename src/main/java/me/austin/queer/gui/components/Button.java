package me.austin.queer.gui.components;

import me.austin.queer.gui.Component;
import me.austin.queer.modules.Nameable;
import me.austin.queer.util.client.ColorHelper;
import me.austin.queer.util.render.ScreenHelper;
import net.minecraft.client.util.math.MatrixStack;

public abstract class Button<T extends Nameable> extends Component {
    protected T module;

    public Button(int x, int y, int width, int height, T module) {
        super(x, y, width, height);
        this.module = module;
    }

    @Override
    public void render(MatrixStack stack) {
        ScreenHelper.drawComponent(stack, ColorHelper.backGroundLow, this);
        ScreenHelper.drawString(stack, module.getName(), x, y,  ColorHelper.white, false);
    }

    @Override
    public String getText() {
        return this.module.getName();
    }
}
