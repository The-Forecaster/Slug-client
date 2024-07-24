package me.austin.client.mixin;

import me.austin.client.Slug;
import org.spongepowered.asm.mixin.Mixin;
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
    @Unique
    private final MinecraftClient client = MinecraftClient.getInstance();

    @Unique
    private boolean ignoreChatMessage;

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void onSendChatMessage(final String message, final CallbackInfo ci) {
        if (ignoreChatMessage) {
            return;
        }

        if (message.startsWith(String.valueOf(CommandManager.INSTANCE.getPrefix()))) {
            Slug.Companion.getLOGGER().info("Detected prefix: command is {}", message);

            try {
                CommandManager.INSTANCE.dispatch(message.substring(1, message.length() - 1));
                Slug.Companion.getLOGGER().info("Dispatched");
            } catch (final CommandSyntaxException e) {
                client.inGameHud.getChatHud().addMessage(Text.of(ChatHelperKt.prefix + "§c" + message));
                Slug.Companion.getLOGGER().info("Errored");
            }

            client.inGameHud.getChatHud().addToMessageHistory(message);
            ci.cancel();
        }
    }
}
