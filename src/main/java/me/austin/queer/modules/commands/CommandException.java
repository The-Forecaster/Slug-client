package me.austin.queer.modules.commands;

import me.austin.queer.util.chat.ChatHelper;
import net.minecraft.util.Formatting;

public class CommandException extends RuntimeException {
    private String message;
    private Type type;

    public CommandException(String message, Type type) {
        this.message = message;
        this.type = type;
    }

    public void addtoChat() {
        if (this.type == Type.USAGE) {
            ChatHelper.addErrorMessage(Formatting.BOLD + "Incorrect usage " + Command.ARROW + " " + this.message);
        }
        else if (this.type == Type.NOTFOUND) {
            ChatHelper.addErrorMessage("Command not found " + Command.ARROW + " " + this.message);
        }
        else {
            ChatHelper.addErrorMessage(this.message);
        }
    }
 
    public static enum Type {
        USAGE,
        NOTFOUND,
        OTHER;
    }
}
