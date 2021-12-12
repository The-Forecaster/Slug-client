package me.austin.queer.modules.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import com.google.common.eventbus.Subscribe;

import org.lwjgl.glfw.GLFW;

import me.austin.queer.TransRights;
import me.austin.queer.event.system.KeyEvent;
import me.austin.queer.mixin.accessors.AccessorMinecraftClient;
import me.austin.queer.modules.Manager;
import me.austin.queer.modules.commands.client.Prefix;
import me.austin.queer.modules.commands.client.Reload;
import me.austin.queer.modules.commands.hack.Set;
import me.austin.queer.util.Globals;

public class Commands extends Manager<Command> implements Globals {
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

        for (Command command : this.get()) {
            for (String alias : command.getAliases()) {
                if (args[0] == this.prefix + alias) {
                    try {
                        command.execute(args.toString().substring(args[0].length()).split(" "));
                    }
                    catch (CommandException e) {
                        e.addtoChat();
                    }
                }
            }
        }
    }

    public static Commands getInstance() {
        return INSTANCE;
    }

    @Override
    public void init() {
        try (Writer writer = new FileWriter(TransRights.getDir())) {
            writer.write("prefix : " + this.prefix);
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onKeyPress(KeyEvent event) {
        if (GLFW.glfwGetKeyName(event.getKey(), GLFW.glfwGetKeyScancode(event.getKey())).equals(prefix)) {
            ((AccessorMinecraftClient) mc).openChatScreen(mc.inGameHud.getChatHud().getMessageHistory().toString());
        }
    }
}
