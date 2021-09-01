package me.austin.queer.module.setting;

import java.util.ArrayList;
import java.util.List;

import me.austin.queer.module.*;
import me.austin.queer.module.hacks.Hack;

public class Settings extends Modules<Setting<?>> {
    public static final List<Setting<?>> SETTINGS = new ArrayList<>();
    public static final Settings INSTANCE = new Settings();

    public Settings() {
        Modules.managers.add(this);
    }

    public static List<Setting<?>> getSettingsFromHack(Hack hack) {
        List<Setting<?>> settings = new ArrayList<>();

        for (Setting<?> setting : settings) {
            if (hack == setting.parent) {
                settings.add(setting);
            }
        }
        return settings;
    }
    
    public static List<Setting<?>> getSettings() {
        return SETTINGS;
    }

    public static void add(Setting<?>... settings) {
        for (Setting<?> setting : settings) {
            SETTINGS.add(setting);
        }
    }

    @Override
    public List<? extends IModule> get() {
        return getSettings();
    }    
}
