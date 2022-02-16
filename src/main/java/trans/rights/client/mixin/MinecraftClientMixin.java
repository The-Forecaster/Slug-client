package trans.rights.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.profiler.Profiler;
import trans.rights.client.events.TickEvent.PostTick;
import trans.rights.client.events.TickEvent.PreTick;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin implements EventObject {
    @Shadow
    private Profiler profiler;

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void beforeTick(CallbackInfo info) {
        this.getEventBus().dispatch(PreTick.get(mc.player != null && mc.world != null));
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;pop()V", ordinal = 0, shift = At.Shift.AFTER), cancellable = true)
    private void onTick(CallbackInfo info) {
        this.getEventBus().dispatch(PostTick.get(mc.player != null && mc.world != null));
    }
}
