package me.austin.queer.modules.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;

import me.austin.queer.modules.Nameable;
import me.austin.queer.util.Util;
import net.minecraft.util.Formatting;

public abstract class Command extends Nameable implements Util {
    private final List<String> aliases;
    public static final String ARROW = "" + Formatting.GRAY + Formatting.BOLD + " ➜ ", USAGE = "" + Formatting.GRAY + Formatting.BOLD + "Usage: ";

    public Command(Register info) {
        this(info.name(), info.description(), info.aliases());
    }

    public Command(String name, String description, String[] aliases) {
        super(name, description, null);
        this.aliases = Arrays.asList(aliases);
    }
    
    public abstract boolean execute(String[] args);

    public List<String> getAliases() {
        return aliases;
    }

    /**
     * This is for registering the class of a command 
     * @param name name of the command, duh
     * @param description you get the point
     * @param aliases different tags for the command, like h instead of help
     */
    @Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public static @interface Register {
		String name();
		String description();
        String usage() default "prefix <insert symbol>";
		String[] aliases() default {};
	}
}
