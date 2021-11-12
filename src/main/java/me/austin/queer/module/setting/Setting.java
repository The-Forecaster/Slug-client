package me.austin.queer.modules.setting;

import me.austin.queer.TransRights;
import me.austin.queer.event.transrights.SettingChangeEvent;
import me.austin.queer.modules.Modulus;
import me.austin.queer.modules.Modules;

public abstract class Setting<T> extends Modulus {
	protected T value;
	protected final Modulus parent;

	public Setting(String name, String description, T defaultValue, Modulus parent) {
		this(name, description, defaultValue, parent.getClass());
	}

	public Setting(String name, String description, T defaultValue, Class<? extends Modulus> parent) {
		super(name, description);
		this.value = defaultValue;
		this.parent = Modules.getModuleByClass(parent);
	}
	
	public T get() {
		return this.value;
	}

	public void set(T value) {
		this.value = value;
		TransRights.EVENTBUS.post(new SettingChangeEvent(this));
	}

	public void set(T value, boolean cancelEvent) {
		this.value = value;
	}

	public Modulus getParent() {
		return this.parent;
	}
}
