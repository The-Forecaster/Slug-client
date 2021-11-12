package me.austin.queer.modules.hacks.client;

import me.austin.queer.modules.hacks.Category;
import me.austin.queer.modules.hacks.Hack;
import me.austin.queer.util.client.DiscordPresence;

@Hack.Register(name = "Rich-Presence", description = "Controls the Rich presense for discord", category = Category.CLIENT)
public class RichPresence extends Hack {
    public RichPresence() {
        super(RichPresence.class.getAnnotation(Register.class));
    }

    @Override
    public void onEnable() {
        DiscordPresence.start();
    }

    @Override
    public void onDisable() {
        DiscordPresence.stop();
    }
}
