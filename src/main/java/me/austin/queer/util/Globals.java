package me.austin.queer.util;

import java.util.Random;

import net.minecraft.client.MinecraftClient;

public interface Globals {
	static final MinecraftClient mc = MinecraftClient.getInstance();
	static final Random rand = new Random();
	
	static boolean nullCheck() {
		return mc.world == null;
	}

	static boolean fullNullCheck() {
		return mc.world == null || mc.player == null;
	}
}
