package trans.rights.client.events;

import net.minecraft.block.Block;
import trans.rights.event.commons.Cancellable;

public final class BlockSideDrawEvent extends Cancellable {
    private static final BlockSideDrawEvent INSTANCE = new BlockSideDrawEvent();

    private Block block;

    public static BlockSideDrawEvent get(Block block) {
        INSTANCE.block = block;
        INSTANCE.setCancelled(false);

        return INSTANCE;
    }

    public Block getBlock() {
        return this.block;
    }
}
