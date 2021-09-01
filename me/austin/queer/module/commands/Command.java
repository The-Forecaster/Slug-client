package me.austin.queer.module.commands;

import java.util.function.Consumer;

import me.austin.queer.TransRights;
import me.austin.queer.event.client.CommandEvent;
import me.austin.queer.module.Module;

public abstract class Command extends Module {
    public Command(String name, String description, Consumer<Command> action) {
        super(name, description);
        action.accept(this);
        TransRights.EVENTBUS.post(new CommandEvent(this));
    }

    public abstract Consumer<?> execute();
}
