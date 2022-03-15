package trans.rights.client.modules.setting

import trans.rights.client.modules.Manager
import java.util.*

class Settings : Manager<Setting<*>>(mutableListOf()) {
    fun get(name: String): Setting<*>? {
        for (setting in this.values)
            if (setting.name.lowercase() == name.lowercase())
                return setting

        return null
    }

    override fun load() {
        values.toSortedSet(Comparator.naturalOrder())
    }
}
