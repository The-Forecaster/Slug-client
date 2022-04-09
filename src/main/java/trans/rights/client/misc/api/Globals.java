package trans.rights.client.misc.api;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

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

    default ClientPlayerEntity getPlayer() {
        return mc.player;
    }
}
