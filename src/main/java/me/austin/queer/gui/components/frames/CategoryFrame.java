package me.austin.queer.gui.components.frames;

import java.io.FileWriter;
import java.util.List;

import me.austin.queer.gui.ClientScreen;
import me.austin.queer.gui.components.Button;
import me.austin.queer.gui.components.Frame;
import me.austin.queer.gui.components.buttons.HackButton;
import me.austin.queer.modules.hacks.Category;
import me.austin.queer.modules.hacks.Hack;
import me.austin.queer.modules.hacks.Hacks;
import me.austin.queer.util.client.ColorHelper;
import me.austin.queer.util.render.ScreenHelper;
import net.minecraft.client.util.math.MatrixStack;

public class CategoryFrame extends Frame {
    private final Category category;

    public CategoryFrame(int x, int y, int width, int height, ClientScreen parent, Category category) {
        super(x, y, width, height, parent);

        this.category = category;

        int offset = 10;
        for (Hack hack : Hacks.getInstance().getHacksByCategory(category)) {
            this.buttons.add(new HackButton(this.x, this.y + offset, this.width, 20, hack));
        }
        offset += 20;
    }

    @Override
    public void render(MatrixStack stack) {
        ScreenHelper.drawComponent(stack, ColorHelper.backGroundLow, this);
    }

    @Override
    public String getText() {
        return category.getName();
    }

    @Override
    protected List<Button<?>> initButtons() {
        int offset = 10;
        for (Hack hack : Hacks.getInstance().getHacksByCategory(category)) {
            this.buttons.add(new HackButton(this.x, this.y + offset, this.width, 20, hack));
        }
        offset += 20;
        return this.buttons;
    }

    @Override
    public void save() {
        try (FileWriter writer = new FileWriter(this.file)) {
            writer.write(this.category.getName() + " position");
            writer.write("x" + this.x);
            writer.write("y" + this.y);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
