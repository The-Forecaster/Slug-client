package trans.rights.client.manager

import kotlinx.coroutines.runBlocking
import trans.rights.client.manager.impl.CommandManager
import trans.rights.client.manager.impl.HackManager
import trans.rights.client.modules.Module

abstract class Manager<T : Module>(val values: MutableCollection<T>) {
    companion object {
        private val managers = mutableSetOf<Manager<*>>()

        fun load() {
            managers.addAll(listOf(
                HackManager,
                CommandManager
            ))

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
