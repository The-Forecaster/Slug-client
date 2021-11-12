package me.austin.queer.modules.commands.hack;

import me.austin.queer.modules.commands.Command;
import me.austin.queer.modules.hacks.Hack;
import me.austin.queer.modules.hacks.Hacks;

@Command.Register(name = "set", description = "set a setting of a hack", aliases = {"set", "s"})
public class Set extends Command {
    public Set() {
        super(Set.class.getAnnotation(Register.class));
    }

    @Override
    public void execute(String[] args) {
        for (Hack hack : Hacks.getInstance().get()) {
            if (hack.getName().toLowerCase().equals(args[0])) {
                
            }
        }
    }
}
