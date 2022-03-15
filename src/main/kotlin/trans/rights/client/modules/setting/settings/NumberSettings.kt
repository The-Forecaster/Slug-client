package trans.rights.client.modules.setting.settings

import trans.rights.client.modules.setting.Setting

abstract class NumberSetting<T : Number>(name: String, description: String, default: T) : Setting<Number>(name, description, default) {
    fun set(other: T, round: Boolean) {
        this.value = when (round) {
            true -> (other.toDouble() * 10).toInt() / 10
            false -> other
        }
    }
}

class IntSetting(name: String, description: String, default: Int) : NumberSetting<Int>(name, description, default)

class DoubleSetting(name: String, description: String, default: Double) : NumberSetting<Double>(name, description, default)