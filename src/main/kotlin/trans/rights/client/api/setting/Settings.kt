package trans.rights.client.api.setting

import trans.rights.client.api.commons.Manager

class Settings : Manager<Setting<*>>(mutableSetOf()) {
    fun get(name: String): Setting<*>? {
        for (setting in this.values) if (setting.name.lowercase() == name.lowercase()) return setting

        return null
    }

    fun <T : Setting<*>> add(setting: T): T {
        this.values.add(setting)

        return setting
    }

    override fun load() {}
}
