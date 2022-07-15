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
    @Inject(method = "sendChatMessage(Ljava/lang/String;Lnet/minecraft/text/Text;)V", at = @At("HEAD"))
    private void onSendChatMessage(String message, Text preview, CallbackInfo info) {
        if (message.startsWith(String.valueOf(CommandManager.INSTANCE.getPrefix()))) CommandManager.INSTANCE.dispatch(message);
    }
}
