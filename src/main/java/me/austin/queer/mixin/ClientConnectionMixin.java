package me.austin.queer.mixin;

import java.io.IOException;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.netty.channel.ChannelHandlerContext;
import me.austin.queer.misc.Globals;
import me.austin.queer.events.*;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.Packet;


// If ur using vscode, this file is gonna produce errors, don't worry about it
// All of this works in a build environment and in the application
@Mixin(ClientConnection.class)
public class ClientConnectionMixin implements Globals {
    @Shadow @Final private NetworkSide side;

    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    private void onChannelRead(ChannelHandlerContext context, Packet<?> packet, CallbackInfo info) {
        if (this.side != NetworkSide.CLIENTBOUND) return;

        var event = new PacketEvent.Recieve(packet);

        EVENTBUS.post(event);
        if (event.isCancelled()) info.cancel();
    }

    @Inject(method = "sendImmediately", at = @At("HEAD"), cancellable = true)
    private void beforeSend(Packet<?> packet, CallbackInfo info) {
        if (this.side != NetworkSide.CLIENTBOUND) return;

        var event = new PacketEvent.Send(packet);

        EVENTBUS.post(event);
        if (event.isCancelled()) info.cancel();
    }

    @Inject(method = "exceptionCaught", at = @At("HEAD"), cancellable = true)
    private void onExceptionCaught(ChannelHandlerContext context, Throwable throwable, CallbackInfo info) {
        if (throwable instanceof IOException) info.cancel();
    }
}
