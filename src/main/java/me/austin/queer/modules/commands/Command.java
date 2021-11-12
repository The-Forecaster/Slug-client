package me.austin.queer.modules.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;

import me.austin.queer.modules.Modulus;
import me.austin.queer.util.Util;
import me.austin.queer.util.text.TextFormatting;

public abstract class Command extends Modulus implements Util {
    private final List<String> aliases;
    public static final String ARROW = "" + TextFormatting.GRAY + TextFormatting.BOLD + " ➜ ", USAGE = "" + TextFormatting.GRAY + TextFormatting.BOLD + "Usage: ";

    public Command(Register info) {
        super(info.name(), info.description());
        
        this.aliases = Arrays.asList(info.aliases());
    }
    
    public abstract void execute(String[] args);

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
	protected static @interface Register {
		String name();
		String description();
		String[] aliases() default {};
	}
}
