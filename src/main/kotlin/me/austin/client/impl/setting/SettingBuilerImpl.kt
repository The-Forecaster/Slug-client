package me.austin.client.impl.setting

import me.austin.client.api.setting.Children
import me.austin.client.api.Description
import me.austin.client.api.setting.AbstractSettingBuilder
import me.austin.client.api.setting.Constrained
import me.austin.client.api.setting.NumberSettingBuilder
import me.austin.client.api.setting.Setting

// Overengineered?
// Maybe

class BooleanSettingBuilder(name: String) :
    AbstractSettingBuilder<Boolean, BooleanSetting, BooleanSettingBuilder>(name) {
    override fun build(): BooleanSetting {
        if (this.description != null) {
            if (this.children != null) {
                class BooleanSettingWithDescriptionWithChildren(
                    name: String,
                    default: Boolean,
                    override val description: String,
                    override val children: Array<out Setting<*>>
                ) : BooleanSetting(
                    name, default
                ), Description, Children

                return BooleanSettingWithDescriptionWithChildren(
                    this.name, this.default ?: true, this.description!!, this.children!!
                )
            }

            class BooleanSettingWithDescription(
                name: String,
                default: Boolean,
                override val description: String,
            ) : BooleanSetting(
                name, default
            ), Description

            return BooleanSettingWithDescription(this.name, this.default ?: true, this.description!!)
        } else if (this.children != null) {
            class BooleanSettingWithChildren(
                name: String, default: Boolean, override val children: Array<out Setting<*>>
            ) : BooleanSetting(
                name, default
            ), Children

            return BooleanSettingWithChildren(this.name, this.default ?: true, this.children!!)
        }

        return BooleanSetting(this.name, this.default ?: true)
    }
}

class EnumSettingBuilder<T : Enum<*>>(name: String, default: T) :
    AbstractSettingBuilder<T, EnumSetting<T>, EnumSettingBuilder<T>>(
        name
    ) {
    init {
        this.default = default
    }

    override fun build(): EnumSetting<T> {
        if (this.description != null) {
            if (this.children != null) {
                class EnumSettingWithDescriptionWithChildren(
                    name: String,
                    default: T,
                    override val description: String,
                    override val children: Array<out Setting<*>>
                ) : EnumSetting<T>(
                    name, default
                ), Description, Children

                return EnumSettingWithDescriptionWithChildren(
                    this.name, this.default!!, this.description!!, this.children!!
                )
            }

            class EnumSettingWithDescription(
                name: String,
                default: T,
                override val description: String,
            ) : EnumSetting<T>(
                name, default
            ), Description

            return EnumSettingWithDescription(this.name, this.default!!, this.description!!)
        } else if (this.children != null) {
            class EnumSettingWithChildren(
                name: String, default: T, override val children: Array<out Setting<*>>
            ) : EnumSetting<T>(
                name, default
            ), Children

            return EnumSettingWithChildren(this.name, this.default!!, this.children!!)
        }

        return EnumSetting(this.name, this.default!!)
    }
}

class IntSettingBuilder(name: String) : NumberSettingBuilder<Int, IntSetting, IntSettingBuilder>(name) {
    override fun build(): IntSetting {
        if (this.minumum != null || this.maximum != null) {
            if (this.description != null) {
                if (this.children != null) {
                    class ConstrainedIntSettingWithDescriptionWithChildren(
                        name: String,
                        default: Int,
                        override val minimum: Int,
                        override val maximum: Int,
                        override val description: String,
                        override val children: Array<out Setting<*>>
                    ) : IntSetting(name, default), Constrained<Int>, Description, Children

                    return ConstrainedIntSettingWithDescriptionWithChildren(
                        this.name,
                        this.default ?: 0,
                        this.minumum ?: -20,
                        this.maximum ?: 20,
                        this.description!!,
                        this.children!!
                    )
                }

                class ConstrainedIntSettingWithDescription(
                    name: String,
                    default: Int,
                    override val description: String,
                    override val minimum: Int,
                    override val maximum: Int
                ) : IntSetting(name, default), Constrained<Int>, Description

                return ConstrainedIntSettingWithDescription(
                    this.name, this.default ?: 0, this.description!!, this.minumum ?: -20, this.maximum ?: 20
                )
            } else if (this.children != null) {
                class ConstrainedIntSettingWithChildren(
                    name: String,
                    default: Int,
                    override val children: Array<out Setting<*>>,
                    override val minimum: Int,
                    override val maximum: Int
                ) : IntSetting(name, default), Constrained<Int>, Children

                return ConstrainedIntSettingWithChildren(
                    this.name, this.default ?: 0, this.children!!, this.minumum ?: -20, this.maximum ?: 20
                )
            }
        } else if (this.description != null) {
            if (this.children != null) {
                class IntSettingWithDescriptionWithChildren(
                    name: String,
                    default: Int,
                    override val description: String,
                    override val children: Array<out Setting<*>>
                ) : IntSetting(name, default), Description, Children

                return IntSettingWithDescriptionWithChildren(
                    this.name, this.default ?: 0, this.description!!, this.children!!
                )
            }

            class IntSettingWithDescription(name: String, default: Int, override val description: String) :
                IntSetting(name, default), Description

            return IntSettingWithDescription(this.name, this.default ?: 0, this.description!!)
        } else if (this.children != null) {
            class IntSettingWithChildren(name: String, default: Int, override val children: Array<out Setting<*>>) :
                IntSetting(name, default), Children

            return IntSettingWithChildren(this.name, this.default ?: 0, this.children!!)
        }

        return IntSetting(this.name, this.default ?: 0)
    }
}

