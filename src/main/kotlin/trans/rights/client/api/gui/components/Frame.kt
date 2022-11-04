package trans.rights.client.api.gui.components

import net.minecraft.client.util.math.MatrixStack
import trans.rights.client.api.Wrapper
import trans.rights.client.api.gui.Component
import trans.rights.client.api.gui.Container
import trans.rights.client.api.gui.components.buttons.Button

open class Frame<T>(xPos: Int, yPos: Int, width: Int, height: Int, override val children: Collection<Button<T>>) :
    Component(xPos, yPos, width, height), Wrapper, Container {
    override fun render(stack: MatrixStack) = children.forEach { it.render(stack) }
}