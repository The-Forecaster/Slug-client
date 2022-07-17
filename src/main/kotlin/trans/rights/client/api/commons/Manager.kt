package trans.rights.client.api.commons

import trans.rights.client.api.command.CommandManager
import trans.rights.client.impl.friend.FriendManager
import trans.rights.client.api.hack.HackManager
import java.util.*

interface Manager<T, L : Collection<T>> {
    companion object {
        private val managers = listOf(FriendManager, HackManager, CommandManager)

        fun load() = managers.stream().forEach(Manager<*, *>::load)

        fun unload() = managers.stream().forEach(Manager<*, *>::unload)
    }

    val values: L

    fun load()

    fun unload()
}
