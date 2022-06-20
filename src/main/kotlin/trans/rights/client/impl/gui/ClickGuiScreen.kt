package trans.rights.client.impl.gui

import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import trans.rights.TransRights
import trans.rights.client.api.hack.HackManager
import trans.rights.client.impl.gui.components.Frame

object ClickGuiScreen : Screen(Text.of(TransRights.NAME)) {
    private val frames = listOf(object : Frame(0, 0, 0, 0) {
        override fun render(stack: MatrixStack) {
            TODO("Not yet implemented")
        }
    })

    private var shouldCloseOnEsc: Boolean = true

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        throw RuntimeException()
    }

    override fun shouldPause() = false

    override fun shouldCloseOnEsc() = shouldCloseOnEsc
}