package me.austin.queer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.zero.alpine.bus.EventManager;
import net.minecraft.client.MinecraftClient;

/**
 * This interface contains the global variables for the client
 * @author Austin 
 */
public abstract interface Globals {
	String NAME = "Trans-Rights", VERSION = "v0.4";
	MinecraftClient mc = MinecraftClient.getInstance();
	Logger LOGGER = LogManager.getLogger(NAME);
	EventManager EVENTBUS = new EventManager();

	static MinecraftClient getMinecraft() {
		return MinecraftClient.getInstance();
	}

	static boolean nullCheck() {
		return mc.player == null && mc.world == null; 
	}
}
