package me.austin.client.api.setting

abstract class AbstractSettingBuilder<T, S : Setting<T>, B : AbstractSettingBuilder<T, S, B>> internal constructor(
    protected val name: String
) {
    protected var default: T? = null
    protected var description: String? = null
    protected var children: Array<out Setting<*>>? = null

    fun default(default: T): B {
        this.default = default

        return this as B
    }

    fun description(description: String): B {
        this.description = description

        return this as B
    }

    fun children(vararg children: Setting<*>): B {
        this.children = children

        return this as B
    }

    abstract fun build(): S
}

abstract class NumberSettingBuilder<T : Number, S : NumberSetting<T>, B : NumberSettingBuilder<T, S, B>> internal constructor(
    name: String
) : AbstractSettingBuilder<T, S, B>(name) {
    protected var increment: T? = null
    protected var minumum: T? = null
    protected var maximum: T? = null

    fun increment(increment: T): B {
        this.increment = increment

        return this as B
    }

    fun minimum(minimum: T): B {
        this.minumum = minimum

        return this as B
    }

    fun maximum(maximum: T): B {
        this.maximum = maximum

        return this as B
    }
}