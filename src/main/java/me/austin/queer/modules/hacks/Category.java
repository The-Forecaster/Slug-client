package me.austin.queer.modules.hacks;

import me.austin.queer.modules.IModulus;

public enum Category implements IModulus {
    CLIENT("Client", "For managing the client"),
    COMBAT("Combat", "For fighting other people"),
    MOVEMENT("Movement", "For movement hacks"),
    PLAYER("Player", "For managing the player");
    
    private final String name, description;
    
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
}
