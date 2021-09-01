package me.austin.queer.module.hacks;

import me.austin.queer.module.IModule;

public enum Category implements IModule {
    CLIENT("Client", "For managing the client"),
    COMBAT("Combat", "For fighting other people"),
    MOVEMENT("Movement", "For movement hacks");
    
    private final String name, description;
    
    Category(final String name, final String description) {
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
}
