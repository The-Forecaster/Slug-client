package trans.rights.client.impl.gui.components.buttons

import trans.rights.client.api.gui.Component

abstract class Button<T>(xPos: Int, yPos: Int, width: Int, height: Int, val parent: T) : Component(xPos, yPos, width, height) {

}