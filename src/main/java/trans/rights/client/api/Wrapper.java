package trans.rights.client.api;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

/**
 * This class contains the global variables for the client
 * 
 * @author Austin
 */
public interface Wrapper {
    default boolean nullCheck() {
        return MinecraftClient.getInstance().player == null && MinecraftClient.getInstance().world == null;
    }

    default MinecraftClient getMinecraft() {
        return MinecraftClient.getInstance();
    }

    default ClientPlayerEntity getPlayer() {
        return MinecraftClient.getInstance().player;
    }
}
