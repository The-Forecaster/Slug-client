package me.austin.queer.event.transrights;

import me.austin.queer.event.Event;
import me.austin.queer.modules.setting.Setting;

public class SettingChangeEvent extends Event {
    private static SettingChangeEvent INSTANCE = new SettingChangeEvent();

    private Setting<?> setting;

    public static SettingChangeEvent get(Setting<?> setting) {
        if (INSTANCE == null) {
            INSTANCE = new SettingChangeEvent();
        }
        else if (INSTANCE.isCancelled()) {
            INSTANCE = new SettingChangeEvent();
        }
        INSTANCE.setting = setting;
        
        return INSTANCE;
    }

    public Setting<?> getSetting() {
        return this.setting;
    }
}
