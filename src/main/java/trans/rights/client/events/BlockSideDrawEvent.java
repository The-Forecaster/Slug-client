package trans.rights.client.events;

import net.minecraft.block.Block;
import trans.rights.event.commons.Cancellable;

public final class BlockSideDrawEvent extends Cancellable {
    private final Block block;

    public BlockSideDrawEvent(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return this.block;
    }
}
