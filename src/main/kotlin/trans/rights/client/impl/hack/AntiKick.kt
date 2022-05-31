package trans.rights.client.impl.hack

import trans.rights.client.api.hack.Hack
import trans.rights.client.impl.setting.Settings

object AntiKick : Hack("AntiKick", "Prevents you from being kicked from the server.") {
    override val settings = Settings()
}