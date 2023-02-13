package me.austin.client.impl.setting

import me.austin.client.api.setting.Setting
import me.austin.client.util.round

abstract class NumberSetting<T : Number>(
    name: String, description: String, default: T, protected val increment: T, vararg children: Setting<*>
) : Setting<T>(name, description, default, *children)

class LongSetting(name: String, description: String, default: Long, vararg children: Setting<*>) :
    NumberSetting<Long>(name, description, default, 1L, *children)

class IntSetting(name: String, description: String, default: Int, vararg children: Setting<*>) :
    NumberSetting<Int>(name, description, default, 1, *children)

class ShortSetting(name: String, description: String, default: Short, vararg children: Setting<*>) :
    NumberSetting<Short>(name, description, default, 1, *children)

class DoubleSetting(
    name: String, description: String, default: Double, increment: Double, vararg children: Setting<*>
) : NumberSetting<Double>(name, description, default, increment, *children) {
    fun set(other: Double, round: Boolean) = if (round) this.value = other.round(this.increment) else this.value = other
}

class FloatSetting(name: String, description: String, default: Float, increment: Float, vararg children: Setting<*>) :
    NumberSetting<Float>(name, description, default, increment, *children) {
    fun set(other: Float, round: Boolean) = if (round) this.value = other.round(this.increment) else this.value = other
}