package trans.rights.client.impl.setting

import trans.rights.client.api.commons.Manager
import trans.rights.client.api.setting.ModularSettingContainer
import trans.rights.client.api.setting.Setting


class Settings(settings: List<ModularSettingContainer>) :
    Manager<ModularSettingContainer, List<ModularSettingContainer>>(settings), Iterable<ModularSettingContainer> {
    constructor(vararg settings: ModularSettingContainer) : this(settings.asList())

    fun allSettings() =
        this.values.flatMap { if (it is Setting<*> && it.isParentSetting) (it.children + it) else it.children }

    fun get(setting: String): Setting<*>? = this.allSettings().find { it.name.lowercase() == setting.lowercase() }

    override fun load() {
        this.values.sortedWith(Comparator.comparing(ModularSettingContainer::name))
    }

    override fun unload() {}

    override fun iterator() = this.allSettings().iterator()
}

class SettingGroup(name: String, description: String) : ModularSettingContainer(name, description) {
    override val children = mutableListOf<Setting<*>>()
}
