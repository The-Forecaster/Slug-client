package trans.rights.mixin;

import net.minecraft.client.ClientBrandRetriever;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trans.rights.client.impl.hack.AntiFabric;

@Mixin(ClientBrandRetriever.class)
public class ClientBrandRetrieverMixin {
    @Inject(method = "getClientModName", at = @At("HEAD"), cancellable = true, remap = false)
    private static void clientModNameModifier(CallbackInfoReturnable<String> info) {
        if (AntiFabric.INSTANCE.getEnabled()) info.setReturnValue("vanilla");
    }
}
