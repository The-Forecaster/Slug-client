package trans.rights.client.impl.setting

import trans.rights.client.api.Manager
import trans.rights.client.api.setting.ModularSettingContainer
import trans.rights.client.api.setting.Setting

/**
 * For all settings within a module
 *
 * Use only once per module
 */
class Settings(override val values: List<ModularSettingContainer>) :
    Manager<ModularSettingContainer, List<ModularSettingContainer>> {
    constructor(vararg settings: ModularSettingContainer) : this(settings.asList())

    val allSettings = this.values.flatMap { if (it is Setting<*>) (it.children + it) else it.children }

    fun get(setting: String) = this.allSettings.find { it.name.lowercase() == setting.lowercase() }

    override fun load() {}

    override fun unload() {}
}
