package trans.rights.client.modules

import trans.rights.client.modules.command.CommandManager
import trans.rights.client.modules.hack.HackManager

abstract class Manager<T : Module>(val values: MutableCollection<T>) : Module("", "") {
    companion object : Manager<Manager<*>>(mutableSetOf()) {
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
        this.values += value

        return value
    }

    open fun unload() {
        this.values.clear()
    }
}
