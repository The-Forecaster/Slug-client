package me.austin.queer.mixin;

import static me.austin.queer.util.Globals.EVENTBUS;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.austin.queer.TransRights;
import me.austin.queer.event.world.TickEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Formatting;
import net.minecraft.util.profiler.Profiler;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow public abstract Profiler getProfiler();

    @Shadow protected abstract void doItemUse();

    @Inject(method = "tick", at = @At("TAIL"), cancellable = true)
    private void onTick(CallbackInfo info) {
        EVENTBUS.post(TickEvent.Post.get());

        if (TickEvent.Post.get().isCancelled()) info.cancel();
    }

    @Inject(method = "getWindowTitle", at = @At("TAIL"), cancellable = true)
    private void getWindowTitle(CallbackInfoReturnable<String> info) {
        info.setReturnValue(Formatting.BOLD + TransRights.NAME + " : " + Formatting.BOLD + TransRights.VERSION);
    }
}