class LongSettingBuilder(name: String) : NumberSettingBuilder<Long, LongSetting, LongSettingBuilder>(name) {
    override fun build(): LongSetting {
        if (this.minumum != null || this.maximum != null) {
            if (this.description != null) {
                if (this.children != null) {
                    class ConstrainedLongSettingWithDescriptionWithChildren(
                        name: String,
                        default: Long,
                        override val minimum: Long,
                        override val maximum: Long,
                        override val description: String,
                        override val children: Array<out Setting<*>>
                    ) : LongSetting(name, default), Constrained<Long>, Description, Children

                    return ConstrainedLongSettingWithDescriptionWithChildren(
                        this.name,
                        this.default ?: 0,
                        this.minumum ?: -20,
                        this.maximum ?: 20,
                        this.description!!,
                        this.children!!
                    )
                }

                class ConstrainedLongSettingWithDescription(
                    name: String,
                    default: Long,
                    override val description: String,
                    override val minimum: Long,
                    override val maximum: Long
                ) : LongSetting(name, default), Constrained<Long>, Description

                return ConstrainedLongSettingWithDescription(
                    this.name, this.default ?: 0, this.description!!, this.minumum ?: -20, this.maximum ?: 20
                )
            } else if (this.children != null) {
                class ConstrainedLongSettingWithChildren(
                    name: String,
                    default: Long,
                    override val children: Array<out Setting<*>>,
                    override val minimum: Long,
                    override val maximum: Long
                ) : LongSetting(name, default), Constrained<Long>, Children

                return ConstrainedLongSettingWithChildren(
                    this.name, this.default ?: 0, this.children!!, this.minumum ?: -20, this.maximum ?: 20
                )
            }
        } else if (this.description != null) {
            if (this.children != null) {
                class LongSettingWithDescriptionWithChildren(
                    name: String,
                    default: Long,
                    override val description: String,
                    override val children: Array<out Setting<*>>
                ) : LongSetting(name, default), Description, Children

                return LongSettingWithDescriptionWithChildren(
                    this.name, this.default ?: 0, this.description!!, this.children!!
                )
            }

            class LongSettingWithDescription(name: String, default: Long, override val description: String) :
                LongSetting(name, default), Description

            return LongSettingWithDescription(this.name, this.default ?: 0, this.description!!)
        } else if (this.children != null) {
            class LongSettingWithChildren(name: String, default: Long, override val children: Array<out Setting<*>>) :
                LongSetting(name, default), Children

            return LongSettingWithChildren(this.name, this.default ?: 0, this.children!!)
        }

        return LongSetting(this.name, this.default ?: 0)
    }
}

