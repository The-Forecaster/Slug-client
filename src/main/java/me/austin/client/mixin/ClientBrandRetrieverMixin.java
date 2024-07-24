package me.austin.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.austin.client.impl.hack.AntiFabric;
import net.minecraft.client.ClientBrandRetriever;

@Mixin(ClientBrandRetriever.class)
public class ClientBrandRetrieverMixin {
    @Inject(method = "getClientModName", at = @At("HEAD"), cancellable = true, remap = false)
    private static void clientModNameModifier(CallbackInfoReturnable<String> info) {
        if (AntiFabric.INSTANCE.isEnabled()) {
            info.setReturnValue("vanilla");
        }
    }
}
