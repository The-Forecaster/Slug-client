package me.austin.queer;

import java.io.File;
import java.nio.file.Paths;

import me.austin.queer.manager.*;
import me.austin.queer.misc.Globals;
import net.fabricmc.api.ModInitializer;

public class TransRights implements ModInitializer, Globals {
	public static final String NAME = "Trans-Rights", VERSION = "v0.2";
	
	private static TransRights instance;
	private static File maindir;

	public TransRights() {
		maindir = Paths.get(mc.runDirectory.getPath(), NAME + "/").toFile();
		if (!maindir.exists()) maindir.mkdirs();
	}

	public static File getDir() {
		return maindir;
	}

	public static TransRights getInstance() {
		return instance;
	}

	public static void load() {
		Manager.loadManagers();
	}

	public static void unload() {
		// unload stuff here
	}

	private static Runnable save = () -> {
		// put save stuff here

		LOGGER.info(NAME + " has successfully saved.");
	};

	@Override
	public final void onInitialize() {
		var initStartTime = System.currentTimeMillis();

		Runtime.getRuntime().addShutdownHook(new Thread(save));
		load();

		// init stuff here
		LOGGER.info(NAME + " initialized in " + (System.currentTimeMillis() - initStartTime) + " ms.");
	}
}

