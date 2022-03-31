package trans.rights.client.modules.hack.impl

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import trans.rights.client.events.BlockSideDrawEvent
import trans.rights.client.misc.api.Globals
import trans.rights.client.modules.hack.Hack
import trans.rights.event.annotation.EventHandler
import trans.rights.event.listener.impl.LambdaListener
import trans.rights.event.listener.impl.lambdaListener

object WallHack : Hack("Wallhacks", "Makes blocks see through and highlights players"), Globals {
    private val blocks = mutableSetOf<Block>()

    @EventHandler
    val blockDrawListener: LambdaListener<BlockSideDrawEvent> = lambdaListener { event ->
        if (!blocks.contains(event.block)) {
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
