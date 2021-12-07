package me.austin.queer;

import java.io.File;

import com.google.common.eventbus.EventBus;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.austin.queer.event.Events;
import me.austin.queer.modules.Manager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class TransRights implements ModInitializer {
	public static final String NAME = "Trans-Rights", VERSION = "0.0.1";
	public static final Logger LOGGER = LogManager.getLogger(NAME);

	public static EventBus EVENTBUS;
	public static Events EVENTS;
	private static TransRights INSTANCE;
	private static File maindir;
	private static final Object sync = new Object();
	
	static {
		INSTANCE = new TransRights();
	}

	public static final void printLog(String message) {
		synchronized (sync) {
			LOGGER.log(Level.INFO, message);
		}
	}

	public static final TransRights getInstance() {
		return INSTANCE;
	}

	private static final void load() {
		EVENTBUS = new EventBus();
		EVENTS = new Events();
		maindir = new File(FabricLoader.getInstance().getGameDir().toFile(), NAME);
		maindir.mkdir();
		Manager.loadManagers();
		printLog(NAME + " has been loaded");
	}

	private static final void unload() {
		EVENTBUS = null;
		EVENTS = null;
		Manager.unloadManagers();
		printLog(NAME + " has been unloaded");
	}
	
	public static final void reload() {
		unload();
		load();
		printLog(NAME + " has been reloaded");
	}

	public static final File getDir() {
		return maindir;
	}
	
	@Override
	public final void onInitialize() {
		long initStartTime = System.currentTimeMillis();
		load();
		printLog(NAME + " initialized in " + (System.currentTimeMillis() - initStartTime) + " ms.");
	}
}

