package trans.rights.client.modules.setting.settings

import trans.rights.client.modules.setting.Setting

class EnumSetting(
        name: String,
        value: Enum<*>,
        default: Enum<*>,
        private val values: Array<Enum<*>>
) : Setting<Enum<*>>(name, value, default) {
    fun cycle() {
        if (this.value == this.values[this.values.size - 1]) {
            this.value = this.values[0]
            return
        }
        this.value = this.values[this.values.indexOf(this.value) + 1]
    }
}
