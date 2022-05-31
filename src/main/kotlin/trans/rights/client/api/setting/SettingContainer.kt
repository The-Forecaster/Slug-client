package trans.rights.client.api.setting

import trans.rights.client.api.commons.Modular

interface SettingContainer {
    val children: MutableList<Setting<*>>

    fun <T : Setting<*>> add(setting: T): T {
        this.children.add(setting)

        return setting
    }

    fun get(name: String): Setting<*>? {
        for (setting in this.children) if (setting.name.lowercase() == name.lowercase()) return setting

        return null
    }
}

// This is kind of dumb, but we have to do it
abstract class ModularSettingContainer(name: String, description: String) : Modular(name, description), SettingContainer