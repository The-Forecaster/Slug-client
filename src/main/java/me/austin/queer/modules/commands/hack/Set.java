package me.austin.queer.modules.commands.hack;

import me.austin.queer.modules.commands.Command;

@Command.Register(name = "set", description = "set a setting of a hack", aliases = {"set", "s"})
public class Set extends Command {
    public Set() {
        super(Set.class.getAnnotation(Register.class));
    }

    @Override
    public void execute(String[] args) {
        return;
    }
}
