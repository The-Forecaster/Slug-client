package me.austin.client.impl.hack

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import me.austin.client.api.hack.Hack
import me.austin.client.impl.setting.*

object WallHack : Hack("Wallhack", "Makes blocks see through and highlights players") {
    enum class Opacity {
        SOME, NONE
    }

    private val opacityLevel = FloatSettingBuilder("Opacity Level").description("How opaque blocks not in the block list will be").default(0.7f).minimum(0.0f).maximum(1.0f).build()
    val opacity = EnumSettingBuilder("Opacity", Opacity.SOME).description("Whether you want the blocks to be visible").children(opacityLevel).build()
    
    override val settings = Settings(opacity)
    
    private val blocks = mutableSetOf(Blocks.DIAMOND_ORE, Blocks.ANCIENT_DEBRIS, Blocks.ENDER_CHEST, Blocks.BEDROCK)

    fun shouldRender(block: Block): Boolean {
        return blocks.contains(block)
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
