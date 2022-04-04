package trans.rights.client.modules.setting.impl

import trans.rights.client.modules.setting.Setting

open class NumberSetting(name: String, default: Double, val increment: Double) : Setting<Double>(name, default) {
    constructor(name: String, default: Double) : this(name, default, 0.1)

    constructor(name: String, default: Int) : this(name, default.toDouble(), 1.0)
}
