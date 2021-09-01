package me.austin.queer.event.client;

import me.austin.queer.event.Event;
import me.austin.queer.module.commands.Command;

public class CommandEvent extends Event {
    private final Command command;

    public CommandEvent(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return this.command;
    }
}
