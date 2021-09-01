package me.austin.queer.event.render;

public class WorldRenderEvent {
    public final float partialTicks;

	public WorldRenderEvent(float partialTicks) {
		this.partialTicks = partialTicks;
	}
}
