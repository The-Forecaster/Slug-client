package me.austin.queer.feature;

import me.zero.alpine.listener.Listenable;;

public abstract class Feature implements Listenable {
    private final String name;
    
    protected Feature(String name) {
        this.name = name;
    }

    public final String getName() {
        return this.name;
    }
}
