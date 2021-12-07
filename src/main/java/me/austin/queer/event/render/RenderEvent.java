package me.austin.queer.event.render;

import me.austin.queer.event.Event;
import net.minecraft.client.util.math.MatrixStack;

public abstract class RenderEvent extends Event {
    protected MatrixStack stack;

    public MatrixStack getStack() {
        return this.stack;
    }

    public static class DrawOverlayEvent extends RenderEvent {
        private static DrawOverlayEvent INSTANCE = new DrawOverlayEvent();

        public static DrawOverlayEvent get(MatrixStack stack) {
            if (INSTANCE == null) {
				INSTANCE = new DrawOverlayEvent();
			}
			else if (INSTANCE.isCancelled()) {
				INSTANCE = new DrawOverlayEvent();
			}
			INSTANCE.stack = stack;

			return INSTANCE;
        }
    }

    public static class Render2dEvent extends RenderEvent {
        private static Render2dEvent INSTANCE = new Render2dEvent();

        public static Render2dEvent get(MatrixStack stack) {
            if (INSTANCE == null) {
				INSTANCE = new Render2dEvent();
			}
			else if (INSTANCE.isCancelled()) {
				INSTANCE = new Render2dEvent();
			}
			INSTANCE.stack = stack;

			return INSTANCE;
        }
    }

    public static class Render3dEvent extends RenderEvent {
        private static Render3dEvent INSTANCE = new Render3dEvent();

        public static Render3dEvent get(MatrixStack stack) {
            if (INSTANCE == null) {
				INSTANCE = new Render3dEvent();
			}
			else if (INSTANCE.isCancelled()) {
				INSTANCE = new Render3dEvent();
			}
			INSTANCE.stack = stack;

			return INSTANCE;
        }
    }

    public static class WorldRender extends RenderEvent {
        private static Render3dEvent INSTANCE = new Render3dEvent();

        public static Render3dEvent get(MatrixStack stack) {
            if (INSTANCE == null) {
				INSTANCE = new Render3dEvent();
			}
			else if (INSTANCE.isCancelled()) {
				INSTANCE = new Render3dEvent();
			}
			INSTANCE.stack = stack;

			return INSTANCE;
        }
    }
}
