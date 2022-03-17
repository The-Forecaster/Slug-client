package trans.rights.client.modules.setting

import trans.rights.client.modules.Module

abstract class Setting<T : Any>(name: String, default: T, var value: T = default) : Module(name), Comparable<Setting<*>> {
    fun set(other: T) {
        this.value = other
    }

    override fun compareTo(other: Setting<*>): Int {
        return this.name.compareTo(other.name)
    }
}
