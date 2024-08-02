package me.austin.client.impl.setting

import me.austin.client.api.Description
import me.austin.client.api.setting.AbstractSettingBuilder
import me.austin.client.api.setting.Children
import me.austin.client.api.setting.Constrained
import me.austin.client.api.setting.NumberSettingBuilder

// Overengineered?
// Maybe

class BooleanSettingBuilder(name: String) : AbstractSettingBuilder<Boolean, BooleanSetting, BooleanSettingBuilder>(name) {
    override fun build(): BooleanSetting {
        if (this.description != null) {
            if (this.children != null) {
                return object : BooleanSetting(name, this.default ?: true), Description, Children {
                    override val description = this@BooleanSettingBuilder.description!!
                    override val children = this@BooleanSettingBuilder.children!!
                }
            }

            return object : BooleanSetting(this.name, this.default ?: true), Description {
                override val description = this@BooleanSettingBuilder.description!!
            }
        } else if (this.children != null) {
            return object : BooleanSetting(this.name, this.default ?: true), Children {
                override val children = this@BooleanSettingBuilder.children!!
            }
        }

        return BooleanSetting(this.name, this.default ?: true)
    }
}

class EnumSettingBuilder<T : Enum<*>>(name: String, default: T) : AbstractSettingBuilder<T, EnumSetting<T>, EnumSettingBuilder<T>>(name) {
    init {
        this.default = default
    }

    override fun build(): EnumSetting<T> {
        if (this.description != null) {
            if (this.children != null) {
                return object : EnumSetting<T>(this.name, this.default!!), Description, Children {
                    override val description = this@EnumSettingBuilder.description!!
                    override val children = this@EnumSettingBuilder.children!!
                }
            }

            return object : EnumSetting<T>(this.name, this.default!!), Description {
                override val description = this@EnumSettingBuilder.description!!
            }
        } else if (this.children != null) {
            return object : EnumSetting<T>(this.name, this.default!!), Children {
                override val children = this@EnumSettingBuilder.children!!
            }
        }

        return EnumSetting(this.name, this.default!!)
    }
}

class IntSettingBuilder(name: String) : NumberSettingBuilder<Int, IntSetting, IntSettingBuilder>(name) {
    override fun build(): IntSetting {
        if (this.minimum != null || this.maximum != null) {
            if (this.description != null) {
                if (this.children != null) {
                    return object : IntSetting(this.name, this.default ?: 0), Constrained<Int>, Description, Children {
                        override val minimum = this@IntSettingBuilder.minimum ?: -20
                        override val maximum = this@IntSettingBuilder.maximum ?: 20
                        override val description = this@IntSettingBuilder.description!!
                        override val children = this@IntSettingBuilder.children!!
                    }
                }

                return object : IntSetting(this.name, this.default ?: 0), Constrained<Int>, Description {
                    override val minimum = this@IntSettingBuilder.minimum ?: -20
                    override val maximum = this@IntSettingBuilder.maximum ?: 20
                    override val description = this@IntSettingBuilder.description!!
                }
            } else if (this.children != null) {
                return object : IntSetting(this.name, this.default ?: 0), Constrained<Int>, Children {
                    override val minimum = this@IntSettingBuilder.minimum ?: -20
                    override val maximum = this@IntSettingBuilder.maximum ?: 20
                    override val children = this@IntSettingBuilder.children!!
                }
            }
        } else if (this.description != null) {
            if (this.children != null) {
                return object : IntSetting(this.name, this.default ?: 0), Description, Children {
                    override val description = this@IntSettingBuilder.description!!
                    override val children = this@IntSettingBuilder.children!!
                }
            }

            return object : IntSetting(this.name, this.default ?: 0), Description {
                override val description = this@IntSettingBuilder.description!!
            }
        } else if (this.children != null) {
            return object : IntSetting(this.name, this.default ?: 0), Children {
                override val children = this@IntSettingBuilder.children!!

            }
        }

        return IntSetting(this.name, this.default ?: 0)
    }
}

class LongSettingBuilder(name: String) : NumberSettingBuilder<Long, LongSetting, LongSettingBuilder>(name) {
    override fun build(): LongSetting {
        if (this.minimum != null || this.maximum != null) {
            if (this.description != null) {
                if (this.children != null) {
                    return object : LongSetting(this.name, this.default ?: 0), Constrained<Long>, Description, Children {
                        override val minimum = this@LongSettingBuilder.minimum ?: -20
                        override val maximum = this@LongSettingBuilder.maximum ?: 20
                        override val description = this@LongSettingBuilder.description!!
                        override val children = this@LongSettingBuilder.children!!
                    }
                }

                return object : LongSetting(this.name, this.default ?: 0), Constrained<Long>, Description {
                    override val minimum = this@LongSettingBuilder.minimum ?: -20
                    override val maximum = this@LongSettingBuilder.maximum ?: 20
                    override val description = this@LongSettingBuilder.description!!
                }
            } else if (this.children != null) {
                return object : LongSetting(this.name, this.default ?: 0), Constrained<Long>, Children {
                    override val minimum = this@LongSettingBuilder.minimum ?: -20
                    override val maximum = this@LongSettingBuilder.maximum ?: 20
                    override val children = this@LongSettingBuilder.children!!
                }
            }
        } else if (this.description != null) {
            if (this.children != null) {
                return object : LongSetting(this.name, this.default ?: 0), Description, Children {
                    override val description = this@LongSettingBuilder.description!!
                    override val children = this@LongSettingBuilder.children!!
                }
            }

            return object : LongSetting(this.name, this.default ?: 0), Description {
                override val description = this@LongSettingBuilder.description!!
            }
        } else if (this.children != null) {
            return object : LongSetting(this.name, this.default ?: 0), Children {
                override val children = this@LongSettingBuilder.children!!
            }
        }

        return LongSetting(this.name, this.default ?: 0)
    }
}

