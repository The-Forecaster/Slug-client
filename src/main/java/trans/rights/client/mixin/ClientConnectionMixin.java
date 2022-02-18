package trans.rights.client.mixin;

import java.io.IOException;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;
import trans.rights.client.events.PacketEvent;
import trans.rights.client.events.PacketEvent.*;
import trans.rights.event.bus.impl.BasicEventManager;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
    @Inject(method = "handlePacket", at = @At("HEAD"), cancellable = true)
    private void beforeRead(Packet<?> packet, PacketListener listener, CallbackInfo info) {
        this.postCancel(PreReceive.get(packet), info);
    }

    @Inject(method = "handlePacket", at = @At("TAIL"), cancellable = true)
    private void afterRead(Packet<?> packet, PacketListener listener, CallbackInfo info) {
        this.postCancel(PostReceive.get(packet), info);
    }

    @Inject(method = "send", at = @At("HEAD"), cancellable = true)
    private void beforeSend(Packet<?> packet, CallbackInfo info) {
        this.postCancel(PreSend.get(packet), info);
    }

    @Inject(at = @At("TAIL"), method = "send", cancellable = true)
    private void afterSend(Packet<?> packet, CallbackInfo info) {
        this.postCancel(PostSend.get(packet), info);
    }

    @Inject(method = "exceptionCaught", at = @At("HEAD"), cancellable = true)
    private void onExceptionCaught(ChannelHandlerContext context, Throwable throwable, CallbackInfo info) {
        if (throwable instanceof IOException)
            info.cancel();
    }

    private void postCancel(PacketEvent event, CallbackInfo info) {
        if (BasicEventManager.INSTANCE.dispatch(event).isCancelled())
            info.cancel();
    }
}
