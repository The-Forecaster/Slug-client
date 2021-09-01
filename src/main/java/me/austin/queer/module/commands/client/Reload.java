package me.austin.queer.module.commands.client;

import java.util.function.Consumer;

import me.austin.queer.module.commands.Command;

public class Reload extends Command {
    public Reload(Stage stage) {
        super("Reload", "reloads different functions of the client", null);
    }

    public Reload() {
        this(Stage.CLIENT);
    }

    @Override
    public Consumer<Command> execute() {
        return execute();
    }

    public enum Stage {
        FULL,
        CLIENT,
        WORLD
    }
}
