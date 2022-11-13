package trans.rights.client.impl.friend

import com.google.gson.JsonObject
import net.minecraft.client.network.ClientPlayerEntity
import trans.rights.TransRights.Companion.mainDirectory
import trans.rights.client.api.Manager
import trans.rights.client.api.Nameable
import trans.rights.client.api.Wrapper
import trans.rights.client.util.fromJson
import trans.rights.client.util.writeToJson
import java.io.File
import java.util.UUID

data class Friend(override val name: String, val uuid: UUID) : Nameable

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
        save()
        values.clear()
    }

    private fun save() = JsonObject().let {
        for (friend in values) it.add(friend.name, null)

        friendFile.writeToJson(it)
    }
}

internal val ClientPlayerEntity.isFriend
    get() = FriendManager.values.map(Friend::uuid).contains(this.uuid)
