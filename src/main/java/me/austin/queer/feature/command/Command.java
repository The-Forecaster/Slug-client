package me.austin.queer.feature.command;

import com.mojang.brigadier.CommandDispatcher;

import me.austin.queer.feature.Feature;
import net.minecraft.server.command.ServerCommandSource;

public abstract class Command extends Feature {
    public final String syntax;

    public Command(String name, String syntax) {
        super(name);
        
        this.syntax = syntax;
    }

    public abstract void execute(String[] args);

    public abstract void register(CommandDispatcher<ServerCommandSource> dispatcher);
}
