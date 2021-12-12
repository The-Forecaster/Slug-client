package me.austin.queer.modules.setting;

import static me.austin.queer.util.Globals.EVENTBUS;

import me.austin.queer.event.transrights.SettingChangeEvent;
import me.austin.queer.modules.Nameable;

public abstract class Setting<T> extends Nameable {
	protected T value;

	public Setting(String name, String description, T defaultValue) {
		super(name, description, null);
		this.value = defaultValue;
	}
	
	public T get() {
		return this.value;
	}

	public void set(T value) {
		this.set(value);
		EVENTBUS.post(SettingChangeEvent.get(this));
	}

	public void set(T value, boolean cancelEvent) {
		this.value = value;
	}

	public Class<?> getType() {
		return this.value.getClass();
	}
}
