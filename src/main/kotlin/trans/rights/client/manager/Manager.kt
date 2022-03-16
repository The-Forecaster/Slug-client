package trans.rights.client.manager

import trans.rights.client.modules.Module
import trans.rights.client.manager.impl.CommandManager
import trans.rights.client.manager.impl.HackManager

abstract class Manager<T : Module>(val values: MutableCollection<T>) {
    companion object {
        private val values = mutableSetOf<Manager<*>>()

        fun load() {
            values.add(HackManager)
            values.add(CommandManager)
        }

        fun unload() {
            values.clear()
        }
    }

    abstract fun load()

    fun add(value: T): T {
        this.values += value

        return value
    }

    fun add(values: Collection<T>) {
        this.values.addAll(values)
    }

    open fun unload() {
        this.values.clear()
    }
}
