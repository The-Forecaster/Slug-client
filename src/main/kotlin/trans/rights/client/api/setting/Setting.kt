package trans.rights.client.api.setting

import trans.rights.client.api.Modular

abstract class Setting<T : Any>(name: String, description: String, default: T, var value: T = default) : Modular(name, description), Comparable<Setting<*>> {
    fun set(other: T) {
        this.value = other
    }

    override fun compareTo(other: Setting<*>): Int {
        return this.name.compareTo(other.name)
    }
}
