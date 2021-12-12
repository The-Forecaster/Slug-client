package me.austin.queer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.austin.queer.TransRights;
import me.austin.queer.util.Globals;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

@Mixin(TitleScreen.class)
public class TitleScreenMixin implements Globals {
	@Inject(method = "render", at = @At("TAIL"))
	private void init(MatrixStack stack, CallbackInfo info) {
		mc.textRenderer.draw(stack, Text.of(TransRights.NAME), 0f, 0f, Formatting.AQUA.getColorIndex());
	}
}
