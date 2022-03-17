package trans.rights.client.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.profiler.Profiler;
import trans.rights.client.events.TickEvent.PostTick;
import trans.rights.client.events.TickEvent.PreTick;
import trans.rights.event.bus.impl.BasicEventManager;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow
    private Profiler profiler;

    @Shadow
    private ClientWorld world;

    @Shadow
    private ClientPlayerEntity player;

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void beforeTick(CallbackInfo info) {
        BasicEventManager.INSTANCE.dispatch(PreTick.get(this.player != null && this.world != null));
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;pop()V", ordinal = 0, shift = At.Shift.AFTER), cancellable = true)
    private void onTick(CallbackInfo info) {
        BasicEventManager.INSTANCE.dispatch(PostTick.get(this.player != null && this.world != null));
    }
}
