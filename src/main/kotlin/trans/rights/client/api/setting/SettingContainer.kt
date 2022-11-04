package trans.rights.client.api.setting

import trans.rights.client.api.Modular

interface SettingContainer {
    val children: List<Setting<*>>

    fun get(name: String): Setting<*>? = this.children.find { it.name.lowercase() == name.lowercase() }
}

// This is kind of dumb, but we have to do it
abstract class ModularSettingContainer(name: String, description: String) : Modular(name, description), SettingContainer