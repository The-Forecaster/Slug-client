package trans.rights.client.modules.setting.settings

import trans.rights.client.modules.setting.Setting

abstract class NumberSetting<T : Number>(name: String, default: T) : Setting<Number>(name, default)

class IntSetting(name: String, default: Int) : NumberSetting<Int>(name, default)

class DoubleSetting(name: String, default: Double) : NumberSetting<Double>(name, default) {
    fun set(other: Double, round: Boolean) {
        this.value = if (round) (other * 10).toInt() / 10 else other
    }
}