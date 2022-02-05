package trans.rights.client.mixin;

import java.io.IOException;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;
import trans.rights.client.TransRights;
import trans.rights.client.events.PacketEvent.*;

@Mixin(ClientConnection.class)
public final class ClientConnectionMixin {
    @Inject(method = "handlePacket", at = @At("HEAD"), cancellable = true)
    private void beforeRead(Packet<?> packet, PacketListener listener, CallbackInfo info) {
        final var event = PreReceive.get(packet);

        TransRights.EVENTBUS.dispatch(event);
        if (event.isCancelled())
            info.cancel();
    }

    @Inject(method = "handlePacket", at = @At("TAIL"), cancellable = true)
    private void afterRead(Packet<?> packet, PacketListener listener, CallbackInfo info) {
        final var event = PostReceive.get(packet);

        TransRights.EVENTBUS.dispatch(event);
        if (event.isCancelled())
            info.cancel();
    }

    @Inject(method = "send", at = @At("HEAD"), cancellable = true)
    private void beforeSend(Packet<?> packet, CallbackInfo info) {
        final var event = PreSend.get(packet);

        TransRights.EVENTBUS.dispatch(event);
        if (event.isCancelled())
            info.cancel();
    }

    @Inject(at = @At("TAIL"), method = "send", cancellable = true)
    private void afterSend(Packet<?> packet, CallbackInfo info) {
        final var event = PostSend.get(packet);

        TransRights.EVENTBUS.dispatch(event);
        if (event.isCancelled())
            info.cancel();
    }

    @Inject(method = "exceptionCaught", at = @At("HEAD"), cancellable = true)
    private void onExceptionCaught(ChannelHandlerContext context, Throwable throwable, CallbackInfo info) {
        if (throwable instanceof IOException)
            info.cancel();
    }
}
