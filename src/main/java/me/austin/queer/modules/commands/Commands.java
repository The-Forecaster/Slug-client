package me.austin.queer.modules.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import me.austin.queer.TransRights;
import me.austin.queer.modules.Manager;
import me.austin.queer.modules.commands.client.Prefix;
import me.austin.queer.modules.commands.client.Reload;
import me.austin.queer.modules.commands.hack.Set;
import me.austin.queer.util.chat.ChatHelper;

public class Commands extends Manager<Command> {
    private static Commands INSTANCE;
    private String prefix;

    public Commands() {
        this(".");
    }

    public Commands(String prefix) {
        super(new File(TransRights.getDir(), "Command-config"));

        this.prefix = prefix;

        this.add(new Prefix());
        this.add(new Reload());
        this.add(new Set());

        INSTANCE = this;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void onChatMessage(String message) {
        String[] args = message.split(" ");
        boolean commandFound = false;

        for (Command command : this.get()) {
            for (String alias : command.getAliases()) {
                if (args[0] == this.prefix + alias) {
                    if (command.execute(args.toString().substring(args[0].length()).split(" "))) commandFound = true;
                }
            }
        }

        if (!commandFound) ChatHelper.addErrorMessage("Incorrect Usage");
    }

    public static Commands getInstance() {
        return INSTANCE;
    }

    @Override
    public void init() {
        try (Writer writer = new FileWriter(TransRights.getDir())) {
            writer.write("prefix : " + this.prefix);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
