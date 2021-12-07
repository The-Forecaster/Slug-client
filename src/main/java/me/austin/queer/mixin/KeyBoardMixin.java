package me.austin.queer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.austin.queer.TransRights;
import me.austin.queer.event.system.KeyEvent;
import net.minecraft.client.Keyboard;

@Mixin(Keyboard.class)
public class KeyBoardMixin {
    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
    private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo info) {
        if (key >= 0) {
            KeyEvent event = KeyEvent.get(key, scancode);
            TransRights.EVENTBUS.post(event);

            if (event.isCancelled()) info.cancel();
        }
    }
}
