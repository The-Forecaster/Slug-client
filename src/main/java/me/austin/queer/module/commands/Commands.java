package me.austin.queer.module.commands;

import java.util.ArrayList;
import java.util.List;

import me.austin.queer.module.IModule;
import me.austin.queer.module.Modules;

public class Commands extends Modules<Command> {
    public static final List<Command> COMMANDS = new ArrayList<>();
    public static String prefix;

    public Commands() {
        this(".");
    }

    public Commands(String prefix) {
        Commands.prefix = prefix;
    }

    public static List<Command> getCommands() {
        return COMMANDS;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static void setPrefix(String prefix) {
        Commands.prefix = prefix;
    }

    @Override
    public List<? extends IModule> get() {
        return getCommands();
    }
}
