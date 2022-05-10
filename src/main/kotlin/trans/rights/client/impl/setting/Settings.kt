package trans.rights.client.impl.setting

import trans.rights.client.api.commons.Manager
import trans.rights.client.api.setting.ModularSettingContainer
import trans.rights.client.api.setting.Setting
import trans.rights.client.api.setting.SettingContainer

class Settings : Manager<ModularSettingContainer>(linkedSetOf()), Iterable<ModularSettingContainer> {
    fun allSettings() = this.values.flatMap { if (it is Setting<*> && it.isParentSetting) (it.children + it) else it.children}.toMutableList()

    fun add(setting: ModularSettingContainer): ModularSettingContainer {
        if (setting is Setting<*> && setting.isParentSetting) this.allSettings().addAll(setting.children)
        this.values.add(setting)

        this.values.add(setting)

        return setting
    }

    fun get(setting: String): Setting<*>? = this.allSettings().find {
        it.name.lowercase() == setting.lowercase()
    }

    override fun load() {
        this.values.forEach {
            if (it is Setting<*>) this.allSettings().add(it)
            this.allSettings().addAll(it.children)
        }

        this.values.sortedWith(Comparator.comparing(ModularSettingContainer::name))
    }

    override fun iterator() = this.values.iterator()
}

class SettingGroup(name: String, description: String) : ModularSettingContainer(name, description) {
    override val children = mutableListOf<Setting<*>>()
}