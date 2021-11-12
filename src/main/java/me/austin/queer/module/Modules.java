package me.austin.queer.modules;

import java.util.ArrayList;
import java.util.List;

import me.austin.queer.modules.commands.Commands;
import me.austin.queer.modules.gui.hud.Hud;
import me.austin.queer.modules.hacks.Hacks;
import me.austin.queer.modules.setting.Settings;

/**
 * This is meant to be the base for a manager class
 * @author Austin
 */
public abstract class Modules<T extends Modulus> {
    public static List<Modules<? extends Modulus>> managers = new ArrayList<>();
    private List<T> modules;

    protected Modules() {
        this.modules = new ArrayList<>();
    }

    public static void loadManagers() {
        managers.add(new Commands());
        managers.add(new Settings());
        managers.add(new Hacks());
        managers.add(new Hud());
    }

    public static Modulus getModuleByClass(Class<?> clazz) {
        for (Modules<? extends Modulus> manager : managers) {
            for (Modulus module : manager.get()) {
                if (module.getClass() == clazz) {
                    return module;
                }
            }
        } 
        return null;
    }
    
    public static void unloadManagers() {
        managers.forEach(m ->  {
            m.unload();
            m = null;
        });
    }

    public static Modulus getModuleByName(String name) {
        for (Modules<? extends Modulus> manager : managers) {
            for (Modulus module : manager.get()) {
                if (module.getName().toLowerCase().equals(name)) {
                    return module;
                }
            }
        }
        return null;
    }

    public static List<Modules<? extends Modulus>> getManagers() {
        return managers;
    }

    public List<T> get() {
        return this.modules;
    }

    public void add(T... values) {
        for (T t : values) {
            modules.add(t);
        }
    }

    public void unload() {
        this.modules.forEach(mod -> {
            mod = null;
        });

        this.modules = null;
    }
}
