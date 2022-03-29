package trans.rights.client.mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.util.telemetry.TelemetrySender;
import net.minecraft.command.CommandSource;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.s2c.play.CommandTreeS2CPacket;
import net.minecraft.server.command.ServerCommandSource;
import trans.rights.client.manager.impl.CommandManager;

/**
 *  this is (mostly) pasted from https://github.com/Earthcomputer/clientcommands
 *
 * Will improve in the future but this is how it is for now
 */
@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Shadow
    private CommandDispatcher<CommandSource> commandDispatcher;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(MinecraftClient mc, Screen screen, ClientConnection connection, GameProfile profile, TelemetrySender sender, CallbackInfo info) {
        CommandManager.registerCommands((CommandDispatcher<ServerCommandSource>) (Object) commandDispatcher);
    }

    @Inject(method = "onCommandTree", at = @At("TAIL"))
    private void onOnCommandTree(CommandTreeS2CPacket packet, CallbackInfo ci) {
        CommandManager.registerCommands((CommandDispatcher<ServerCommandSource>) (Object) commandDispatcher);
    }
}
