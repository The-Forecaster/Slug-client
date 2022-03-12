package trans.rights.client.modules.setting

import trans.rights.client.modules.Module

abstract class Setting<T : Any>(name: String, description: String, var value: T, var default: T) : Module(name, description), Comparable<Setting<*>> {
    override fun compareTo(other: Setting<*>): Int {
        return this.name.compareTo(other.name)
    }
}
