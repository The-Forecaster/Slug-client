package me.austin.queer.mixin;

import static me.austin.queer.util.Globals.EVENTBUS;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.austin.queer.event.render.RenderEvent.Render3dEvent;
import me.austin.queer.event.render.RenderEvent.WorldRender;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void onRender(MatrixStack stack, CallbackInfo info) {
        Render3dEvent event = Render3dEvent.get(stack);

        EVENTBUS.post(event);

        if (event.isCancelled()) info.cancel();
    }

    @Inject(method = "renderWorld", at = @At("HEAD"), cancellable = true)
    private void onWorldRender(MatrixStack stack, CallbackInfo info) {
        WorldRender event = WorldRender.get(stack);

        EVENTBUS.post(event);

        if (event.isCancelled()) info.cancel();
    }

}
