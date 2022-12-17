package trans.rights.api.gui.components

import net.minecraft.client.util.math.MatrixStack
import trans.rights.api.Wrapper
import trans.rights.api.gui.Component
import trans.rights.api.gui.Container
import trans.rights.api.gui.components.buttons.Button

open class Frame<T>(xPos: Int, yPos: Int, width: Int, height: Int, override val children: Collection<Button<T>>) :
    Component(xPos, yPos, width, height), Wrapper, Container {
    override fun render(stack: MatrixStack) {
        for (button in children) button.render(stack)
    }
}