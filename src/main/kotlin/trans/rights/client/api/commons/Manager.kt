package trans.rights.client.api.commons

import trans.rights.client.api.command.CommandManager
import trans.rights.client.api.friend.FriendManager
import trans.rights.client.api.hack.HackManager

abstract class Manager<T>(val values: MutableCollection<T>) {
    companion object {
        private val managers = listOf(FriendManager, HackManager, CommandManager)

        fun load() {
            managers.stream().forEach { load() }
        }

        fun unload() {
            managers.stream().forEach { unload() }
        }
    }

    abstract fun load()

    open fun unload() {
        this.values.clear()
    }
}
