package trans.rights.client.modules.hack.impl

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import trans.rights.client.events.BlockSideDrawEvent
import trans.rights.client.modules.hack.Hack
import trans.rights.client.modules.setting.settings.BooleanSetting
import trans.rights.client.modules.setting.settings.IntSetting
import trans.rights.event.annotation.EventHandler

object WallHack : Hack("Wallhacks", "Makes blocks see through and highlights players") {
    private var blockAlpha = IntSetting("Block-Alpha", 50)
    private var players = BooleanSetting("Players", true)

    private val blocks = mutableSetOf<Block>()

    init {
        settings.add(blockAlpha)
        settings.add(players)

        blocks.add(Blocks.DIAMOND_ORE)
        blocks.add(Blocks.ANCIENT_DEBRIS)
        blocks.add(Blocks.ENDER_CHEST)
        blocks.add(Blocks.BEDROCK)
    }

    @EventHandler
    fun onBlockDraw(event: BlockSideDrawEvent) {
        if (!blocks.contains(event.block)) {
            event.isCancelled = true
        }
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
