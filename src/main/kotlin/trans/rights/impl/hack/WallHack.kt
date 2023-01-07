package trans.rights.impl.hack

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import trans.rights.api.hack.Hack

object WallHack : Hack("Wallhack", "Makes blocks see through and highlights players") {
    enum class Opacity {
        SOME, NONE
    }
    
    private val blocks = mutableSetOf(Blocks.DIAMOND_ORE, Blocks.ANCIENT_DEBRIS, Blocks.ENDER_CHEST, Blocks.BEDROCK)

    fun shouldRender(block: Block) = blocks.contains(block)

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
