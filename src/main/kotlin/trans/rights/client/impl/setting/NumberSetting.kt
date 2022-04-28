package trans.rights.client.impl.setting

import trans.rights.client.api.setting.Setting
import trans.rights.client.util.round

open class NumberSetting(
    name: String,
    description: String,
    default: Double,
    isParentSetting: Boolean = false,
    private val increment: Double = 0.1
) : Setting<Double>(name, description, default, isParentSetting) {
    constructor(name: String, description: String, default: Int, isParentSetting: Boolean = false) : this(
        name, description, default.toDouble(), isParentSetting, 1.0
    )

    override fun set(other: Double) = this.set(other, true)

    fun set(other: Double, round: Boolean) = if (round) this.value = other.round(this.increment) else this.value = other
}
