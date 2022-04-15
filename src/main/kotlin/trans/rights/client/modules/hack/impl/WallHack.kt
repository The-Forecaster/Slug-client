package trans.rights.client.modules.hack.impl

import trans.rights.event.listener.impl.LambdaListener
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import trans.rights.client.events.BlockSideDrawEvent
import trans.rights.client.misc.api.Globals
import trans.rights.client.modules.hack.Hack
import trans.rights.event.listener.EventHandler
import trans.rights.event.listener.impl.listener

object WallHack : Hack("Wall-hacks", "Makes blocks see through and highlights players"), Globals {
    private val blocks = mutableSetOf<Block>()

    @EventHandler
    val blockDrawListener: LambdaListener<BlockSideDrawEvent> = listener { event ->
        if (blocks.contains(event.block)) {
            event.isCancelled = true
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
