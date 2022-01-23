package trans.rights.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.zero.alpine.bus.EventManager;
import net.minecraft.client.MinecraftClient;

/**
 * This class contains the global variables for the client
 * @author Austin 
 */
public final class Globals {
    public static final MinecraftClient mc = MinecraftClient.getInstance();
    public static final Logger LOGGER = LogManager.getLogger();
    public static final EventManager EVENTBUS = new EventManager();

    private Globals() {}

    public static final boolean nullCheck() {
        return mc.player == null && mc.world == null; 
    }
}
