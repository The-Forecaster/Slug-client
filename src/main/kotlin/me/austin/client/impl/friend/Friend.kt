package me.austin.client.impl.friend

import com.google.gson.JsonObject
import net.minecraft.client.network.ClientPlayerEntity
import me.austin.client.Slug.Companion.mainDirectory
import me.austin.client.api.Manager
import me.austin.client.api.Name
import me.austin.client.api.Wrapper
import me.austin.client.util.fromJson
import me.austin.client.util.writeToJson
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.UUID
import kotlin.io.path.exists

data class Friend(override val name: String, val uuid: UUID) : Name

object FriendManager : Manager<Friend, MutableList<Friend>>, Wrapper {
    override val values = ArrayList<Friend>()

    private val friendFile = Path.of("${mainDirectory}/friends.json")

    override fun load() {
        if (!friendFile.exists()) {
            Files.createFile(friendFile)
            return
        }

        for (friend in friendFile.fromJson().keySet()) {
            values.add(Friend(friend, minecraft.socialInteractionsManager.getUuid(friend)))
        }
    }

    override fun unload() {
        JsonObject().let { obj ->
            for (friend in values) {
                obj.add(friend.name, null)
            }

            friendFile.writeToJson(obj)
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
