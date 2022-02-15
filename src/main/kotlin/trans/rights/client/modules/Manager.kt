package trans.rights.client.modules

import trans.rights.client.modules.command.CommandManager
import trans.rights.client.modules.hack.HackManager

abstract class Manager<T : Module>(val values: MutableCollection<T>) {
    companion object  {
        private val values = mutableSetOf<Manager<*>>()

        fun load() {
            this.values.add(HackManager)
            this.values.add(CommandManager)
        }

        fun unload() {
            this.values.clear()
        }
    }

    abstract fun load()

    fun add(value: T): T {
        this.values += value

        return value
    }

    open fun unload() {
        this.values.clear()
    }
}
