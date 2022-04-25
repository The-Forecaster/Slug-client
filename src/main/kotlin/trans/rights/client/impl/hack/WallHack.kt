package trans.rights.client.impl.hack

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import trans.rights.client.api.hack.Hack
import trans.rights.client.events.BlockSideDrawEvent
import trans.rights.event.listener.impl.EventHandler
import trans.rights.event.listener.impl.LambdaListener
import trans.rights.event.listener.impl.listener

object WallHack : Hack("Wallhack", "Makes blocks see through and highlights players") {
    private val blocks = mutableSetOf<Block>()

    @EventHandler
    val blockDrawListener: LambdaListener<BlockSideDrawEvent> = listener { event: BlockSideDrawEvent ->
        if (blocks.contains(event.block)) {
            event.cancel()
        }
    }

    init {
        blocks.add(Blocks.DIAMOND_ORE)
        blocks.add(Blocks.ANCIENT_DEBRIS)
        blocks.add(Blocks.ENDER_CHEST)
        blocks.add(Blocks.BEDROCK)
    }

    override fun onEnable() {
        if (nullCheck()) {
            disable()
            return
        }
        minecraft.worldRenderer.reload()
    }

    override fun onDisable() {
        if (!nullCheck()) {
            minecraft.worldRenderer.reload()
        }
    }
}
