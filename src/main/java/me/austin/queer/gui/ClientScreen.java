package me.austin.queer.gui;

import java.io.File;

import me.austin.queer.modules.INameable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public abstract class ClientScreen extends Screen implements INameable {
    private static final File dir = ClientScreens.INSTANCE.getFile();
    private File file;
    
    private final String name, description;

    public ClientScreen(Register info) {
        this(info.name(), info.description());
    }

    protected ClientScreen(String title, String description) {
        super(Text.of(title));
        this.name = title;
        this.description = description;
        this.file = new File(title, dir.getAbsolutePath());
    }

    public abstract void save();

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public File getFile() {
        return this.file;
    }

    public static @interface Register {
        String name();
        String description();
    }
}
