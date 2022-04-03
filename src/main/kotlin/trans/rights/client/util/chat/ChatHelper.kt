package trans.rights.client.util.chat

import net.minecraft.text.Text
import trans.rights.client.TransRights.Companion.NAME
import trans.rights.client.misc.api.Globals

object ChatHelper : Globals {
    private const val prefix = "\\u00A79[${NAME}]\\u00A79"

    fun send(text: String) {
        minecraft.inGameHud.chatHud.addMessage(Text.of("$prefix$text"))
    }
}
