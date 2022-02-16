package trans.rights.client.modules.hack.hacks

import trans.rights.client.modules.hack.Hack
import net.minecraft.block.Block
import net.minecraft.block.Blocks

object WallHack : Hack("Wallhacks", "Makes blocks see through and highlights players") {
    private var blockAlpha = 50
    private var players = true
    
    private val blocks = mutableSetOf<Block>()

    init {
        this.settings["Block-Alpha"] = blockAlpha
        this.settings["Players"] = players

        blocks.add(Blocks.DIAMOND_ORE)
        blocks.add(Blocks.ANCIENT_DEBRIS)
        blocks.add(Blocks.ENDER_CHEST)
        blocks.add(Blocks.BEDROCK)
    }
     
    override fun onEnable() {
        if (this.nullCheck()) {
            this.disable()
            return
        }
        this.minecraft.worldRenderer.reload()
    }

    override fun onDisable() {
        if (!this.nullCheck()) {
            this.minecraft.worldRenderer.reload()
        }
    }
}
