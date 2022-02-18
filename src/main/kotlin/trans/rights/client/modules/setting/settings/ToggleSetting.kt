package trans.rights.client.modules.setting.settings

import trans.rights.client.modules.setting.Setting

class ToggleSetting(name: String, description: String, value: Boolean, default: Boolean) : Setting<Boolean>(name, description, value, default) {
    fun toggle() {
        this.value = !this.value
    }
}