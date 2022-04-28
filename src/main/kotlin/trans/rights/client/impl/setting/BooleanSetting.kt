package trans.rights.client.impl.setting

import trans.rights.client.api.setting.Setting

class BooleanSetting(name: String, description: String, default: Boolean, isParentSetting: Boolean = false) : Setting<Boolean>(name, description, default, isParentSetting) {
    fun toggle() {
        this.value = !this.value
    }
}
