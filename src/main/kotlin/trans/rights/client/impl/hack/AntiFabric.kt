package trans.rights.client.impl.hack

import trans.rights.client.api.hack.Hack
import trans.rights.client.impl.setting.Settings

object AntiFabric : Hack("AntiFabric", "Removes the fabric signature from the client") {
    override val settings = Settings()
}