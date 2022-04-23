package trans.rights.client.events;

import net.minecraft.block.Block;
import trans.rights.event.type.Cancellable;

import javax.annotation.ParametersAreNonnullByDefault;

public final class BlockSideDrawEvent extends Cancellable {
    private final Block block;

    @ParametersAreNonnullByDefault
    public BlockSideDrawEvent(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return this.block;
    }
}
