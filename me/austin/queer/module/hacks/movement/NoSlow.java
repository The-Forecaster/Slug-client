package me.austin.queer.module.hacks.movement;

import me.austin.queer.module.hacks.Category;
import me.austin.queer.module.hacks.Hack;
import me.austin.queer.module.setting.settings.KeyBindSetting;

public class NoSlow extends Hack {
    public NoSlow() {
        super("No Slow", "Prevents things from slowing you down", KeyBindSetting.NONE, Category.MOVEMENT);
    }

    @Override
    public final void onUpdate() {
        if (mc.player.getMovementSpeed() > 1f) {
            mc.player.setMovementSpeed(1f);
        }
    }
}
