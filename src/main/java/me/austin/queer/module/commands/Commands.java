package me.austin.queer.modules.commands;

import me.austin.queer.modules.Modules;
import me.austin.queer.modules.commands.client.Prefix;
import me.austin.queer.modules.commands.client.Reload;
import me.austin.queer.modules.commands.hack.Set;
import me.austin.queer.util.text.TextFormatting;

public class Commands extends Modules<Command> {
    public static String ARROW = "" + TextFormatting.GRAY + TextFormatting.BOLD + " ➜ ", USAGE = "" + TextFormatting.GRAY + TextFormatting.BOLD + "Usage: ";
    private static Commands INSTANCE;
    private String prefix;

    public Commands() {
        this(".");
    }

    public Commands(String prefix) {
        this.prefix = prefix;

        this.add(new Prefix());
        this.add(new Reload());
        this.add(new Set());

        this.setInstance();
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void onChatMessage(String message) {
        String[] args = message.split(" ");

        for (Command command : this.get()) {
            for (String alias : command.getAliases()) {
                if (args[0] == this.prefix + alias) {
                    command.execute(args.toString().substring(args[0].length()).split(" "));
                }
            }
        }
    }

    public static Commands getInstance() {
        if (INSTANCE == null) INSTANCE = new Commands();

        return INSTANCE;
    }

    public void setInstance() {
        INSTANCE = this;
    }
}
