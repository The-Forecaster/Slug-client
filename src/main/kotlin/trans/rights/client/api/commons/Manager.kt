package trans.rights.client.api.commons

import trans.rights.client.api.command.CommandManager
import trans.rights.client.api.friend.FriendManager
import trans.rights.client.api.hack.HackManager
import java.util.*

abstract class Manager<T>(val values: MutableCollection<T>) {
    companion object {
        private val managers = LinkedList(listOf(FriendManager, HackManager, CommandManager))

        fun load() = managers.stream().forEach(Manager<*>::load)

        fun unload() = managers.stream().forEach(Manager<*>::unload)
    }

    abstract fun load()

    open fun unload() = this.values.clear()
}
