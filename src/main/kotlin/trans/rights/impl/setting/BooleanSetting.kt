package trans.rights.impl.setting

import trans.rights.api.setting.Setting

class BooleanSetting(
    name: String, description: String, default: Boolean, vararg children: Setting<*>
) : Setting<Boolean>(name, description, default, *children)