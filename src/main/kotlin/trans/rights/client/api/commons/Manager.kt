package trans.rights.client.api.commons

import kotlinx.coroutines.runBlocking
import trans.rights.client.api.command.CommandManager
import trans.rights.client.api.friend.FriendManager
import trans.rights.client.api.hack.HackManager

abstract class Manager<T>(val values: MutableCollection<T>) {
    companion object {
        private val managers = mutableSetOf<Manager<*>>()

        fun load() {
            managers.addAll(listOf(FriendManager, HackManager, CommandManager))

            managers.stream().forEach(Manager<*>::load)
        }

        fun unload() {
            managers.clear()
        }
    }

    abstract fun load()

    fun add(value: T, vararg values: T) {
        this.values.add(value)
        this.values.addAll(values)
    }

    open fun unload() {
        this.values.clear()
    }
}
