package trans.rights.client.misc.api;

import net.minecraft.client.MinecraftClient;

/**
 * This class contains the global variables for the client
 * 
 * @author Austin
 */
public interface Globals {
    MinecraftClient mc = MinecraftClient.getInstance();

    default boolean nullCheck() {
        return mc.player == null && mc.world == null;
    }

    default MinecraftClient getMinecraft() {
        return MinecraftClient.getInstance();
    }
}
