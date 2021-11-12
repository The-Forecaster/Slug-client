package me.austin.queer.modules.hacks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import me.austin.queer.modules.Modulus;
import me.austin.queer.modules.setting.Setting;
import me.austin.queer.modules.setting.Settings;
import me.austin.queer.modules.setting.settings.KeyBindSetting;
import me.austin.queer.util.Util;
import net.minecraft.network.Packet;

public abstract class Hack extends Modulus implements Util {
	private boolean enabled = false;
	private final KeyBindSetting bind = new KeyBindSetting(getClass().getAnnotation(Register.class).bind(), this);
	private final Category category = getClass().getAnnotation(Register.class).category();

	public Hack(Register info) {
		super(info.name(), info.description());
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
		this.enabled = true;
		this.onEnable();
	}
	
	public void disable() {
		this.enabled = true;
		this.onEnable();
	}
	
	public void toggle() {
		if (this.isEnabled()) {
			this.disable();
		} else {
			this.enable();	
		}
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

	protected static <T extends Setting<?>> T register(T setting) {
        Settings.getInstance().add(setting);
        return setting;
    }
	
	/**
	 * @param name name of the hack
	 * @param description description of the hack
	 * @param bind optional, sets the bind of the hack, default for this is no bind or 0
	 * @param category category of the client
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	protected static @interface Register {
		String name();
		String description();
		int bind() default KeyBindSetting.NONE;
		Category category();
	}
}
