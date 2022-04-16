package trans.rights.client.api

import kotlinx.coroutines.runBlocking
import trans.rights.client.api.command.CommandManager
import trans.rights.client.api.hack.HackManager

abstract class Manager<T>(val values: MutableCollection<T>) {
    companion object {
        private val managers = mutableSetOf<Manager<*>>()

        fun load() {
            managers.addAll(listOf(HackManager, CommandManager))

            runBlocking {
                managers.stream().forEach { manager ->
                    manager()
                }
            }
        }

        fun unload() {
            managers.clear()
        }
    }

    abstract operator fun invoke(): Manager<T>

    fun add(value: T, vararg values: T) {
        this.values.add(value)
        this.values.addAll(values)
    }

    open fun unload() {
        this.values.clear()
    }
}
