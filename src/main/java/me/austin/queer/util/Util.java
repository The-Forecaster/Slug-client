package me.austin.queer.util;

import java.util.Random;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;

public abstract interface Util {
	public static final MinecraftClient mc = MinecraftClient.getInstance();
	public static final Keyboard keyboad = new Keyboard(mc);
	public static final Random rand = new Random();
	
	public static boolean nullCheck() {
		return mc.world == null;
	}

	public static boolean fullNullCheck() {
		return mc.world == null || mc.player == null;
	}
}
