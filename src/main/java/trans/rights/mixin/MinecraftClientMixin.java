package trans.rights.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trans.rights.BasicEventManager;
import trans.rights.client.events.TickEvent;
import trans.rights.client.impl.friend.FriendManager;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow
    public ClientWorld world;

    @Shadow
    public ClientPlayerEntity player;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;pop()V", ordinal = 0, shift = At.Shift.AFTER))
    private void onTick(CallbackInfo info) {
        BasicEventManager.INSTANCE.dispatch(new TickEvent.Post(this.player != null && this.world != null));
    }

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setErrorCallback(Lorg/lwjgl/glfw/GLFWErrorCallbackI;)V", ordinal = 0, shift = At.Shift.AFTER))
    private void postInit(CallbackInfo info) {
        FriendManager.INSTANCE.add("stepho2003");
    }
}
