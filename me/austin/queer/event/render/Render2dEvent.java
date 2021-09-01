package me.austin.queer.event.render;

import me.austin.queer.event.Event;
import net.minecraft.client.util.math.MatrixStack;

public class Render2dEvent extends Event {
    private final MatrixStack stack;

    public Render2dEvent(MatrixStack stack) {
        this.stack = stack;
    }

    public MatrixStack getStack() {
        return this.stack;
    }
}
