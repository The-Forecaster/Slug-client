package me.austin.client.impl.setting

import me.austin.client.api.setting.NumberSetting
import me.austin.client.api.setting.Setting
import me.austin.client.util.round

open class BooleanSetting internal constructor(
    final override val name: String, final override val default: Boolean
) : Setting<Boolean> {
    override var value = default
}

open class EnumSetting<T : Enum<*>> internal constructor(
    final override val name: String, final override val default: T
) : Setting<T> {
    override var value = default

    /**
     * set function but accepts a string instead of an enum constant
     *
     * @param other string to set the enum value to
     * @return true if the string matches one of the enum constants in this setting
     */
    fun set(other: String): Boolean {
        for (value in this.default.javaClass.enumConstants) {
            if (other.lowercase() == value.toString().lowercase()) {
                this.value = value
                return true
            }
        }
        return false
    }
}

open class LongSetting internal constructor(name: String, default: Long) : NumberSetting<Long>(name, default, 1L)

open class IntSetting internal constructor(name: String, default: Int) : NumberSetting<Int>(name, default, 1)

open class DoubleSetting internal constructor(
    name: String, default: Double, increment: Double
) : NumberSetting<Double>(name, default, increment) {
    fun set(other: Double, round: Boolean) {
        if (round) {
            this.value = other.round(this.increment)
        } else {
            this.value = other
        }
    }
}

open class FloatSetting internal constructor(name: String, default: Float, increment: Float) :
    NumberSetting<Float>(name, default, increment) {
    fun set(other: Float, round: Boolean) {
        if (round) this.value = other.round(this.increment) else this.value = other
    }
}