class FloatSettingBuilder(name: String) : NumberSettingBuilder<Float, FloatSetting, FloatSettingBuilder>(name) {
    override fun build(): FloatSetting {
        if (this.minimum != null || this.maximum != null) {
            if (this.description != null) {
                if (this.children != null) {
                    return object : FloatSetting(this.name, this.default ?: 0f, this.increment ?: 0.1f), Constrained<Float>, Description, Children {
                        override val minimum = this@FloatSettingBuilder.minimum ?: -20f
                        override val maximum = this@FloatSettingBuilder.maximum ?: 20f
                        override val description = this@FloatSettingBuilder.description!!
                        override val children = this@FloatSettingBuilder.children!!
                    }
                }

                return object : FloatSetting(this.name, this.default ?: 0f, this.increment ?: 0.1f), Constrained<Float>, Description {
                    override val minimum = this@FloatSettingBuilder.minimum ?: -20f
                    override val maximum = this@FloatSettingBuilder.maximum ?: 20f
                    override val description = this@FloatSettingBuilder.description!!
                }
            } else if (this.children != null) {
                return object : FloatSetting(this.name, this.default ?: 0f, this.increment ?: 0.1f), Constrained<Float>, Children {
                    override val minimum = this@FloatSettingBuilder.minimum ?: -20f
                    override val maximum = this@FloatSettingBuilder.maximum ?: 20f
                    override val children = this@FloatSettingBuilder.children!!
                }
            }
        } else if (this.description != null) {
            if (this.children != null) {
                return object : FloatSetting(this.name, this.default ?: 0f, this.increment ?: 0.1f), Description, Children {
                    override val description = this@FloatSettingBuilder.description!!
                    override val children = this@FloatSettingBuilder.children!!
                }
            }

            return object : FloatSetting(this.name, this.default ?: 0f, this.increment ?: 0.1f), Description {
                override val description = this@FloatSettingBuilder.description!!
            }
        } else if (this.children != null) {
            return object : FloatSetting(this.name, this.default ?: 0f, this.increment ?: 0.1f), Children {
                override val children = this@FloatSettingBuilder.children!!
            }
        }

        return FloatSetting(this.name, this.default ?: 0f, this.increment!!)
    }
}

class DoubleSettingBuilder(name: String) : NumberSettingBuilder<Double, DoubleSetting, DoubleSettingBuilder>(name) {
    override fun build(): DoubleSetting {
        if (this.minimum != null || this.maximum != null) {
            if (this.description != null) {
                if (this.children != null) {
                    return object : DoubleSetting(this.name, this.default ?: 0.0, this.increment ?: 0.1), Constrained<Double>, Description, Children {
                        override val minimum = this@DoubleSettingBuilder.minimum ?: -20.0
                        override val maximum = this@DoubleSettingBuilder.maximum ?: 20.0
                        override val description = this@DoubleSettingBuilder.description!!
                        override val children = this@DoubleSettingBuilder.children!!
                    }
                }

                return object : DoubleSetting(this.name, this.default ?: 0.0, this.increment ?: 0.1), Constrained<Double>, Description {
                    override val minimum = this@DoubleSettingBuilder.minimum ?: -20.0
                    override val maximum = this@DoubleSettingBuilder.maximum ?: 20.0
                    override val description = this@DoubleSettingBuilder.description!!
                }
            } else if (this.children != null) {
                return object : DoubleSetting(this.name, this.default ?: 0.0, this.increment ?: 0.1), Constrained<Double>, Children {
                    override val minimum = this@DoubleSettingBuilder.minimum ?: -20.0
                    override val maximum = this@DoubleSettingBuilder.maximum ?: 20.0
                    override val children = this@DoubleSettingBuilder.children!!
                }
            }
        } else if (this.description != null) {
            if (this.children != null) {
                return object : DoubleSetting(this.name, this.default ?: 0.0, this.increment ?: 0.1), Description, Children {
                    override val description = this@DoubleSettingBuilder.description!!
                    override val children = this@DoubleSettingBuilder.children!!
                }
            }

            return object : DoubleSetting(this.name, this.default ?: 0.0, this.increment ?: 0.1), Description {
                override val description = this@DoubleSettingBuilder.description!!
            }
        } else if (this.children != null) {
            return object : DoubleSetting(this.name, this.default ?: 0.0, this.increment ?: 0.1), Children {
                override val children = this@DoubleSettingBuilder.children!!
            }
        }

        return DoubleSetting(this.name, this.default ?: 0.0, this.increment!!)
    }
}