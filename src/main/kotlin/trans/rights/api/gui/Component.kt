package trans.rights.api.gui

import net.minecraft.client.util.math.MatrixStack

abstract class Component(var xPos: Int, var yPos: Int, var width: Int, var height: Int) {
    fun isOver(mouseX: Int, mouseY: Int) = mouseX > xPos && mouseX < xPos + width && mouseY > yPos && mouseY < yPos + height

    fun setPos(x: Int, y: Int) {
        this.xPos = x
        this.yPos = y
    }

    abstract fun render(stack: MatrixStack)
}
