package me.austin.queer.modules.hacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import org.lwjgl.glfw.GLFW;

import me.austin.queer.modules.Modules;
import me.austin.queer.modules.gui.clickgui.screens.ClickGuiScreen;
import me.austin.queer.modules.hacks.client.ClickGui;
import me.austin.queer.modules.hacks.client.RichPresence;
import me.austin.queer.modules.hacks.combat.KillAura;
import me.austin.queer.modules.hacks.movement.AntiKnockBack;
import me.austin.queer.modules.hacks.movement.NoSlow;
import me.austin.queer.modules.hacks.player.AutoTotem;
import me.austin.queer.util.Util;
import net.minecraft.network.Packet;

public class Hacks extends Modules<Hack> implements Util {
	private static Hacks INSTANCE;

	public Hacks() {

		// client
		this.get().add(new ClickGui());
		this.get().add(new RichPresence());

		// combat
		this.get().add(new KillAura());

		// movement
		this.get().add(new AntiKnockBack());
		this.get().add(new NoSlow());

		// player
		this.get().add(new AutoTotem());

		INSTANCE = this;
	}

	public static Hacks getInstance() {
		return INSTANCE;
	}

	public Hack getHackByClass(Class<? extends Hack> clazz) {
		for (Hack hack : this.get()) {
			if (hack.getClass() == clazz) {
				return hack;
			}
		} 
		return null;
	}
	
	public List<Hack> getHacksByCategory(Category c) {
		List<Hack> hackz = new ArrayList<>();
		
		for (Hack hack : this.get()) {
			if (hack.getCategory() == c) {
				hackz.add(hack);
			}
		}
        return hackz;
	}
	
	public void forEachEnabled(Consumer<Hack> action) {
		Objects.requireNonNull(action);
        for (Hack hack : this.get()) {
            action.accept(hack);
        }
	}

	public void onKeyPress(int key) {
		this.get().forEach(hack -> {
			if (key == GLFW.GLFW_KEY_ESCAPE && ClickGuiScreen.getInstance().shouldCloseOnEsc()) {
				mc.openScreen(null);
			}
			if (hack.getBind().get() == key) {	
				hack.toggle();
			}
		});
	}

	public void onTickUpdate() {
		forEachEnabled(Hack::onUpdate);
	}

	public void onPacketRecieve(Packet<?> packet) {
		forEachEnabled(hack -> hack.onPacketRecieve(packet));
	}

	@Override
    public void unload() {
		INSTANCE = null;
		super.unload();
    }
}
