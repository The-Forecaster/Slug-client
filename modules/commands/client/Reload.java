package me.austin.queer.modules.commands.client;

import me.austin.queer.TransRights;
import me.austin.queer.modules.commands.Command;

@Command.Register(name = "Reload", description = "Reloads parts of the client and/or game", aliases = {"r", "rel"})
public class Reload extends Command {

    public Reload() {
        super(Reload.class.getAnnotation(Register.class));
    }

    @Override
    public void execute(String[] args) {
        String stage = args[0].toLowerCase();

        switch(stage) {
            case "full":
                mc.reloadResources();
                TransRights.getInstance().reload();
            
            case "client":
                TransRights.getInstance().reload();

            case "world":
                mc.reloadResources();
        }
    }
}
