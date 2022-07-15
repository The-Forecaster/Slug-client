package trans.rights.client.util

import net.minecraft.client.gui.hud.ChatHud
import net.minecraft.text.Text
import trans.rights.TransRights.Companion.NAME

internal const val prefix = "§9[$NAME]§r "

fun ChatHud.clientSend(text: String, pre: Boolean = true) = this.addMessage(Text.of(if (pre)"$prefix$text" else text))

fun ChatHud.error(text: String, pre: Boolean = true) = this.addMessage(Text.of(if (pre)"$prefix$text" else text))
