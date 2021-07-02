package me.austin.queer.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.austin.queer.util.Globals;

@Mixin(TitleScreen.class)
public class TitleScreenMixin implements Globals {
	@Inject(at = @At("HEAD"), method = "render")
	private void onRender(CallbackInfo info) {
		
	}
}
