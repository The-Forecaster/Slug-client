package trans.rights.client.impl.hack

import net.minecraft.block.Blocks
import trans.rights.client.api.hack.Hack
import trans.rights.client.events.BlockSideDrawEvent
import trans.rights.event.listener

object WallHack : Hack("Wallhack", "Makes blocks see through and highlights players") {
    private val blocks = mutableSetOf(Blocks.DIAMOND_ORE, Blocks.ANCIENT_DEBRIS, Blocks.ENDER_CHEST, Blocks.BEDROCK)

    override val listeners = listOf(listener<BlockSideDrawEvent> { event ->
        if (nullCheck()) return@listener

        if (!blocks.contains(event.block)) event.cancel()
    })

    override fun onEnable() {
        if (nullCheck()) {
            disable()
            return
        }
        minecraft.worldRenderer.reload()
    }

    override fun onDisable() {
        if (!nullCheck()) minecraft.worldRenderer.reload()
    }
}