class FloatSettingBuilder(name: String) : NumberSettingBuilder<Float, FloatSetting, FloatSettingBuilder>(name) {
    override fun build(): FloatSetting {
        if (this.minumum != null || this.maximum != null) {
            if (this.description != null) {
                if (this.children != null) {
                    class ConstrainedFloatSettingWithDescriptionWithChildren(
                        name: String,
                        default: Float,
                        override val minimum: Float,
                        override val maximum: Float,
                        override val description: String,
                        override val children: Array<out Setting<*>>
                    ) : FloatSetting(name, default, this.increment ?: 0.1f), Constrained<Float>, Description, Children

                    return ConstrainedFloatSettingWithDescriptionWithChildren(
                        this.name,
                        this.default ?: 0f,
                        this.minumum ?: -20f,
                        this.maximum ?: 20f,
                        this.description!!,
                        this.children!!
                    )
                }

                class ConstrainedFloatSettingWithDescription(
                    name: String,
                    default: Float,
                    override val description: String,
                    override val minimum: Float,
                    override val maximum: Float
                ) : FloatSetting(name, default, this.increment ?: 0.1f), Constrained<Float>, Description

                return ConstrainedFloatSettingWithDescription(
                    this.name, this.default ?: 0f, this.description!!, this.minumum ?: -20f, this.maximum ?: 20f
                )
            } else if (this.children != null) {
                class ConstrainedFloatSettingWithChildren(
                    name: String,
                    default: Float,
                    override val children: Array<out Setting<*>>,
                    override val minimum: Float,
                    override val maximum: Float
                ) : FloatSetting(name, default, this.increment ?: 0.1f), Constrained<Float>, Children

                return ConstrainedFloatSettingWithChildren(
                    this.name, this.default ?: 0f, this.children!!, this.minumum ?: -20f, this.maximum ?: 20f
                )
            }
        } else if (this.description != null) {
            if (this.children != null) {
                class FloatSettingWithDescriptionWithChildren(
                    name: String,
                    default: Float,
                    override val description: String,
                    override val children: Array<out Setting<*>>
                ) : FloatSetting(name, default, this.increment ?: 0.1f), Description, Children

                return FloatSettingWithDescriptionWithChildren(
                    this.name, this.default ?: 0f, this.description!!, this.children!!
                )
            }

            class FloatSettingWithDescription(name: String, default: Float, override val description: String) :
                FloatSetting(name, default, this.increment ?: 0.1f), Description

            return FloatSettingWithDescription(this.name, this.default ?: 0f, this.description!!)
        } else if (this.children != null) {
            class FloatSettingWithChildren(name: String, default: Float, override val children: Array<out Setting<*>>) :
                FloatSetting(name, default, this.increment ?: 0.1f), Children

            return FloatSettingWithChildren(this.name, this.default ?: 0f, this.children!!)
        }

        return FloatSetting(this.name, this.default ?: 0f, this.increment!!)
    }
}

class DoubleSettingBuilder(name: String) : NumberSettingBuilder<Double, DoubleSetting, DoubleSettingBuilder>(name) {
    override fun build(): DoubleSetting {
        if (this.minumum != null || this.maximum != null) {
            if (this.description != null) {
                if (this.children != null) {
                    class ConstrainedDoubleSettingWithDescriptionWithChildren(
                        name: String,
                        default: Double,
                        override val minimum: Double,
                        override val maximum: Double,
                        override val description: String,
                        override val children: Array<out Setting<*>>
                    ) : DoubleSetting(name, default, this.increment ?: 0.1), Constrained<Double>, Description, Children

                    return ConstrainedDoubleSettingWithDescriptionWithChildren(
                        this.name,
                        this.default ?: 0.0,
                        this.minumum ?: -20.0,
                        this.maximum ?: 20.0,
                        this.description!!,
                        this.children!!
                    )
                }

                class ConstrainedDoubleSettingWithDescription(
                    name: String,
                    default: Double,
                    override val description: String,
                    override val minimum: Double,
                    override val maximum: Double
                ) : DoubleSetting(name, default, this.increment ?: 0.1), Constrained<Double>, Description

                return ConstrainedDoubleSettingWithDescription(
                    this.name, this.default ?: 0.0, this.description!!, this.minumum ?: -20.0, this.maximum ?: 20.0
                )
            } else if (this.children != null) {
                class ConstrainedDoubleSettingWithChildren(
                    name: String,
                    default: Double,
                    override val children: Array<out Setting<*>>,
                    override val minimum: Double,
                    override val maximum: Double
                ) : DoubleSetting(name, default, this.increment ?: 0.1), Constrained<Double>, Children

                return ConstrainedDoubleSettingWithChildren(
                    this.name, this.default ?: 0.0, this.children!!, this.minumum ?: -20.0, this.maximum ?: 20.0
                )
            }
        } else if (this.description != null) {
            if (this.children != null) {
                class DoubleSettingWithDescriptionWithChildren(
                    name: String,
                    default: Double,
                    override val description: String,
                    override val children: Array<out Setting<*>>
                ) : DoubleSetting(name, default, this.increment ?: 0.1), Description, Children

                return DoubleSettingWithDescriptionWithChildren(
                    this.name, this.default ?: 0.0, this.description!!, this.children!!
                )
            }

            class DoubleSettingWithDescription(name: String, default: Double, override val description: String) :
                DoubleSetting(name, default, this.increment ?: 0.1), Description

            return DoubleSettingWithDescription(this.name, this.default ?: 0.0, this.description!!)
        } else if (this.children != null) {
            class DoubleSettingWithChildren(
                name: String, default: Double, override val children: Array<out Setting<*>>
            ) : DoubleSetting(name, default, this.increment ?: 0.1), Children

            return DoubleSettingWithChildren(this.name, this.default ?: 0.0, this.children!!)
        }

        return DoubleSetting(this.name, this.default ?: 0.0, this.increment!!)
    }
}