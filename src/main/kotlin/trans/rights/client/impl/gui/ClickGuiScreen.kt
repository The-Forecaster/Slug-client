package trans.rights.client.impl.gui

import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import trans.rights.TransRights
import trans.rights.client.api.hack.Hack
import trans.rights.client.api.hack.HackManager
import trans.rights.client.impl.gui.components.Frame
import trans.rights.client.impl.gui.components.buttons.Button

object ClickGuiScreen : Screen(Text.of(TransRights.NAME)) {
    private val frames: List<Frame<*>>

    init {
        var offset = 0

        frames = listOf(object : Frame<Hack>(
            20, 20, 60, HackManager.values.size * 20, HackManager.values.map {
                offset += 20

                object : Button<Hack>(20, offset, 60, 20, it) {
                    override fun render(stack: MatrixStack) {
                        TODO("Not yet implemented")
                    }
                }
            }
        ) {
            override fun render(stack: MatrixStack) {
                TODO("Not yet implemented")
            }
        })
    }

    private var shouldCloseOnEsc: Boolean = true

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        throw RuntimeException()
    }

    override fun shouldPause() = false

    override fun shouldCloseOnEsc() = shouldCloseOnEsc
}