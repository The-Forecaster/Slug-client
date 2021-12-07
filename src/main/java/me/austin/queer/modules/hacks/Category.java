package me.austin.queer.modules.hacks;

import java.io.File;

import me.austin.queer.modules.INameable;

public enum Category implements INameable {
    CLIENT("Client", "For managing the client"),
    COMBAT("Combat", "For fighting other people"),
    MOVEMENT("Movement", "For movement hacks"),
    PLAYER("Player", "For managing the player");
    
    private final String name, description;
    private File file;
    
    private Category(final String name, final String description) {
        this.name = name;
        this.description = description;
    }
    
    public final String getName() {
        return this.name;
    }

    public final String getDescription() {
        return this.description;
    }

    public final File getFile() {
        return this.file;
    }
}
