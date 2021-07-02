package me.austin.queer.event.events;

import me.austin.queer.event.Event;
import net.minecraft.client.util.math.MatrixStack;

public class Render2dEvent extends Event {
    MatrixStack stack;

    public Render2dEvent(MatrixStack stack) {
        this.stack = stack;
    }
}
