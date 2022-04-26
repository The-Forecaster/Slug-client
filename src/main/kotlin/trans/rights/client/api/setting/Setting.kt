package trans.rights.client.api.setting

import trans.rights.client.api.commons.Modular
import java.util.LinkedList

abstract class Setting<T : Any>(name: String, description: String, default: T, isParentSetting: Boolean) : Modular(name, description), Comparable<Setting<*>> {
    var value: T = default
    var children: LinkedList<Setting<*>>? = null
        private set

    init {
        if (isParentSetting) this.children = LinkedList()
    }

    open fun set(other: T) {
        this.value = other
    }

    open fun add(setting: Setting<*>) {
        this.children!!.add(setting)
    }

    override operator fun compareTo(other: Setting<*>): Int {
        return this.name.compareTo(other.name)
    }
}
