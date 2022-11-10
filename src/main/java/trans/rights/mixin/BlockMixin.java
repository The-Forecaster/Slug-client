package trans.rights.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trans.rights.BasicEventManager;
import trans.rights.client.events.BlockSideDrawEvent;
import trans.rights.client.impl.hack.WallHack;

/**
 * This modifies the Block class, used to xray with the {@link trans.rights.client.impl.hack.WallHack}
 */
@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "shouldDrawSide", at = @At("RETURN"), cancellable = true)
    private static void shouldDrawSideModifier(BlockState state, BlockView world, BlockPos pos, Direction side, BlockPos blockPos, CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(WallHack.INSTANCE.shouldRender(state.getBlock()));
    }
}
