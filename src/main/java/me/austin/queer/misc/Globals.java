package me.austin.queer.misc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.austin.queer.TransRights;
import me.zero.alpine.bus.EventManager;
import net.minecraft.client.MinecraftClient;

/**
 * This interface contains the global variables for the client
 * @author Austin 
 */
public interface Globals {
	MinecraftClient mc = MinecraftClient.getInstance();
	Logger LOGGER = LogManager.getLogger(TransRights.NAME);
	EventManager EVENTBUS = new EventManager();

	default MinecraftClient getMinecraft() {
		return MinecraftClient.getInstance();
	}
}
