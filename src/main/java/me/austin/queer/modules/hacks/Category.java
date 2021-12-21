package me.austin.queer.modules.hacks;

import java.io.File;

import me.austin.queer.modules.INameable;

public enum Category implements INameable {
    CLIENT("Client", "For managing the client"),
    COMBAT("Combat", "For fighting other people"),
    MOVEMENT("Movement", "For movement hacks"),
    PLAYER("Player", "For managing the player"),
    RENDER("Render", "For modules that render stuff");
    
    private final String name, description;
    private File file;
    
    private Category(final String name, final String description) {
        this.name = name;
        this.description = description;
    }
    
    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final String getDescription() {
        return this.description;
    }

    @Override
    public final File getFile() {
        return this.file;
    }
}
