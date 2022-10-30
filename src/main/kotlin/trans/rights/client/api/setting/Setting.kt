package trans.rights.client.api.setting

abstract class Setting<T>(
    name: String, description: String, default: T, vararg children: Setting<*>
) : ModularSettingContainer(name, description), Comparable<Setting<*>> {
    var value = default
    override var children = children.toList()

    open fun set(other: T) {
        this.value = other
    }

    override operator fun compareTo(other: Setting<*>) = this.name.compareTo(other.name)
}
