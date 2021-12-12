package me.austin.queer.modules.commands.client;

import me.austin.queer.TransRights;
import me.austin.queer.modules.commands.Command;
import me.austin.queer.modules.commands.CommandException;

@Command.Register(name = "Reload", description = "Reloads parts of the client and/or game", aliases = {"r", "rel"})
public class Reload extends Command {

    public Reload() {
        super(Reload.class.getAnnotation(Register.class));
    }

    @Override
    public void execute(String[] args) {
        String stage = args[0].toLowerCase();
        
        if (args.length > 1) throw new CommandException(this.getName() + " <type>", CommandException.Type.USAGE);
        switch(stage) {
            case "full":
                mc.reloadResources();
                TransRights.reload();

            case "client":
                TransRights.reload();

            case "world":
                mc.reloadResources();
        } 
    }
}
