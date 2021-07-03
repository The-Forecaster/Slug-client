package me.austin.queer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.austin.queer.event.Events;
import me.austin.queer.module.hacks.Hacks;
import meteordevelopment.orbit.EventBus;
import net.fabricmc.api.ModInitializer;

/*
	TODO: make save / load system
	TODO: learn json
	TODO: add getType() to Setting
	TODO: add ISerializable to module
	TODO: revert to alpine for event base
	TODO: add more events and fix event system
	TODO: revamp the main module
	TODO: add queue and flow of items
	TODO: make more hacks
	TODO: add command system
	TODO: calm down this is for fun
	TODO: change the icon.png in assets
*/

public class TransRights implements ModInitializer {
	
	public static final String modname = "Trans Rights";
	public static final String modid = "transrights";
	public static final String version = "b0.0.1";

	private static Hacks hacks;
	private static Events events;
	public static EventBus EVENTBUS;
	public static Logger LOGGER;

	public static final Object sync = new Object();
	public static void printLog(String message) {
		synchronized(sync) {
			LOGGER.info(message);
		}
	}
	
	public static void load() {
		hacks = new Hacks();
		events = new Events(hacks);
		EVENTBUS = new EventBus();
		LOGGER = LogManager.getLogger(modname);
		
		printLog(modname + "has been loaded");
	}

	public static void unload() {
		hacks = null;
		events = null;
		EVENTBUS = null;
		LOGGER = null;

		printLog(modname + "has been unloaded");
	}
	
	public static void reload() {
		unload();
		load();

		printLog(modname + "has been reloaded");
	}
	
	@Override
	public void onInitialize() {
		long initStartTime = System.currentTimeMillis();

		load();

		printLog(modname + " initialized in " + (System.currentTimeMillis() - initStartTime) + " ms.");
	}
}
