package trans.rights.client.api.friend

import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import net.minecraft.client.network.ClientPlayerEntity
import trans.rights.TransRights.Companion.mainDirectory
import trans.rights.client.api.Wrapper
import trans.rights.client.api.commons.Manager
import trans.rights.client.api.commons.Nameable
import trans.rights.client.util.fromJson
import trans.rights.client.util.writeToJson
import java.io.File
import java.util.*

class Friend(override val name: String, val uuid: UUID) : Nameable

object FriendManager : Manager<Friend>(mutableListOf()), Wrapper {
    private val friendFile: File = File("${mainDirectory}/friends.json")

    override fun load() {
        if (!friendFile.exists()) {
            friendFile.createNewFile()
            return
        }

        friendFile.fromJson().keySet().stream().forEach {
            values.add(
                Friend(it, minecraft.socialInteractionsManager.getUuid(it))
            )
        }
    }

    override fun unload() {
        save()

        super.unload()
    }

    fun save() {
        val obj = JsonObject()

        values.stream().forEach {
            obj.add(it.name, JsonPrimitive(it.uuid.toString()))
        }

        friendFile.writeToJson(obj)
    }
}

fun ClientPlayerEntity.isFriend() = FriendManager.values.map(Friend::uuid).contains(this.uuid)
