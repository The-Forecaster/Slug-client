package me.austin.queer.module.hacks;

import java.util.List;

import me.austin.queer.module.Module;
import me.austin.queer.module.setting.Setting;
import me.austin.queer.module.setting.Settings;
import me.austin.queer.module.setting.settings.KeyBindSetting;
import me.austin.queer.util.Globals;
import net.minecraft.network.Packet;

public abstract class Hack extends Module implements Globals {
	public static Hack INSTANCE;
	private final KeyBindSetting bind;
	private boolean enabled;
	private final Category category;	
	public final Settings settings = new Settings();

	public Hack(String name, String description, int bind, Category category) {
		super(name, description);
		this.bind = new KeyBindSetting(bind);
		this.enabled = false;
		this.category = category;
		this.settings.add(this.bind);
	}
	
	public void onEnable() {
	}
	
	public void onDisable() {
	}

	public void onUpdate() {
	}

	public void onPacketRecieve(Packet packet) {
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
			this.onDisable();
		} else {
			this.onEnable();
		}
		this.enabled = !this.enabled;
	}
	
	public KeyBindSetting getBind() {
		return this.bind;
	}
	
	public void setBind(int bind) {
		this.bind.setValue(bind);
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public Category getCategory() {
		return this.category;
	}

	public List<Setting> getSettings() {
		return this.settings.getSettings();
	}
}
