package me.austin.queer.mixin;

import static me.austin.queer.Globals.EVENTBUS;
import static me.austin.queer.Globals.mc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.austin.queer.event.events.TickEvent.PostTick;
import me.austin.queer.event.events.TickEvent.PreTick;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.util.profiler.Profiler;

@Mixin(MinecraftClient.class)
public final class MinecraftClientMixin extends MinecraftClient {
    @Shadow
    private Profiler profiler;

    private MinecraftClientMixin(RunArgs args) {
        super(args);
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private final void beforeTick(CallbackInfo info) {
        var event = PreTick.get(mc.player != null && mc.world != null);

        EVENTBUS.post(event);
        if (event.isCancelled())
            info.cancel();
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;pop()V", ordinal = 0, shift = At.Shift.AFTER), cancellable = true)
    private final void onTick(CallbackInfo info) {
        var event = PostTick.get(mc.player != null && mc.world != null);

        EVENTBUS.post(event);
        if (event.isCancelled())
            info.cancel();
    }
}
