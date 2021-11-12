package me.austin.queer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.austin.queer.TransRights;
import me.austin.queer.util.Util;
import me.austin.queer.util.text.TextFormatting;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
	protected TitleScreenMixin(Text text) {
		super(text);
	}

	@Inject(method = "init()V", at = @At("HEAD"))
	private void init(MatrixStack stack, CallbackInfo info) {
		Util.mc.textRenderer.draw(stack, Text.of(TransRights.NAME), 0f, 0f, TextFormatting.AQUA.getColorIndex());
		super.init();
	}
}
