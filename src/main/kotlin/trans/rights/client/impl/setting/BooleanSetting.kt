package trans.rights.client.impl.setting

import trans.rights.client.api.setting.Setting

class BooleanSetting(name: String, description: String, default: Boolean) :
    Setting<Boolean>(name, description, default) {
    fun toggle() {
        this.value = !this.value
    }
}
