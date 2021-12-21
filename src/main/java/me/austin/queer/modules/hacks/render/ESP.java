package me.austin.queer.modules.hacks.render;

import me.austin.queer.modules.hacks.Category;
import me.austin.queer.modules.hacks.Hack;
import me.austin.queer.modules.setting.settings.ToggleSetting;

@Hack.Register(name = "ESP", description = "Lets you see other players.", category = Category.RENDER)
public class ESP extends Hack {
    public final ToggleSetting playerGlow = register(new ToggleSetting("Player-Glow", "Toggles glow mode", true));

    public ESP() {
        super(ESP.class.getAnnotation(Register.class));
    }

    
}
