package me.austin.queer.modules.hacks;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import me.austin.queer.TransRights;
import me.austin.queer.event.transrights.ToggleEvent;
import me.austin.queer.modules.Nameable;
import me.austin.queer.modules.setting.Setting;
import me.austin.queer.modules.setting.Settings;
import me.austin.queer.modules.setting.settings.KeyBindSetting;
import me.austin.queer.modules.setting.settings.ToggleSetting;
import me.austin.queer.util.Globals;
import net.minecraft.network.Packet;

public abstract class Hack extends Nameable implements Globals {
	private final Category category;
	private final Settings settings;
	private final KeyBindSetting bind = register(new KeyBindSetting(0));
	private final ToggleSetting enabled = register(new ToggleSetting("Enabled", "Controls whether the hack is enabled or not", false));

	protected Hack(Register info) {
		this(info.name(), info.description(), info.category());
	}

	public Hack(String name, String description, Category category) {
		super(name, description, new File(TransRights.getDir(), name));
		this.category = category;
		this.settings = new Settings(this);
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
		this.enabled.set(true);
		this.onEnable();
	}
	
	public void disable() {
		this.enabled.set(false);
		this.onEnable();
	}
	
	public final void toggle() {
		if (this.isEnabled()) {
			this.disable();
			EVENTBUS.register(this);
			EVENTBUS.post(ToggleEvent.get(this));
		} else {
			this.enable();
			EVENTBUS.unregister(this);
			EVENTBUS.post(ToggleEvent.get(this));
		}
	}
	
	public KeyBindSetting getBind() {
		return this.bind;
	}
	
	public void setBind(int bind) {
		this.bind.set(bind);
	}
	
	public boolean isEnabled() {
		return this.enabled.get();
	}
	
	public Category getCategory() {
		return this.category;
	}

	public Settings getSettings() {
		return this.settings;
	}

	protected <T extends Setting<?>> T register(T setting) {
        this.settings.add(setting);
        return setting;
    }
	
	/**
	 * @param name name of the hack
	 * @param description description of the hack
	 * @param category category of the client
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public static @interface Register {
		String name();
		String description();
		Category category();
	}
}
