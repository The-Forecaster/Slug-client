package me.austin.queer.event.transrights;

import me.austin.queer.event.Event;
import me.austin.queer.modules.setting.Setting;

public class SettingChangeEvent extends Event {
    private final Setting<?> setting;

    public SettingChangeEvent(Setting<?> setting) {
        this.setting = setting;
    }

    public Setting<?> getSetting() {
        return this.setting;
    }
}
