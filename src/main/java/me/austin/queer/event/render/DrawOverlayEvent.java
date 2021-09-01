package me.austin.queer.event.render;

import me.austin.queer.event.Event;
import net.minecraft.client.util.math.MatrixStack;

public class DrawOverlayEvent extends Event {
    private final MatrixStack stack;

	public DrawOverlayEvent(MatrixStack stack) {
		this.stack = stack;
	}

    public MatrixStack getStack() {
        return this.stack;
    }
}
