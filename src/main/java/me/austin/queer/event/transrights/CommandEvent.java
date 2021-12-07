package me.austin.queer.event.transrights;

import me.austin.queer.event.Event;
import me.austin.queer.modules.commands.Command;

public class CommandEvent extends Event {
    private static CommandEvent INSTANCE = new CommandEvent();

    private Command command;

    public static CommandEvent get(Command command) {
        if (INSTANCE == null) {
            INSTANCE = new CommandEvent();
        }
        else if (INSTANCE.isCancelled()) {
            INSTANCE = new CommandEvent();
        }
        INSTANCE.command = command;

        return INSTANCE;
    }

    public Command getCommand() {
        return this.command;
    }
}
