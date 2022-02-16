package trans.rights.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import trans.rights.client.events.BlockSideDrawEvent;
import trans.rights.client.misc.api.EventObject;

@Mixin(Block.class)
public class BlockMixin implements EventObject {
    @Inject(method = "shouldDrawSide", at = @At("HEAD"))
    private final void shouldDrawSideModifier(BlockState state, BlockView world, BlockPos pos, Direction side, BlockPos blockPos, CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(this.getEventBus().dispatch(BlockSideDrawEvent.get(state.getBlock()).isCancelled()));
    }
}
