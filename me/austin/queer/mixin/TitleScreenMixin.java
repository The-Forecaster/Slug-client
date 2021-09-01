package me.austin.queer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.austin.queer.util.Util;
import net.minecraft.client.gui.screen.TitleScreen;

@Mixin(TitleScreen.class)
public class TitleScreenMixin implements Util {
	@Shadow
	@Inject(at = @At("HEAD"), method = "render")
	private void onRender(CallbackInfo info) {
	}
}
