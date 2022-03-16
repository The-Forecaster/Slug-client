package trans.rights.client.manager.impl

import trans.rights.client.manager.Manager
import trans.rights.client.modules.setting.Setting
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
