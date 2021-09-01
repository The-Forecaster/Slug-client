package me.austin.queer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.austin.queer.event.Events;
import me.austin.queer.module.commands.Commands;
import me.austin.queer.module.hacks.Hacks;
import me.austin.queer.module.setting.Settings;
import meteordevelopment.orbit.EventBus;
import net.fabricmc.api.ModInitializer;

public class TransRights implements ModInitializer {
	public static final String modname = "Trans Rights";
	public static final String modid = "transrights";
	public static final String version = "0.0.1";

	public static Logger LOGGER = LogManager.getLogger(modname);
	public static EventBus EVENTBUS;
	public static Settings settings;
	public static Commands commands;
	public static Hacks hacks;
	public static Events events;
	public static TransRights INSTANCE;

	public TransRights() {
		INSTANCE = this;
		load();
	}

	private static final Object sync = new Object();
	
	public static void printLog(String message) {
		synchronized(sync) {
			LOGGER.log(Level.INFO, message);
		}
	}

	public static void load() {
		EVENTBUS = new EventBus();
		settings = new Settings();
		commands = new Commands();
		hacks = new Hacks();
		events = new Events();
		
		printLog(modname + " has been loaded");
	}

	public static void unload() {
		EVENTBUS = null;
		settings = null;
		commands = null;
		hacks = null;
		events = null;
		
		printLog(modname + " has been unloaded");
	}
	
	public static void reload() {
		unload();
		load();

		printLog(modname + " has been reloaded");
	}
	
	@Override
	public void onInitialize() {
		final long initStartTime = System.currentTimeMillis();
		load();
  
		printLog(modname + " initialized in " + (System.currentTimeMillis() - initStartTime) + " ms.");
	}
}
