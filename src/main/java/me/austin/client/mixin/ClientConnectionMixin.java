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
import net.minecraft.network.packet.Packet;
import net.minecraft.network.listener.PacketListener;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
    @Inject(method = "handlePacket", at = @At("TAIL"), cancellable = true)
    private static void afterRead(final Packet<?> packet, final PacketListener listener, final CallbackInfo info) {
        if (BasicEventManager.INSTANCE.post(new PacketEvent.PostReceive(packet)).isCancelled()) {
            info.cancel();
        }
    }

    @Inject(method = "send", at = @At("HEAD"), cancellable = true)
    private void beforeSend(final Packet<?> packet, final CallbackInfo info) {
        if (BasicEventManager.INSTANCE.post(new PacketEvent.PreSend(packet)).isCancelled()) {
            info.cancel();
        }
    }

    @Inject(method = "exceptionCaught", at = @At("HEAD"), cancellable = true)
    private void onExceptionCaught(final ChannelHandlerContext context, final Throwable throwable, final CallbackInfo info) {
        if (throwable instanceof IOException && AntiKick.INSTANCE.isEnabled()) {
            info.cancel();
        }
    }
}
