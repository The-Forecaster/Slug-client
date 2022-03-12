package trans.rights.client.modules.setting

import trans.rights.client.modules.Manager
import java.util.*

class Settings : Manager<Setting<*>>(mutableListOf()) {
    override fun load() {
        values.toSortedSet(Comparator.naturalOrder())
    }
}
