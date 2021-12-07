package me.austin.queer.util.client;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import me.austin.queer.TransRights;
import net.minecraft.util.Formatting;

public abstract interface DiscordPresence {
    DiscordRichPresence richPresence = new DiscordRichPresence();
    DiscordRPC rpc = DiscordRPC.INSTANCE;

    static void start() {
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        rpc.Discord_Initialize("835240968533049424", handlers, true, null);

        richPresence.startTimestamp = System.currentTimeMillis() / 1000l;

        richPresence.largeImageKey = TransRights.NAME;
        richPresence.largeImageText = TransRights.NAME + "!";
        richPresence.details = TransRights.VERSION + Formatting.ITALIC;
        richPresence.state = "Gaming with the power of " + TransRights.NAME + "!";
    }

    static void stop() {
        rpc.Discord_Shutdown();
        rpc.Discord_ClearPresence();
    }
}