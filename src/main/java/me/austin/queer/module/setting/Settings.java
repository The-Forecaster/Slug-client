package me.austin.queer.modules.setting;

import java.util.ArrayList;
import java.util.List;

import me.austin.queer.modules.Modulus;
import me.austin.queer.modules.Modules;
import me.austin.queer.modules.hacks.Hack;

public class Settings extends Modules<Setting<?>> {
    protected static Settings INSTANCE;

    public Settings() {
        INSTANCE = this;
    }

    public List<Setting<?>> getSettingsFromHack(Hack hack) {
        List<Setting<?>> settings = new ArrayList<>();

        for (Setting<?> setting : this.get()) {
            if (hack == setting.getParent()) {
                settings.add(setting);
            }
        }
        return settings;
    }

    public List<Setting<?>> getSettingsFromModule(Modulus module) {
        List<Setting<?>> settings = new ArrayList<>();

        for (Setting<?> setting : this.get()) {
            if (module == setting.getParent()) {
                settings.add(setting);
            }
        }
        return settings;
    }
    
    public static Settings getInstance() {
        return INSTANCE;
    }
    
    @Override
    public void unload() {
        INSTANCE = null;
        super.unload();
    }
}
