package me.austin.queer.module.hacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import org.lwjgl.glfw.GLFW;

import me.austin.queer.module.IModule;
import me.austin.queer.module.Modules;
import me.austin.queer.module.gui.clickgui.ClickGuiScreen;
import me.austin.queer.module.hacks.client.ClickGui;
import me.austin.queer.module.hacks.combat.KillAura;
import me.austin.queer.module.hacks.movement.NoSlow;
import me.austin.queer.util.Util;
import net.minecraft.network.Packet;

public class Hacks extends Modules<Hack> implements Util {
	public static List<Hack> HACKS;

	public Hacks() {
		HACKS = new ArrayList<>();

		// client
		HACKS.add(new ClickGui());

		// combat
		HACKS.add(new KillAura());

		// movement
		HACKS.add(new NoSlow());

		Modules.managers.add(this);
	}

    public static List<Hack> getHacks() {
		return HACKS;
	}

	public static Hack getHackByClass(Class<? extends Hack> clazz) {
		for (Hack hack : HACKS) {
			if (hack.getClass() == clazz) {
				return hack;
			}
		} 
		return null;
	}
	
	public static List<Hack> getHacksByCategory(Category c) {
		List<Hack> hackz = new ArrayList<>();
		
		for (Hack hack : HACKS) {
			if (hack.getCategory() == c) {
				hackz.add(hack);
			}
		}
        return hackz;
	}
	
	public static void forEachEnabled(Consumer<Hack> action) {
		Objects.requireNonNull(action);
        for (Hack hack : getHacks()) {
            action.accept(hack);
        }
	}

	public static void onKeyPress(int key) {
		HACKS.forEach(hack -> {
			if (key == GLFW.GLFW_KEY_ESCAPE && ClickGuiScreen.shouldCloseOnEsc) {
				mc.openScreen(null);
			}
			if (hack.getBind().get() == key) {	
				hack.toggle();
			}
		});
	}

	public static void onTickUpdate() {
		forEachEnabled(hack -> {
			hack.onUpdate();
		});
	}

	public static void onPacketRecieve(Packet<?> packet) {
		forEachEnabled(hack -> {
			hack.onPacketRecieve(packet);
		});
	}

	@Override
	public List<? extends IModule> get() {
		return getHacks();
	}
}
