package trans.rights.client.util

import net.minecraft.client.gui.hud.ChatHud
import net.minecraft.text.Text
import trans.rights.TransRights.Companion.NAME

internal const val prefix = "§9[$NAME]§r "

fun ChatHud.clientSend(text: String) {
    this.clientSend(text)
}

fun ChatHud.clientSend(text: String, pre: Boolean) {
    if (pre) this.addMessage(Text.of("${prefix}$text"))
    else this.addMessage(Text.of(text))
}
