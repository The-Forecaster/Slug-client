package trans.rights.client.impl.friend

import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import net.minecraft.client.network.ClientPlayerEntity
import trans.rights.TransRights.Companion.mainDirectory
import trans.rights.client.api.Wrapper
import trans.rights.client.api.commons.Manager
import trans.rights.client.api.commons.Nameable
import trans.rights.client.util.fromJson
import trans.rights.client.util.readString
import trans.rights.client.util.writeToJson
import java.io.File
import java.util.*

class Friend(override val name: String, val uuid: UUID) : Nameable

object FriendManager : Manager<Friend, MutableList<Friend>>, Wrapper {
    override val values = mutableListOf<Friend>()

    private val friendFile: File = File("${mainDirectory}/friends.json")

    override fun load() {
        if (!friendFile.exists()) {
            friendFile.createNewFile()
            return
        } else if (friendFile.readString == "") {
            save()
            return
        }

        friendFile.fromJson().keySet().forEach {
            values.add(Friend(it, minecraft.socialInteractionsManager.getUuid(it)))
        }
    }

    override fun unload() {
        save(); values.clear()
    }

    private fun save() = JsonObject().let { obj ->
        values.forEach {
            obj.add(it.name, JsonPrimitive(it.uuid.toString()))
        }

        friendFile.writeToJson(obj)
    }
}

val ClientPlayerEntity.isFriend
    get() = FriendManager.values.map(Friend::uuid).contains(this.uuid)
