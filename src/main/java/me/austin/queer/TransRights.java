package me.austin.queer;

import com.google.common.eventbus.EventBus;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.austin.queer.event.Events;
import me.austin.queer.event.world.TickEvent;
import me.austin.queer.modules.Modules;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.AutoInvokingEvent;

public class TransRights implements ModInitializer {
	public static final String NAME = "Trans Rights";
	public static final String VERSION = "0.0.1";
	public static final Logger LOGGER = LogManager.getLogger(NAME);

	public static EventBus EVENTBUS;
	public static Events EVENTS;
	private static TransRights INSTANCE;
	private static final Object sync = new Object();

	public TransRights() {
		INSTANCE = this;
	}
	
	public static final void printLog(String message) {
		synchronized(sync) {
			LOGGER.log(Level.INFO, message);
		}
	}

	public static TransRights getInstance() {
		return INSTANCE;
	}

	public void load() {
		EVENTBUS = new EventBus();
		EVENTS = new Events();
		Modules.loadManagers();

		printLog(NAME + " has been loaded");
	}

	public void unload() {
		EVENTBUS = null;
		EVENTS = null;
		Modules.unloadManagers();
		
		printLog(NAME + " has been unloaded");
	}
	
	public void reload() {
		unload();
		load();

		printLog(NAME + " has been reloaded");
	}

	@AutoInvokingEvent
	public void onTickEvent(TickEvent event) {

	}
	
	@Override
	public void onInitialize() {
		final long initStartTime = System.currentTimeMillis();
		INSTANCE = this;
		this.load();
		printLog(NAME + " initialized in " + (System.currentTimeMillis() - initStartTime) + " ms.");
	}
}
