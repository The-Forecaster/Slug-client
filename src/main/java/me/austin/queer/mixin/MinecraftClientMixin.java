package me.austin.queer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javassist.tools.Callback;
import me.austin.queer.events.*;
import me.austin.queer.misc.Globals;
import me.austin.queer.TransRights;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.profiler.Profiler;

// If ur using vscode, this file is gonna produce errors, don't worry about it
// All of this works in a build environment and in the application
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin implements Globals{

    @Shadow private Profiler profiler;

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void beforeTick(CallbackInfo info) {
        var event = new TickEvent.Pre(mc.player != null && mc.world != null);

        EVENTBUS.post(event);
        if (event.isCancelled()) info.cancel();
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;pop()V", ordinal = 0, shift = At.Shift.AFTER), cancellable = true)
    private void onTick(CallbackInfo info) {
        profiler.push(TransRights.NAME + "tick");
        profiler.pop();

        var event = new TickEvent.Post(mc.player != null && mc.world != null);

        EVENTBUS.post(event);
        if (event.isCancelled()) info.cancel();
    }
}
