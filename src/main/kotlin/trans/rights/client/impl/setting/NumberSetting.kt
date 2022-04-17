package trans.rights.client.impl.setting

import trans.rights.client.api.setting.Setting
import trans.rights.client.util.round

open class NumberSetting(name: String, description: String, default: Double, val increment: Double = 0.1) : Setting<Double>(name, description, default) {
    constructor(name: String, description: String, default: Int) : this(name, description, default.toDouble(), 1.0)

    override fun set(other: Double) {
        this.value = other.round(this.increment)
    }
}
