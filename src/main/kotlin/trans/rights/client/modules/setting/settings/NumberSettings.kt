package trans.rights.client.modules.setting.settings

import trans.rights.client.modules.setting.Setting

class NumberSetting(name: String, default: Double, val increment: Double) : Setting<Double>(name, default) {
    constructor(name: String, default: Double) : this(name, default, 1.0)

    constructor(name: String, default: Int) : this(name, default.toDouble())
}
