package trans.rights.client.api.gui

abstract class Component(var xPos: Int, var yPos: Int, var width: Int, var height: Int) {
    fun isOver(mouseX: Int, mouseY: Int): Boolean {
        return mouseX > xPos && mouseX < xPos + width && mouseY > yPos && mouseY < yPos + height
    }

    fun setPos(x: Int, y: Int) {
        this.xPos = x
        this.yPos = y
    }
}