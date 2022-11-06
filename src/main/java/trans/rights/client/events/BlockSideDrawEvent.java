package trans.rights.client.events;

import net.minecraft.block.Block;
import org.jetbrains.annotations.NotNull;
import trans.rights.event.Cancellable;

public final class BlockSideDrawEvent extends Cancellable {
    private final Block block;

    public BlockSideDrawEvent(@NotNull Block block) {
        this.block = block;
    }

    public @NotNull Block getBlock() {
        return this.block;
    }
}
