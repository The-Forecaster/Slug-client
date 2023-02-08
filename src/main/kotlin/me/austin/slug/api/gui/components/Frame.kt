package me.austin.client.api.gui.components

import net.minecraft.client.util.math.MatrixStack
import me.austin.client.api.Wrapper
import me.austin.client.api.gui.Component
import me.austin.client.api.gui.Container
import me.austin.client.api.gui.components.buttons.Button

open class Frame<T>(xPos: Int, yPos: Int, width: Int, height: Int, override val children: Collection<Button<T>>) :
    Component(xPos, yPos, width, height), Wrapper, Container {
    override fun render(stack: MatrixStack) {
        for (button in children) button.render(stack)
    }
}