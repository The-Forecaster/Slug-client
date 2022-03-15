package trans.rights.client.modules.setting.settings

import trans.rights.client.modules.setting.Setting

class BooleanSetting(name: String, description: String, default: Boolean) : Setting<Boolean>(name, description, default) {
    fun toggle() {
        this.value = !this.value
    }
}
