package me.austin.queer.modules.commands.client;

import me.austin.queer.modules.commands.Command;
import me.austin.queer.modules.commands.Commands;
import me.austin.queer.util.chat.ChatHelper;

@Command.Register(name = "Prefix", description = "Set the prefix for the client.", aliases = {"prefix, p"})
public class Prefix extends Command {
    public Prefix() {
        super(Prefix.class.getAnnotation(Register.class));
    }

    @Override
    public void execute(String[] args) {
        if (args[1].length() == 1) {
            Commands.getInstance().setPrefix(args[1]);
            ChatHelper.addPreMessage("Changed the prefix to: " + args[1]);
        }
        else {
            ChatHelper.addErrorMessage("Only use 1 character when using this Command");
        }
    }
}
