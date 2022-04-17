package trans.rights.client.api.setting

import trans.rights.client.api.Modular

abstract class Setting<T : Any>(name: String, description: String, default: T) : Modular(name, description), Comparable<Setting<*>> {
    var value: T

    init {
        this.value = default
    }

    open fun set(other: T) {
        this.value = other
    }

    override fun compareTo(other: Setting<*>): Int {
        return this.name.compareTo(other.name)
    }
}
