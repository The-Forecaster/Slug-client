package trans.rights.client.api.gui.components

import net.minecraft.client.util.math.MatrixStack
import trans.rights.client.api.Wrapper
import trans.rights.client.api.gui.Component
import trans.rights.client.api.gui.components.buttons.Button

open class Frame<T>(xPos: Int, yPos: Int, width: Int, height: Int, private val buttons: List<Button<T>>) : Component(xPos, yPos, width, height), Wrapper {
    override fun render(stack: MatrixStack) = buttons.forEach { it.render(stack) }
}