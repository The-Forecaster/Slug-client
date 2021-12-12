package me.austin.queer.util;

import com.google.common.eventbus.EventBus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.austin.queer.TransRights;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public interface Globals {
	MinecraftClient mc = MinecraftClient.getInstance();
	ClientPlayerEntity player = mc.player;
	EventBus EVENTBUS = new EventBus();
	Logger LOGGER = LogManager.getLogger(TransRights.NAME);
	
	static boolean nullCheck() {
		return mc.world == null;
	}

	static boolean fullNullCheck() {
        return mc != null && mc.world != null && mc.player != null;
    }
}
