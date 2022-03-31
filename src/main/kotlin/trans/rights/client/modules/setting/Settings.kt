package trans.rights.client.modules.setting

class Settings {
    val values = mutableSetOf<Setting<*>>()

    fun get(name: String): Setting<*>? {
        for (setting in this.values) if (setting.name.lowercase() == name.lowercase()) return setting

        return null
    }

    fun <T : Any> add(setting: Setting<T>): Setting<T> {
        this.values.add(setting)

        return setting
    }
}