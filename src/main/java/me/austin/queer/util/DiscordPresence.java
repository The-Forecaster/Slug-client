package me.austin.queer.util;

import club.minnced.discord.rpc.*;

public class DiscordPresence {
    public static DiscordRPC lib;
    public static String applicationId, steamId;

	public static void init(String[] args) {
        lib = DiscordRPC.INSTANCE; 
        lib.Discord_Register("Trans Rights", "do cool shit");
    }
}
