package me.austin.client.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import me.austin.client.util.ChatHelperKt;
import me.austin.client.api.command.CommandManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.text.Text;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Shadow
    @Final
    private MinecraftClient client;

    @Unique
    private boolean ignoreChatMessage;

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void onSendChatMessage(String message, CallbackInfo ci) {
        if (ignoreChatMessage)
            return;

        if (message.startsWith(String.valueOf(CommandManager.INSTANCE.getPrefix()))) {
            try {
                CommandManager.INSTANCE.dispatch(message.substring(1));
            } catch (CommandSyntaxException e) {
                client.inGameHud.getChatHud().addMessage(Text.of(ChatHelperKt.prefix + "§c" + message));
            }

            client.inGameHud.getChatHud().addToMessageHistory(message);
            ci.cancel();
        }
    }
}
