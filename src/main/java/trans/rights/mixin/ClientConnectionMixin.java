package trans.rights.mixin;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trans.rights.client.events.PacketEvent;
import trans.rights.client.events.PacketEvent.PostReceive;
import trans.rights.client.events.PacketEvent.PostSend;
import trans.rights.client.events.PacketEvent.PreReceive;
import trans.rights.client.events.PacketEvent.PreSend;
import trans.rights.event.bus.impl.BasicEventManager;

import java.io.IOException;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
    @Inject(method = "handlePacket", at = @At("HEAD"), cancellable = true)
    private static void beforeRead(Packet<?> packet, PacketListener listener, CallbackInfo info) {
        postCancel(PreReceive.get(packet), info);
    }

    @Inject(method = "handlePacket", at = @At("TAIL"), cancellable = true)
    private static void afterRead(Packet<?> packet, PacketListener listener, CallbackInfo info) {
        postCancel(PostReceive.get(packet), info);
    }

    private static void postCancel(PacketEvent event, CallbackInfo info) {
        if (BasicEventManager.INSTANCE.dispatch(event).getCancelled()) info.cancel();
    }

    @Inject(method = "send*", at = @At("HEAD"), cancellable = true)
    private void beforeSend(Packet<?> packet, CallbackInfo info) {
        postCancel(PreSend.get(packet), info);
    }

    @Inject(at = @At("TAIL"), method = "send*", cancellable = true)
    private void afterSend(Packet<?> packet, CallbackInfo info) {
        postCancel(PostSend.get(packet), info);
    }

    @Inject(method = "exceptionCaught", at = @At("HEAD"), cancellable = true)
    private void onExceptionCaught(ChannelHandlerContext context, Throwable throwable, CallbackInfo info) {
        if (throwable instanceof IOException)
            info.cancel();
    }
}
