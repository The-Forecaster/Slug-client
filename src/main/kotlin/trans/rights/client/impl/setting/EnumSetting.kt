package trans.rights.client.impl.setting

import trans.rights.client.api.setting.Setting

class EnumSetting<T: Enum<*>>(name: String, description: String, default: T) : Setting<T>(name, description, default) {
    private val values: Array<T>

    init {
        this.values = default.javaClass.enumConstants
    }

    fun cycle() {
        if (this.value == this.values[this.values.size - 1]) {
            this.value = this.values[0]
            return
        }
        this.value = this.values[this.values.indexOf(this.value) + 1]
    }

    fun set(other: String) {
        for (value in this.values) {
            if (other.lowercase() == value.name) {
                this.set(value)
            }
        }
    }
}
