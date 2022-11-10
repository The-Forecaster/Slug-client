package trans.rights.client.impl.hack

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.command.CommandSource
import trans.rights.client.api.hack.Hack

object WallHack : Hack("Wallhack", "Makes blocks see through and highlights players") {
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

    override fun build(builder: LiteralArgumentBuilder<CommandSource>): LiteralArgumentBuilder<CommandSource> = builder
}
