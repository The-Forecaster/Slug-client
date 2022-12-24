package trans.rights.impl.setting

import trans.rights.api.Manager
import trans.rights.api.setting.ModularSettingContainer
import trans.rights.api.setting.Setting

/**
 * For all settings within a module
 *
 * Use only once per module
 */
class Settings(private val values: List<ModularSettingContainer>) {
    constructor(vararg settings: ModularSettingContainer) : this(settings.asList())

    val allSettings = this.values.flatMap { if (it is Setting<*>) (it.children + it) else it.children }

    fun get(setting: String) = this.allSettings.find { it.name.lowercase() == setting.lowercase() }
}
