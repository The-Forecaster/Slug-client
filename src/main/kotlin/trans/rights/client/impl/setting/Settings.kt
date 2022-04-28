package trans.rights.client.impl.setting

import trans.rights.client.api.commons.Manager
import trans.rights.client.api.setting.ModularSettingContainer
import trans.rights.client.api.setting.Setting

class Settings : Manager<ModularSettingContainer>(linkedSetOf()), Iterable<ModularSettingContainer> {
    val settings = mutableListOf<Setting<*>>()

    fun <T> add(setting: Setting<T>): Setting<T> {
        if (setting.isParentSetting) this.settings.addAll(setting.children)
        this.settings.add(setting)

        this.values.add(setting)

        return setting
    }

    fun get(setting: String): Setting<*>? {
        this.settings.forEach {
            if (it.name.lowercase() == setting.lowercase()) return it
        }
        return null
    }


    override fun load() {
        this.values.forEach {
            if (it is Setting<*>) this.settings.add(it)
            this.settings.addAll(it.children)
        }

        this.values.sortedWith(Comparator.comparing(ModularSettingContainer::name))
    }

    override fun iterator() = this.values.iterator()
}

class SettingGroup(name: String, description: String) : ModularSettingContainer(name, description) {
    override val children = mutableListOf<Setting<*>>()
}