package trans.rights.api.setting

abstract class Setting<T> protected constructor(
    name: String, description: String, default: T, vararg children: Setting<*>
) : ModularSettingContainer(name, description), Comparable<Setting<*>> {
    var value = default
    final override val children = children.toList()

    fun set(other: T) {
        this.value = other
    }

    override operator fun compareTo(other: Setting<*>) = this.name.compareTo(other.name)
}
