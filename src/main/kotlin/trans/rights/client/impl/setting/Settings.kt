package trans.rights.client.impl.setting

import trans.rights.client.api.commons.Manager
import trans.rights.client.api.setting.ModularSettingContainer
import trans.rights.client.api.setting.Setting

class Settings : Manager<ModularSettingContainer>(linkedSetOf()) {
    val settings = mutableListOf<Setting<*>>()

    fun <T> add(setting: Setting<T>): Setting<T> {
        this.values.add(setting)

        return setting
    }


    override fun load() {
        this.values.forEach {
            if (it is Setting<*>) this.settings.add(it)
            this.settings.addAll(it.children)
        }

        this.values.sortedWith(Comparator.comparing(ModularSettingContainer::name))
    }
}

class SettingGroup(name: String, description: String) : ModularSettingContainer(name, description) {
    override val children: MutableList<Setting<*>> = mutableListOf()
}