package me.austin.queer.module.hacks;

import me.austin.queer.module.Module;
import me.austin.queer.module.setting.Setting;
import me.austin.queer.module.setting.Settings;
import me.austin.queer.module.setting.settings.KeyBindSetting;
import me.austin.queer.util.Util;
import net.minecraft.network.Packet;

public abstract class Hack extends Module implements Util {
	public static Hack INSTANCE;
	private boolean enabled = false;
	private final KeyBindSetting bind;
	private final Category category;

	public Hack(String name, String description, int bind, Category category) {
		super(name, description);
		this.bind = new KeyBindSetting(bind, this);
		this.category = category;
	}
	
	public void onEnable() {
	}
	
	public void onDisable() {
	}

	public void onUpdate() {
	}

	public void onPacketRecieve(Packet<?> packet) {
	}
	
	public void enable() {
		if (!this.enabled) {
			this.enabled = true;
			this.onEnable();
		}
	}
	
	public void disable() {
		if (this.enabled) {
			this.enabled = false;
			this.onDisable();
		}
	}
	
	public void toggle() {
		if (this.enabled) {
			this.disable();
		} else {
			this.onEnable();
		}
		this.enabled = !this.enabled;
	}
	
	public KeyBindSetting getBind() {
		return this.bind;
	}
	
	public void setBind(int bind) {
		this.bind.set(bind);
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public Category getCategory() {
		return this.category;
	}

	public static Setting<?> register(Setting<?> setting) {
		Settings.add(setting);
		return setting;
	}
}
