package me.austin.queer.module.hacks;

import java.util.ArrayList;
import java.util.List;

import me.austin.queer.module.hacks.client.*;
import me.austin.queer.module.hacks.combat.*;
import me.zero.alpine.listener.Listenable;
import net.minecraft.network.Packet;

public class Hacks {
    public static List<Hack> hacks;

    public Hacks() {
        hacks = new ArrayList<>();
		
		// client
		hacks.add(ClickGui.INSTANCE == null ? new ClickGui() : ClickGui.INSTANCE);

		// combat
		hacks.add(KillAura.INSTANCE == null ? new KillAura() : ClickGui.INSTANCE);
    }

    public static List<Hack> getHacks() {
		return hacks;
	}
	
	public static List<Hack> getHacksByCategory(Category c) {
		List<Hack> hackz = new ArrayList<>();
		
		for (Hack hack : hacks) {
			if (hack.getCategory() == c) {
				hackz.add(hack);
			}
		} 
        return hackz;
	}

	public void onKeyPress(int key) {
		hacks.forEach(hack -> {
			if (hack.getBind().getValue() == key) {
				hack.toggle();
			}
		});
	}

	public void onTickUpdate() {
		hacks.forEach(hack -> {
			if (hack.isEnabled()) {
				hack.onUpdate();
			}
		});
	}

	public void onPacketRecieve(Packet packet) {
		hacks.forEach(hack -> hack.onPacketRecieve(packet));
	}
}
