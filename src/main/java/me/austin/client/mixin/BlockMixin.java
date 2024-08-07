package me.austin.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.austin.client.impl.hack.WallHack;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

/**
 * This modifies the Block class, used to xray with
 * {@link me.austin.client.impl.hack.WallHack}
 */
@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "shouldDrawSide", at = @At("RETURN"), cancellable = true)
    private static void shouldDrawSideModifier(final BlockState state, final BlockView world, final BlockPos pos, final Direction side, final BlockPos blockPos, final CallbackInfoReturnable<Boolean> info) {
        if (WallHack.INSTANCE.isEnabled()) {
            info.setReturnValue(WallHack.INSTANCE.shouldRender(state.getBlock()));
        }
    }
}
