package me.austin.queer.modules.hacks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import me.austin.queer.TransRights;
import me.austin.queer.modules.Manager;
import me.austin.queer.modules.hacks.client.ClickGui;
import me.austin.queer.modules.hacks.client.RichPresence;
import me.austin.queer.modules.hacks.combat.KillAura;
import me.austin.queer.modules.hacks.movement.AntiKnockBack;
import me.austin.queer.modules.hacks.movement.NoSlow;
import me.austin.queer.modules.hacks.player.AutoTotem;
import me.austin.queer.util.Globals;
import net.minecraft.network.Packet;

public class Hacks extends Manager<Hack> implements Globals {
	private static Hacks INSTANCE;

	public Hacks() {
		super(new File(TransRights.getDir().getAbsolutePath()));

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
			if (hack.getClass() == clazz) return hack;
		} 
		return null;
	}

	public Hack getHack(String name) {
		for (Hack hack : this.get()) {
			if (hack.getName().toLowerCase().equals(name.toLowerCase())) return hack;
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
		final Consumer<Hack> act = Objects.requireNonNull(action);
        for (Hack hack : this.get()) {
            act.accept(hack);
        }
	}

	public void onKeyPress(int key) {
		this.get().forEach(Hack::toggle);
	}

	public void onTickUpdate() {
		this.forEachEnabled(Hack::onUpdate);
	}

	public void onPacketRecieve(Packet<?> packet) {
		this.forEachEnabled(hack -> hack.onPacketRecieve(packet));
	}
	
	@Override
	public void init() {
		this.get().forEach(hack -> hack.getSettings().init());
		this.get().forEach(hack -> {
			if (mc.world != null && hack.isEnabled()) hack.onEnable();
		});
	}

	@Override
    public void unload() {
		INSTANCE = null;
		super.unload();
    }
}
