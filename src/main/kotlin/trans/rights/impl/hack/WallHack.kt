package trans.rights.impl.hack

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import trans.rights.api.hack.Hack
import trans.rights.impl.setting.EnumSetting
import trans.rights.impl.setting.Settings

object WallHack : Hack("Wallhack", "Makes blocks see through and highlights players") {
    enum class Opacity {
        SOME, NONE
    }
    
    val opacity = EnumSetting<Opacity>("Opacity", "Whether you want the blocks to be visible", Opacity.SOME)
    
    override val settings = Settings(opacity)
    
    private val blocks = mutableSetOf(Blocks.DIAMOND_ORE, Blocks.ANCIENT_DEBRIS, Blocks.ENDER_CHEST, Blocks.BEDROCK)

    fun shouldRender(block: Block) = blocks.contains(block) || opacity.value != Opacity.NONE

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
