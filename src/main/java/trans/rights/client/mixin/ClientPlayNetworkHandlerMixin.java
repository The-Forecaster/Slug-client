package trans.rights.client.mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.server.command.ServerCommandSource;
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
import trans.rights.client.modules.command.CommandManager;

@Mixin(ClientPlayNetworkHandler.class)
public final class ClientPlayNetworkHandlerMixin {
    @Shadow
    private CommandDispatcher<CommandSource> commandDispatcher;

    @Inject(method = "<init>", at = @At("RETURN"))
    private final void onInit(MinecraftClient mc, Screen screen, ClientConnection connection, GameProfile profile, TelemetrySender sender, CallbackInfo info) {
        CommandManager.registerCommands((CommandDispatcher<ServerCommandSource>) (Object) commandDispatcher);
    }
}
