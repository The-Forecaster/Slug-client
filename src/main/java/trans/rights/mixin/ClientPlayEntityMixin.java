package trans.rights.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trans.rights.client.api.command.CommandManager;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayEntityMixin {

    // This is pasted from meteor https://github.com/MeteorDevelopment/meteor-client
    // go check them out for a better implementation of a command system
    @Inject(method = "sendChatMessage(Ljava/lang/String;Lnet/minecraft/text/Text;)V", at = @At("HEAD"), cancellable = true)
    private void onSendChatMessage(String message, Text preview, CallbackInfo info) {
        if (message.startsWith(String.valueOf(CommandManager.INSTANCE.getPrefix()))) {
            CommandManager.INSTANCE.dispatch(message);
            info.cancel();
        }
    }
}
