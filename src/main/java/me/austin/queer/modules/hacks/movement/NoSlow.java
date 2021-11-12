package me.austin.queer.modules.hacks.movement;

import me.austin.queer.modules.hacks.Category;
import me.austin.queer.modules.hacks.Hack;
import me.austin.queer.modules.setting.settings.KeyBindSetting;

@Hack.Register(name = "No-Slow", description = "Prevents things from slowing you down", bind = KeyBindSetting.NONE, category = Category.MOVEMENT)
public class NoSlow extends Hack {
    public NoSlow() {
        super(NoSlow.class.getAnnotation(Hack.Register.class));
    }

    @Override
    public void onUpdate() {
        if (mc.player.getMovementSpeed() > 1f) {
            mc.player.setMovementSpeed(1f);
        }
    }
}
