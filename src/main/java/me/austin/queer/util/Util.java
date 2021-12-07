package me.austin.queer.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public abstract interface Util {
	public static final MinecraftClient mc = MinecraftClient.getInstance();
	public static final ClientPlayerEntity player = mc.player;
	
	public static boolean nullCheck() {
		return mc.world == null;
	}

	public static boolean fullNullCheck() {
        return mc != null && mc.world != null && mc.player != null;
    }
}
