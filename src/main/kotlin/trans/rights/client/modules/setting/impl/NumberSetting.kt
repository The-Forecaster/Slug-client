package trans.rights.client.modules.setting.impl

import trans.rights.client.modules.setting.Setting

open class NumberSetting(name: String, description: String, default: Double, val increment: Double = 0.1) :
    Setting<Double>(name, description, default) {
    constructor(name: String, description: String, default: Int) : this(name, description, default.toDouble(), 1.0)
}
