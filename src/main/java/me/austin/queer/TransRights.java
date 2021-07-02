package me.austin.queer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.austin.queer.event.Events;
import me.austin.queer.module.hacks.Hacks;
import me.zero.alpine.bus.EventManager;
import me.zero.alpine.listener.EventHandler;
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

	@EventHandler
	public static Events EVENTS;
	public static TransRights INSTANCE;
	public static EventManager EVENTBUS;
	public static Hacks HACKS;
	public static Logger LOGGER;

	public static Object sync = new Object();
	public static void printLog(String message) {
		synchronized (sync) {
			LOGGER.info(message);
		}
	}
	
	public static void load() {
		EVENTS = new Events();
		INSTANCE = new TransRights();
		EVENTBUS = new EventManager();
		HACKS = new Hacks();
		LOGGER = LogManager.getLogger(modname);
		
		printLog(modname + "has been loaded");
	}

	public static void unload() {
		EVENTS = null;
		INSTANCE = null;
		EVENTBUS = null;
		HACKS = null;
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
		if (INSTANCE == null) {
			INSTANCE = this;
			return;
		}

		load();
		EVENTBUS.subscribe(EVENTS);
	}
}
