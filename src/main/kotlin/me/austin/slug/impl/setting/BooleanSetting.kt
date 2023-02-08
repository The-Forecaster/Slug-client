package me.austin.client.impl.setting

import me.austin.client.api.setting.Setting

class BooleanSetting(
    name: String, description: String, default: Boolean, vararg children: Setting<*>
) : Setting<Boolean>(name, description, default, *children)