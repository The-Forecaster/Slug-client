package me.austin.queer.module.hacks;

import java.util.ArrayList;
import java.util.List;

import me.austin.queer.module.Modules;
import me.austin.queer.module.hacks.client.*;
import me.austin.queer.module.hacks.combat.*;
import net.minecraft.network.Packet;

public class Hacks extends Modules<Hack> {
    public Hacks() {
        modules = new ArrayList<Hack>();
		
		// client
		this.modules.add(ClickGui.INSTANCE == null ? new ClickGui(this) : ClickGui.INSTANCE);

		// combat
		this.modules.add(KillAura.INSTANCE == null ? new KillAura() : ClickGui.INSTANCE);
    }

    public List<Hack> getHacks() {
		return this.modules;
	}
	
	public List<Hack> getHacksByCategory(Category c) {
		List<Hack> hackz = new ArrayList<>();
		
		for (Hack hack : modules) {
			if (hack.getCategory() == c) {
				hackz.add(hack);
			}
		} 
        return hackz;
	}

	public void onKeyPress(int key) {
		this.modules.forEach(hack -> {
			if (hack.getBind().getValue() == key) {
				hack.toggle();
			}
		});
	}

	public void onTickUpdate() {
		this.modules.forEach(hack -> {
			if (hack.isEnabled()) {
				hack.onUpdate();
			}
		});
	}

	public void onPacketRecieve(Packet<?> packet) {
		this.modules.forEach(hack -> hack.onPacketRecieve(packet));
	}
}
