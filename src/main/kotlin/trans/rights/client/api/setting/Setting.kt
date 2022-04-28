package trans.rights.client.api.setting

import java.util.*

abstract class Setting<T>(name: String, description: String, default: T, val isParentSetting: Boolean) : ModularSettingContainer(name, description), Comparable<Setting<*>> {
    var value: T = default
    override var children: LinkedList<Setting<*>> = LinkedList()

    open fun set(other: T) {
        this.value = other
    }

    open fun add(setting: Setting<*>) = this.children.add(setting)

    override operator fun compareTo(other: Setting<*>) = this.name.compareTo(other.name)
}
