package me.austin.queer;

import static me.austin.queer.util.Globals.LOGGER;

import java.io.File;

import com.google.common.eventbus.EventBus;

import org.apache.logging.log4j.Level;

import me.austin.queer.modules.Manager;
import net.fabricmc.api.ModInitializer;

public class TransRights implements ModInitializer {
	public static final String NAME = "Trans-Rights", VERSION = "0.0.1";
	public static final EventBus EVENT_BUS = new EventBus();

	private static TransRights INSTANCE;
	private static File maindir;
	
	public TransRights() {
		INSTANCE = this;
	}

	public static void printLog(String message) {
		LOGGER.log(Level.INFO, message);
	}

	public static TransRights getInstance() {
		return INSTANCE;
	}

	private static void load() {
		maindir = new File(getInstance().toString(), NAME);
		maindir.mkdir();
		Manager.loadManagers();
		printLog(NAME + " has been loaded");
	}

	private static void unload() {
		Manager.unloadManagers();
		printLog(NAME + " has been unloaded");
	}
	
	public static void reload() {
		unload();
		load();
		printLog(NAME + " has been reloaded");
	}

	public static File getDir() {
		return maindir;
	}
	
	@Override
	public final void onInitialize() {
		var initStartTime = System.currentTimeMillis();
		load();
		printLog(NAME + " initialized in " + (System.currentTimeMillis() - initStartTime) + " ms.");
	}
}

