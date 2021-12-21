package me.austin.queer.command;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.server.command.ServerCommandSource;

public interface Command {
    abstract void register(CommandDispatcher<ServerCommandSource> dispatcher);

    abstract void execute();

    abstract String getName();
}
