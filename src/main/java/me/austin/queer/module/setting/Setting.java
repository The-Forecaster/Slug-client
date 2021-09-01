package me.austin.queer.module.setting;

import me.austin.queer.TransRights;
import me.austin.queer.event.client.SettingChangeEvent;
import me.austin.queer.module.IModule;
import me.austin.queer.module.Module;
import me.austin.queer.module.Modules;

public abstract class Setting<T> extends Module {
	public T currentValue;
	public IModule parent;

	public Setting(String name, String description, T defaultValue, IModule parent) {
		this(name, description, defaultValue, parent.getClass());
	}

	public Setting(String name, String description, T defaultValue, Class<? extends IModule> parent) {
		super(name, description);
		this.currentValue = defaultValue;
		this.parent = Modules.getModuleByClass(parent);
		Settings.add(this);
	}
	
	public T get() {
		return this.currentValue;
	}

	public void set(T value) {
		this.currentValue = value;
		TransRights.EVENTBUS.post(new SettingChangeEvent(this));
	}
}
