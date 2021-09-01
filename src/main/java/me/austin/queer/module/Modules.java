package me.austin.queer.module;

import java.util.ArrayList;
import java.util.List;

public abstract class Modules<T extends IModule> {
    public static List<Modules<? extends IModule>> managers = new ArrayList<>();

    public static <T extends IModule>IModule getModuleByClass(Class<T> clazz) {
        for (Modules<? extends IModule> manager : managers) {
            for (IModule module : manager.get()) {
                if (module.getClass() == clazz) {
                    return module;
                }
            }
        } 
        return null;
    }

    public abstract List<? extends IModule> get();
}
