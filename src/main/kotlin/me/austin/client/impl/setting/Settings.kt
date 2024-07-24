package me.austin.client.impl.setting

import me.austin.client.api.setting.Children
import me.austin.client.api.setting.Setting

/**
 * For all settings within a module
 *
 * Use only once per module
 */
class Settings(private val settings: List<Setting<*>>) {
    constructor(vararg settings: Setting<*>) : this(settings.asList())

    operator fun get(setting: String): Setting<*>? {
        return this.allSettings().find { it.name.lowercase() == setting.lowercase() }
    }

    fun allSettings(): List<Setting<*>> {
        val list = mutableListOf<Setting<*>>()

        fun recurse(settings: Array<out Setting<*>>) {
            for (setting in settings) {
                if (setting is Children) {
                    recurse(setting.children)
                }
                list.add(setting)
            }
        }

        recurse(this.settings.toTypedArray())
        return list
    }
}
