package me.austin.queer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.zero.alpine.bus.EventManager;
import net.minecraft.client.MinecraftClient;

/**
 * This interface contains the global variables for the client
 * @author Austin 
 */
public final class Globals {
	public static final String NAME = "Trans-Rights", VERSION = "v0.4";
	public static final MinecraftClient mc = MinecraftClient.getInstance();
	public static final Logger LOGGER = LogManager.getLogger(NAME);
	public static final EventManager EVENTBUS = new EventManager();

	private Globals() {}

	public static final MinecraftClient getMinecraft() {
		return MinecraftClient.getInstance();
	}

	public static final boolean nullCheck() {
		return mc.player == null && mc.world == null; 
	}
}
