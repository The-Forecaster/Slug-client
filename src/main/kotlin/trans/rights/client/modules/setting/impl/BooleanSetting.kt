package trans.rights.client.modules.setting.impl

import trans.rights.client.modules.setting.Setting

class BooleanSetting(name: String, default: Boolean) : Setting<Boolean>(name, default) {
    fun toggle() {
        this.value = !this.value
    }
}
