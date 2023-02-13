package me.austin.client.impl.setting

import me.austin.client.api.Manager
import me.austin.client.api.setting.ModularSettingContainer
import me.austin.client.api.setting.Setting

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
