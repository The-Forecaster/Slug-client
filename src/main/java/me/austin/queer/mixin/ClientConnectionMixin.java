package me.austin.queer.mixin;

import java.io.IOException;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.netty.channel.ChannelHandlerContext;
import me.austin.queer.event.events.PacketEvent;
import me.austin.queer.misc.Globals;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;


// If ur using vscode, this file is gonna produce errors, don't worry about it
// All of this works in a build environment and in the application
@Mixin(ClientConnection.class)
public class ClientConnectionMixin implements Globals {

    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    private void beforeRead(ChannelHandlerContext channelHandlerContext, Packet<?> packet, CallbackInfo info) {
        var event = new PacketEvent.Recieve(packet);

        EVENTBUS.post(event);
        if (event.isCancelled()) info.cancel();
    }

    @Inject(method = "channelRead0", at = @At("TAIL"), cancellable = true)
    private void afterRead(ChannelHandlerContext channelHandlerContext, Packet<?> packet, CallbackInfo info) {
        var event = new PacketEvent.PostRecieve(packet);

        EVENTBUS.post(event);
        if (event.isCancelled()) info.cancel();
    }

    @Inject(at = @At("HEAD"), method = "send", cancellable = true)
    private void beforeSend(Packet<?> packet, CallbackInfo info) {;
        var event = new PacketEvent.Send(packet);

        EVENTBUS.post(event);
        if (event.isCancelled()) info.cancel();
    }

    @Inject(at = @At("TAIL"), method = "send", cancellable = true)
    private void afterSend(Packet<?> packet, CallbackInfo info) {
        var event = new PacketEvent.Send(packet);

        EVENTBUS.post(event);
        if (event.isCancelled()) info.cancel(); 
    }

    @Inject(method = "exceptionCaught", at = @At("HEAD"), cancellable = true)
    private void onExceptionCaught(ChannelHandlerContext context, Throwable throwable, CallbackInfo info) {
        if (throwable instanceof IOException) info.cancel();
    }
}
