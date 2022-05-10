package trans.rights.client.api.setting

import java.util.*

abstract class Setting<T>(name: String, description: String, default: T, val isParentSetting: Boolean) :
    ModularSettingContainer(name, description), Comparable<Setting<*>> {
    var value: T = default
    override var children: LinkedList<Setting<*>> = LinkedList()

    override fun <T : Setting<*>> add(setting: T): T {
        if (this.isParentSetting) return super.add(setting)

        throw RuntimeException("$name is not a parent setting!")
    }

    open fun set(other: T) {
        this.value = other
    }

    override operator fun compareTo(other: Setting<*>) = this.name.compareTo(other.name)
}
