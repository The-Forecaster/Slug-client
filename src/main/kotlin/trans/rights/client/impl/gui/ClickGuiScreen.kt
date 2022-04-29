package trans.rights.client.impl.gui

import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import trans.rights.TransRights

object ClickGuiScreen : Screen(Text.of(TransRights.NAME)) {
    private var shouldCloseOnEsc: Boolean = true

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        throw RuntimeException()
    }

    override fun shouldPause() = false

    override fun shouldCloseOnEsc() = this.shouldCloseOnEsc
}