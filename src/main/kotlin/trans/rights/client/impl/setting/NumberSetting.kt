package trans.rights.client.impl.setting

import trans.rights.client.api.setting.Setting
import trans.rights.client.util.round

open class NumberSetting(
    name: String, description: String, default: Double, private val increment: Double = 0.1, vararg children: Setting<*>
) : Setting<Double>(name, description, default, *children) {
    constructor(
        name: String, description: String, default: Int, vararg children: Setting<*>
    ) : this(
        name, description, default.toDouble(), 1.0, *children
    )

    fun set(other: Double, round: Boolean = false) = if (round) this.value = other.round(this.increment) else this.value = other
}
