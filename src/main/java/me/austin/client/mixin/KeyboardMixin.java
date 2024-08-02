package me.austin.client.mixin;

import me.austin.client.impl.events.KeyEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.austin.client.BasicEventManager;
import net.minecraft.client.Keyboard;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(method = "onKey", at = @At("HEAD"))
    private void onKey(final long window, final int key, final int scancode, final int action, final int modifiers, final CallbackInfo info) {
        BasicEventManager.INSTANCE.post(new KeyEvent(key));
    }
}
