package me.austin.client.impl.setting

import me.austin.client.api.setting.Setting

class EnumSetting<T : Enum<*>>(
    name: String, description: String, default: T, vararg children: Setting<*>
) : Setting<T>(name, description, default, *children) {
    private val values = default.javaClass.enumConstants

    /**
     * set function but accepts a string instead of an enum constant
     *
     * @param other string to set the enum value to
     * @return true if the string is able to be set to one of the enum constants in this setting
     */
    fun set(other: String): Boolean {
        for (value in this.values) {
            if (other.lowercase() == value.toString().lowercase()) {
                this.set(value)
                return true
            }
        }
        return false
    }
}
