package trans.rights.mixin;

import net.minecraft.client.WindowEventHandler;
import net.minecraft.util.thread.ReentrantThreadExecutor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import trans.rights.BasicEventManager;
import trans.rights.client.events.TickEvent.PostTick;
import trans.rights.client.events.TickEvent.PreTick;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin extends ReentrantThreadExecutor<Runnable> implements WindowEventHandler {
    @Shadow
    public ClientWorld world;

    @Shadow
    public ClientPlayerEntity player;

    protected MinecraftClientMixin(String string) {
        super(string);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void beforeTick(CallbackInfo info) {
        BasicEventManager.INSTANCE.dispatch(PreTick.get(this.player != null && this.world != null));
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;pop()V", ordinal = 0, shift = At.Shift.AFTER))
    private void onTick(CallbackInfo info) {
        BasicEventManager.INSTANCE.dispatch(PostTick.get(this.player != null && this.world != null));
    }
}
