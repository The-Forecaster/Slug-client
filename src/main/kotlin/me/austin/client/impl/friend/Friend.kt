package me.austin.client.impl.friend

import com.google.gson.JsonObject
import net.minecraft.client.network.ClientPlayerEntity
import me.austin.client.Slug.Companion.mainDirectory
import me.austin.client.api.Manager
import me.austin.client.api.Wrapper
import me.austin.client.util.fromJson
import me.austin.client.util.writeToJson
import me.austin.client.api.Nameable
import java.io.File
import java.util.UUID

data class Friend(final override val name: String, val uuid: UUID) : Nameable

object FriendManager : Manager<Friend, MutableList<Friend>>, Wrapper {
    override val values = ArrayList<Friend>()

    private val friendFile = File("${mainDirectory}/friends.json")

    override fun load() {
        if (!friendFile.exists()) {
            friendFile.createNewFile()
            return
        }

        for (friend in friendFile.fromJson().keySet()) values.add(Friend(friend, minecraft.socialInteractionsManager.getUuid(friend)))
    }

    override fun unload() {
        JsonObject().let {
            for (friend in values) it.add(friend.name, null)

            friendFile.writeToJson(it)
        }
        values.clear()
    }

    fun add(name: String) {
        values.add(Friend(name, minecraft.socialInteractionsManager.getUuid(name)))
    }

    fun remove(name: String) {
        values.removeIf { it.name == name }
    }
}

internal val ClientPlayerEntity.isFriend
    get() = FriendManager.values.map(Friend::uuid).contains(this.uuid)
