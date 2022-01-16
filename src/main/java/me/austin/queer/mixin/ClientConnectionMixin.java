package me.austin.queer.mixin;

import static me.austin.queer.Globals.EVENTBUS;

import java.io.IOException;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.netty.channel.ChannelHandlerContext;
import me.austin.queer.event.events.PacketEvent.*;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;

@Mixin(ClientConnection.class)
public final class ClientConnectionMixin {
    @Inject(method = "handlePacket", at = @At("HEAD"), cancellable = true)
    private final void beforeRead(Packet<?> packet, PacketListener listener, CallbackInfo info) {
        final var event = PreReceive.get(packet);

        EVENTBUS.post(event);
        if (event.isCancelled())
            info.cancel();
    }

    @Inject(method = "handlePacket", at = @At("TAIL"), cancellable = true)
    private final void afterRead(Packet<?> packet, PacketListener listener, CallbackInfo info) {
        final var event = PostReceive.get(packet);

        EVENTBUS.post(event);
        if (event.isCancelled())
            info.cancel();
    }

    @Inject(at = @At("HEAD"), method = "send", cancellable = true)
    private final void beforeSend(Packet<?> packet, CallbackInfo info) {
        final var event = PreSend.get(packet);

        EVENTBUS.post(event);
        if (event.isCancelled())
            info.cancel();
    }

    @Inject(at = @At("TAIL"), method = "send", cancellable = true)
    private final void afterSend(Packet<?> packet, CallbackInfo info) {
        final var event = PostSend.get(packet);

        EVENTBUS.post(event);
        if (event.isCancelled())
            info.cancel();
    }

    @Inject(method = "exceptionCaught", at = @At("HEAD"), cancellable = true)
    private final void onExceptionCaught(ChannelHandlerContext context, Throwable throwable, CallbackInfo info) {
        if (throwable instanceof IOException)
            info.cancel();
    }
}
