package me.austin.queer.modules;

import static me.austin.queer.util.Globals.EVENTBUS;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.austin.queer.gui.ClientScreens;
import me.austin.queer.modules.commands.Commands;
import me.austin.queer.modules.hacks.Hacks;

/**
 * This is meant to be the base for a manager class
 * @author Austin
 */
public abstract class Manager<T extends INameable> {
    public static final List<Manager<? extends INameable>> managers = new ArrayList<>();

    private List<T> modules = new ArrayList<>();
    private final File file;

    protected Manager(File file) {
        this.file = file;
        EVENTBUS.register(this);
    }

    public abstract void init();

    public static void loadManagers() {
        managers.add(new Commands());
        managers.add(new Hacks());
        managers.add(new ClientScreens());
        // managers.add(new Hud());
    }

    public static void initManagers() {
        managers.forEach(Manager::init);
    }
    
    public static void unloadManagers() {
        managers.forEach(m -> {
            m.unload();
            m = null;
        });
    }

    public static INameable getModuleByClass(Class<?> clazz) {
        for (Manager<? extends INameable> manager : managers) {
            for (INameable module : manager.get()) {
                if (module.getClass().equals(clazz)) {
                    return module;
                }
            }
        }
        return null;
    }

    public static INameable getModuleByName(String name) {
        for (Manager<? extends INameable> manager : managers) {
            for (INameable module : manager.get()) {
                if (module.getName().toLowerCase().equals(name.toLowerCase())) {
                    return module;
                }
            }
        }
        return null;
    }

    public static List<Manager<? extends INameable>> getManagers() {
        return managers;
    }

    public List<T> get() {
        return this.modules;
    }

    public File getFile() {
        return this.file;
    }

    public void add(T value) {
        modules.add(value);
    }

    public void unload() {
        this.modules.forEach(mod -> mod = null);
        this.modules = null;
    }
}
