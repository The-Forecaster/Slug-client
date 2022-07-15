package trans.rights.client.impl.setting

import trans.rights.client.api.setting.Setting

class BooleanSetting(
    name: String, description: String, default: Boolean, vararg children: Setting<*>
) : Setting<Boolean>(name, description, default, *children) {
    fun toggle() {
        this.value = !this.value
    }
}
