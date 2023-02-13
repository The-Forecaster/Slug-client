package me.austin.client.api.gui.components.buttons

import me.austin.client.api.gui.Component

abstract class Button<T>(xPos: Int, yPos: Int, width: Int, height: Int, private val parent: T) : Component(xPos, yPos, width, height)