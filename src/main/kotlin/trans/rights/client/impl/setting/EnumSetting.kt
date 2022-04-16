package trans.rights.client.impl.setting

import trans.rights.client.api.setting.Setting

class EnumSetting(name: String, description: String, default: Enum<*>, private val values: Array<Enum<*>>) :
    Setting<Enum<*>>(name, description, default) {
    fun cycle() {
        if (this.value == this.values[this.values.size - 1]) {
            this.value = this.values[0]
            return
        }
        this.value = this.values[this.values.indexOf(this.value) + 1]
    }
}
