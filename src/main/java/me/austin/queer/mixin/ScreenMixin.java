package me.austin.queer.mixin;

import java.awt.Color;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.austin.queer.util.Globals;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(Screen.class)
public abstract class ScreenMixin implements Globals {
    @Inject(method = "renderBackground", at = @At("HEAD"))
    private void onRenderBackground(CallbackInfo info) {
        mc.textRenderer.draw(new MatrixStack(), "Trans Rights yo", 20, 20, new Color(255, 255, 255, 255).getRGB());
    }
}