package me.austin.client.mixin;

import java.io.IOException;

import me.austin.client.impl.events.PacketEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.netty.channel.ChannelHandlerContext;
import me.austin.client.BasicEventManager;
import me.austin.client.impl.hack.AntiKick;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
    @Inject(method = "handlePacket", at = @At("TAIL"), cancellable = true)
    private static void afterRead(Packet<?> packet, PacketListener listener, CallbackInfo info) {
        postCancel(new PacketEvent.PostReceive(packet), info);
    }

    private static void postCancel(PacketEvent event, CallbackInfo info) {
        if (BasicEventManager.INSTANCE.dispatch(event).isCancelled())
            info.cancel();
    }

    @Inject(method = "send", at = @At("HEAD"), cancellable = true)
    private void beforeSend(Packet<?> packet, CallbackInfo info) {
        postCancel(new PacketEvent.PreSend(packet), info);
    }

    @Inject(method = "exceptionCaught", at = @At("HEAD"), cancellable = true)
    private void onExceptionCaught(ChannelHandlerContext context, Throwable throwable, CallbackInfo info) {
        if (throwable instanceof IOException && AntiKick.INSTANCE.isEnabled())
            info.cancel();
    }
}
