package me.austin.queer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.austin.queer.Globals.*;
import me.austin.queer.event.events.TickEvent.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.profiler.Profiler;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow
    private Profiler profiler;

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private final void beforeTick(CallbackInfo info) {
        var event = PreTick.get(mc.player != null && mc.world != null);
        
        profiler.push(NAME);

        EVENTBUS.post(event);
        if (event.isCancelled())
            info.cancel();
        
        profiler.pop();
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;pop()V", ordinal = 0, shift = At.Shift.AFTER), cancellable = true)
    private final void onTick(CallbackInfo info) {
        var event = PostTick.get(mc.player != null && mc.world != null);

        EVENTBUS.post(event);
        if (event.isCancelled())
            info.cancel();
    }
}
