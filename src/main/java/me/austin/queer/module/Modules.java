package me.austin.queer.module;

import java.util.ArrayList;
import java.util.List;

public class Modules<T extends IModule> {
    public List<T> modules;

    public Modules() {
        modules = new ArrayList<>();
    }
}
