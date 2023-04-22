package me.austin.client.api;

import net.minecraft.client.MinecraftClient;

/**
 * This class contains the global variables for the client
 *
 * @author Austin
 */
public interface Wrapper {
    default MinecraftClient getMinecraft() {
        return MinecraftClient.getInstance();
    }
}
