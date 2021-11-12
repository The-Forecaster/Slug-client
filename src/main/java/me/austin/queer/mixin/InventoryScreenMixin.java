package me.austin.queer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.gui.screen.ingame.InventoryScreen;

@Mixin(InventoryScreen.class)
public interface InventoryScreenMixin {
    @Accessor
    int fps();
}
