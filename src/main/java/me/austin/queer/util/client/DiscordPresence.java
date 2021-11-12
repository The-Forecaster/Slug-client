package me.austin.queer.util.client;

import club.minnced.discord.rpc.*;
import me.austin.queer.TransRights;
import me.austin.queer.util.text.TextFormatting;

public interface DiscordPresence {
    static final DiscordRichPresence richPresence = new DiscordRichPresence();
    static final DiscordRPC rpc = DiscordRPC.INSTANCE;

    public static void start() {
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        rpc.Discord_Initialize("835240968533049424", handlers, true, null);

        richPresence.startTimestamp = System.currentTimeMillis() / 1000l;

        richPresence.largeImageKey = TransRights.NAME;
        richPresence.largeImageText = TransRights.NAME + "!";
        richPresence.details = TransRights.VERSION + TextFormatting.ITALIC;
    }

    public static void stop() {
        rpc.Discord_Shutdown();
        rpc.Discord_ClearPresence();
    }
}