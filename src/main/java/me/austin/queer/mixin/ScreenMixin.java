package me.austin.queer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.austin.queer.util.Util;
import net.minecraft.client.gui.screen.Screen;

@Mixin(Screen.class)
public abstract class ScreenMixin implements Util {
    @Inject(method = "renderBackground", at = @At("HEAD"))
    private void onRenderBackground(CallbackInfo info) {
        info.cancel();
    }
}