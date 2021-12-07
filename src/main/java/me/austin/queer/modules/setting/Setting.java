package me.austin.queer.modules.setting;

import java.io.File;

import me.austin.queer.TransRights;
import me.austin.queer.event.transrights.SettingChangeEvent;
import me.austin.queer.modules.Nameable;
import me.austin.queer.modules.INameable;
import me.austin.queer.modules.Manager;

public abstract class Setting<T> extends Nameable {
	protected T value;
	protected final INameable parent;

	public Setting(String name, String description, T defaultValue, INameable parent) {
		this(name, description, defaultValue, parent.getClass());
	}

	public Setting(String name, String description, T defaultValue, Class<? extends INameable> parent) {
		super(name, description, Manager.getModuleByClass(parent).getFile());
		this.value = defaultValue;
		this.parent = Manager.getModuleByClass(parent);
	}
	
	public T get() {
		return this.value;
	}

	public void set(T value) {
		this.set(value);
		TransRights.EVENTBUS.post(SettingChangeEvent.get(this));
	}

	public void set(T value, boolean cancelEvent) {
		this.value = value;
	}

	public INameable getParent() {
		return this.parent;
	}

	public Class<?> getType() {
		return this.value.getClass();
	}

	@Override
	public File getFile() {
		return parent.getFile();
	}
}
