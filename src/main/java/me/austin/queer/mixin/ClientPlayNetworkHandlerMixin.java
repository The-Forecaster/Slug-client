package me.austin.queer.mixin;

import static me.austin.queer.Globals.EVENTBUS;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.austin.queer.event.events.PacketEvent.PostReceive;
import me.austin.queer.event.events.PacketEvent.PreReceive;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.util.telemetry.TelemetrySender;
import net.minecraft.command.CommandSource;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;

@Mixin(ClientPlayNetworkHandler.class)
public final class ClientPlayNetworkHandlerMixin {
    @Shadow
    private CommandDispatcher<CommandSource> commandDispatcher;

    @Inject(method = "<init>", at = @At("RETURN"))
    private final void onInit(MinecraftClient mc, Screen screen, ClientConnection connection, GameProfile profile, TelemetrySender sender, CallbackInfo info) {
        // CommandManager.registerCommands((CommandDispatcher<ServerCommandSource>) (Object) commandDispatcher);
    }

    @Inject(method = "onExplosion", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/NetworkThreadUtils;forceMainThread(Lnet/minecraft/network/Packet;Lnet/minecraft/network/listener/PacketListener;Lnet/minecraft/util/thread/ThreadExecutor;)V", shift = At.Shift.BEFORE))
    private final void beforeExplosion(ExplosionS2CPacket packet, CallbackInfo info) {
        final var event = PreReceive.get(packet);

        EVENTBUS.post(event);
        if (event.isCancelled())
            info.cancel();
    }

    @Inject(method = "onExplosion", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/NetworkThreadUtils;forceMainThread(Lnet/minecraft/network/Packet;Lnet/minecraft/network/listener/PacketListener;Lnet/minecraft/util/thread/ThreadExecutor;)V", shift = At.Shift.AFTER))
    private final void afterExplosion(ExplosionS2CPacket packet, CallbackInfo info) {
        final var event = PostReceive.get(packet);

        EVENTBUS.post(event);
        if (event.isCancelled())
            info.cancel();
    }
}
