package me.austin.queer.util.client;

import club.minnced.discord.rpc.*;
import me.austin.queer.TransRights;

public interface DiscordPresence {
    static final DiscordRichPresence rpc = new DiscordRichPresence();
    static final DiscordRPC instance = DiscordRPC.INSTANCE;

    public static void init() {
        rpc.details = "im fucking gaming";
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        instance.Discord_Initialize("835240968533049424", handlers, true, null);
        instance.Discord_Register(TransRights.modid, "initialize");
        instance.Discord_UpdatePresence(rpc);
    }
}