package me.austin.queer.util.render;

import com.mojang.blaze3d.systems.RenderSystem;

public class Renderer {
	public static void setup() {
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.disableTexture();
	}

	public static void cleanup() {
		RenderSystem.disableBlend();
		RenderSystem.enableTexture();
	}
}
