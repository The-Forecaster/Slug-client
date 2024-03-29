package me.austin.client.impl.hack

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import me.austin.client.api.hack.Hack
import me.austin.client.impl.setting.EnumSetting
import me.austin.client.impl.setting.Settings

object WallHack : Hack("Wallhack", "Makes blocks see through and highlights players") {
    enum class Opacity {
        SOME, NONE
    }
    
    val opacity = EnumSetting<Opacity>("Opacity", "Whether you want the blocks to be visible", Opacity.SOME)
    
    override val settings = Settings(opacity)
    
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
