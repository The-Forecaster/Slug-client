package trans.rights.client.api.commons

import trans.rights.client.api.command.CommandManager
import trans.rights.client.api.friend.FriendManager
import trans.rights.client.api.hack.HackManager
import java.util.LinkedList

abstract class Manager<T>(val values: MutableCollection<T>) {
    companion object {
        private val managers = LinkedList(listOf(FriendManager, HackManager, CommandManager))

        fun load() {
            managers.stream().forEach { manager ->
                manager.load()
            }
        }

        fun unload() {
            managers.stream().forEach { manager ->
                manager.unload()
            }
        }
    }

    abstract fun load()

    open fun unload() {
        this.values.clear()
    }
}
