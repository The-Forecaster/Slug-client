package trans.rights.client.modules

import trans.rights.client.modules.command.CommandManager
import trans.rights.client.modules.hack.HackManager

abstract class Manager<T : Module>(val values: MutableList<T> = mutableListOf()) : Module("", "") {
    companion object : Manager<Manager<*>>() {
        fun reload() {
            this.load()
            this.unload()
        }

        override fun load() {
            this.add(HackManager)
            this.add(CommandManager)
        }

        override fun unload() {
            this.values.clear()
        }
    }

    abstract fun load()

    fun add(value: T): T {
        this.values.add(value)

        return value
    }

    open fun unload() {
        this.values.clear()
    }
}
